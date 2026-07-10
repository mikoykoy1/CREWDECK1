package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.User;

public class UserDAO {

    // 1. FIND BY EMAIL (Used during login authentication)
    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM `users` WHERE `email` = ?";
        
        try (
            Connection connection = DBConnection.GetConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getBoolean("isActive")
                    );
                }
            }
        }
        return null; // Return null if user does not exist
    }

    // 2. GET USER ROLES (Fetches role name strings assigned to a user id)
    public List<String> getUserRoles(int userId) throws SQLException {
        List<String> roles = new ArrayList<>();
        String sql = "SELECT r.name FROM `roles` r "
                   + "JOIN `user_role` ur ON r.id = ur.role_id "
                   + "WHERE ur.user_id = ?";
                   
        try (
            Connection connection = DBConnection.GetConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    roles.add(rs.getString("name"));
                }
            }
        }
        return roles;
    }

    // 3. ADD USER (Inserts credentials and populates the auto-generated primary key ID)
    public void add(User user) throws SQLException {
        String sql = "INSERT INTO `users` (`email`, `password_hash`, `isActive`) VALUES (?, ?, ?)";
        
        try (
            Connection connection = DBConnection.GetConnection();
            // Statement.RETURN_GENERATED_KEYS allows retrieval of the auto-increment value assigned by MariaDB/MySQL
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPasswordHash());
            stmt.setBoolean(3, user.getIsActive());
            
            stmt.executeUpdate();
            
            // Extract and update the User object with its fresh primary key
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    // 4. ASSIGN ROLE (Bridges a User to a Role name in the many-to-many user_role junction table)
    public void assignRole(int userId, String roleName) throws SQLException {
        // Look up the corresponding role_id dynamically by selecting it via its descriptive name
        String findRoleSql = "SELECT `id` FROM `roles` WHERE `name` = ?";
        String insertRelationSql = "INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (?, ?)";
        
        try (Connection connection = DBConnection.GetConnection()) {
            int roleId = -1;
            
            // Step 4a: Find the target role ID matching the string descriptor
            try (PreparedStatement stmt = connection.prepareStatement(findRoleSql)) {
                stmt.setString(1, roleName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        roleId = rs.getInt("id");
                    } else {
                        throw new SQLException("Role assignment failed: Role '" + roleName + "' does not exist.");
                    }
                }
            }
            
            // Step 4b: Bind the relationship entry safely into your user_role bridge table
            try (PreparedStatement stmt = connection.prepareStatement(insertRelationSql)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, roleId);
                stmt.executeUpdate();
            }
        }
    }
}