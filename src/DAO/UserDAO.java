package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.User;

public class UserDAO {

    // 1. FIND BY EMAIL (Used during login authentication)
    
    
    public User findByIdOrEmail(String identifier) throws SQLException {
        // Check if the input is entirely numbers (regex for one or more digits)
        boolean isNumeric = identifier.trim().matches("\\d+");
        String sql;

        if (isNumeric) {
            sql = "SELECT * FROM `users` WHERE `id` = ?";
        } else {
            sql = "SELECT * FROM `users` WHERE `email` = ?";
        }

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            if (isNumeric) {
                // Convert the string to an integer for the ID query
                stmt.setInt(1, Integer.parseInt(identifier.trim()));
            } else {
                stmt.setString(1, identifier.trim());
            }

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
        return null; // Return null if no matching User ID or Email is found
    }


    // 2. GET USER ROLES (Fetches role name strings assigned to a user id)
    public List<String> getUserRoles(int userId) throws SQLException {
        List<String> roles = new ArrayList<>();
        String sql = "SELECT r.name FROM `roles` r "
                   + "JOIN `user_role` ur ON r.id = ur.role_id "
                   + "WHERE ur.user_id = ?";
                   
        try (
            Connection connection = DBConnection.getConnection();
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
            Connection connection = DBConnection.getConnection();
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
        
        try (Connection connection = DBConnection.getConnection()) {
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
    
    // Inside DAO/UserDAO.java

/**
 * Generates a completely unique corporate email by checking the database
 * for existing active addresses and appending increments if duplicates exist.
 */
    public String generateUniqueEmail(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }

        // Format the name: "John Doe" -> "john.doe"
        String baseName = fullName.trim().toLowerCase().replaceAll("[^a-zA-Z0-9]+", ".");
        String domain = "@company.com";
        String generatedEmail = baseName + domain;

        int counter = 1;

        // Check database loop to ensure absolute uniqueness
        while (doesEmailExist(generatedEmail)) {
            generatedEmail = baseName + counter + domain; // e.g., john.doe1@company.com
            counter++;
        }

        return generatedEmail;
    }

/**
 * Helper to check if an email already exists in the users table.
 */
    public boolean doesEmailExist(String email) {
        String sql = "SELECT 1 FROM `users` WHERE `email` = ? LIMIT 1";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Returns true if email is taken
            }
        } catch (SQLException e) {
            System.err.println("Error verifying unique email constraint: " + e.getMessage());
            return true; // Fallback safe lock
        }
    }
}