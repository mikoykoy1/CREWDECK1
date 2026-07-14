package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Employee;
import Model.User;

public class EmployeeDAO {

    // CREATE
    public void add(Employee emp) throws SQLException {
        int userId = emp.getUser().getId(); 
        
        String sql = "INSERT INTO `employee` "
                + "(user_id, name, contactNum, address, department, dateHired, salary, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (
            Connection connection = DBConnection.GetConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, userId);
            stmt.setString(2, emp.getName());
            stmt.setString(3, emp.getContactNum());
            stmt.setString(4, emp.getAddress());
            stmt.setString(5, emp.getDepartment());
            stmt.setObject(6, emp.getDateHired()); // LocalDate
            stmt.setDouble(7, emp.getSalary());
            stmt.setString(8, emp.getStatus());
            
            stmt.executeUpdate();
        }
    }

    // READ (all records)
    public List<Employee> getAll() throws SQLException {
        List<Employee> lists = new ArrayList<>();
        // Querying strictly existing columns from your database schema
        String sql = "SELECT e.user_id, e.name, e.contactNum, e.address, e.department, e.dateHired, e.salary, e.status, "
                   + "u.email, u.isActive "
                   + "FROM `employee` e "
                   + "JOIN `users` u ON e.user_id = u.id";
                   
        try (
            Connection connection = DBConnection.GetConnection();
            Statement stmt = connection.createStatement();    
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("email"),
                    "PROTECTED", 
                    rs.getBoolean("isActive")
                );

                // Rebuilding Employee without querying non-existent columns in 'employee' table
                Employee emp = new Employee(
                    user,
                    rs.getString("name"),
                    rs.getString("contactNum"),
                    rs.getString("address"),
                    rs.getString("department"),
                    rs.getDate("dateHired") != null ? rs.getDate("dateHired").toLocalDate() : null,
                    rs.getDouble("salary"),
                    rs.getString("status"),
                    0,     // evaluationScore default (stored in performance_evaluations table)
                    "",    // evaluationRemarks default
                    0.0    // workHours default
                );
                lists.add(emp);
            }
        }
        return lists;
    }

    // READ (one precise profile)
    public Employee getItem(int id) throws SQLException {
        String sql = "SELECT e.user_id, e.name, e.contactNum, e.address, e.department, e.dateHired, e.salary, e.status, "
                   + "u.email, u.isActive "
                   + "FROM `employee` e "
                   + "JOIN `users` u ON e.user_id = u.id "
                   + "WHERE e.user_id = ?";
                   
        try (
            Connection connection = DBConnection.GetConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        "PROTECTED",
                        rs.getBoolean("isActive")
                    );

                    return new Employee(
                        user,
                        rs.getString("name"),
                        rs.getString("contactNum"),
                        rs.getString("address"),
                        rs.getString("department"),
                        rs.getDate("dateHired") != null ? rs.getDate("dateHired").toLocalDate() : null,
                        rs.getDouble("salary"),
                        rs.getString("status"),
                        0,   // evaluationScore default
                        "",  // evaluationRemarks default
                        0.0  // workHours default
                    );
                }
            }
            return null;
        }
    }

    // UPDATE
    public void update(Employee updatedemp, String roleName) throws SQLException {
        String updateEmployeeSql = "UPDATE `employee` SET "
                + "name = ?, contactNum = ?, address = ?, department = ?, "
                + "dateHired = ?, salary = ?, status = ? "
                + "WHERE user_id = ?";

        String updateUserSql = "UPDATE `users` SET email = ? WHERE id = ?";
        
        // SQL to dynamically update the user's mapped role in your 'user_role' join table
        String updateRoleSql = "UPDATE `user_role` SET role_id = (SELECT id FROM `roles` WHERE name = ?) WHERE user_id = ?";
                
        Connection connection = null;
        try {
            connection = DBConnection.GetConnection();
            connection.setAutoCommit(false); // Enable manual transactions

            // 1. Update User Account Details
            if (updatedemp.getUser() != null) {
                try (PreparedStatement stmtUser = connection.prepareStatement(updateUserSql)) {
                    stmtUser.setString(1, updatedemp.getUser().getEmail());
                    stmtUser.setInt(2, updatedemp.getUserId());
                    stmtUser.executeUpdate();
                }
            }

            // 2. Update Employee Profile details (using strictly matching DB columns)
            try (PreparedStatement stmtEmp = connection.prepareStatement(updateEmployeeSql)) {
                stmtEmp.setString(1, updatedemp.getName());
                stmtEmp.setString(2, updatedemp.getContactNum());
                stmtEmp.setString(3, updatedemp.getAddress());
                stmtEmp.setString(4, updatedemp.getDepartment());
                stmtEmp.setObject(5, updatedemp.getDateHired());
                stmtEmp.setDouble(6, updatedemp.getSalary());
                stmtEmp.setString(7, updatedemp.getStatus());
                stmtEmp.setInt(8, updatedemp.getUserId());
                
                stmtEmp.executeUpdate();
            }

            // 3. Update User security role if a role option was chosen
            if (roleName != null && !roleName.isEmpty()) {
                try (PreparedStatement stmtRole = connection.prepareStatement(updateRoleSql)) {
                    stmtRole.setString(1, roleName);
                    stmtRole.setInt(2, updatedemp.getUserId());
                    stmtRole.executeUpdate();
                }
            }

            connection.commit(); // Save changes

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback if any part fails
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    System.err.println("Closing connection failed: " + ex.getMessage());
                }
            }
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {
        // Because of ON DELETE CASCADE on your constraints, purging users purges user_role & employee tables instantly!
        String sql = "DELETE FROM `users` WHERE id = ?";
        
        try (
            Connection connection = DBConnection.GetConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
