package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Employee;
import Model.User;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import Presentation.EmployeeRecordDialog;



public class EmployeeDAO {

    // CREATE
    public void add(Employee emp) throws SQLException {
        // Pull the primary key ID assigned via the User account generation process
        int userId = emp.getUser().getId(); 
        
        String sql = "INSERT INTO `employee` "
                + "(user_id, name, contactNum, address, department, dateHired, "
                + "salary, status, evaluationScore, evaluationRemarks, workHours) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (
            Connection connection = DBConnection.GetConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, userId);
            stmt.setString(2, emp.getName());
            stmt.setString(3, emp.getContactNum());
            stmt.setString(4, emp.getAddress());
            stmt.setString(5, emp.getDepartment());
            stmt.setObject(6, emp.getDateHired()); // LocalDates pass smoothly via setObject
            stmt.setDouble(7, emp.getSalary());
            stmt.setString(8, emp.getStatus());
            stmt.setInt(9, emp.getEvaluationScore());
            stmt.setString(10, emp.getEvaluationRemarks());
            stmt.setDouble(11, emp.getWorkHours());
            
            stmt.executeUpdate();
        }
    }

    // READ (all records)
    public List<Employee> getAll() throws SQLException {
        List<Employee> lists = new ArrayList<>();
        // We use a JOIN statement to build both the User object and Employee object concurrently
        String sql = "SELECT e.*, u.email, u.isActive FROM `employee` e "
                   + "JOIN `users` u ON e.user_id = u.id";
                   
        try (
            Connection connection = DBConnection.GetConnection();
            Statement stmt = connection.createStatement();    
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                // 1. Rebuild the core user credentials sub-object
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("email"),
                    "PROTECTED", // Hide hash strings from generic memory pools
                    rs.getBoolean("isActive")
                );

                // 2. Wrap it inside the complete full profile data sequence
                Employee emp = new Employee(
                    user,
                    rs.getString("name"),
                    rs.getString("contactNum"),
                    rs.getString("address"),
                    rs.getString("department"),
                    rs.getDate("dateHired") != null ? rs.getDate("dateHired").toLocalDate() : null,
                    rs.getDouble("salary"),
                    rs.getString("status"),
                    rs.getInt("evaluationScore"),
                    rs.getString("evaluationRemarks"),
                    rs.getDouble("workHours")
                );
                lists.add(emp);
            }
        }
        return lists;
    }

    // READ (one precise profile)
    public Employee getItem(int id) throws SQLException {
        String sql = "SELECT e.*, u.email, u.isActive FROM `employee` e "
                   + "JOIN `users` u ON e.user_id = u.id WHERE e.user_id = ?";
                   
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
                        rs.getInt("evaluationScore"),
                        rs.getString("evaluationRemarks"),
                        rs.getDouble("workHours")
                    );
                }
            }
            return null;
        }
    }

    // UPDATE
    public void update(Employee updatedemp) throws SQLException {
        String sql = "UPDATE `employee` SET "
                + "name = ?, contactNum = ?, address = ?, department = ?, "
                + "dateHired = ?, salary = ?, status = ?, "
                + "evaluationScore = ?, evaluationRemarks = ?, workHours = ? "
                + "WHERE user_id = ?";
                
        try (
            Connection connection = DBConnection.GetConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, updatedemp.getName());
            stmt.setString(2, updatedemp.getContactNum());
            stmt.setString(3, updatedemp.getAddress());
            stmt.setString(4, updatedemp.getDepartment());
            stmt.setObject(5, updatedemp.getDateHired());
            stmt.setDouble(6, updatedemp.getSalary());
            stmt.setString(7, updatedemp.getStatus());
            stmt.setInt(8, updatedemp.getEvaluationScore());
            stmt.setString(9, updatedemp.getEvaluationRemarks());
            stmt.setDouble(10, updatedemp.getWorkHours());
            stmt.setInt(11, updatedemp.getUserId()); // Matches the target user_id index
            
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {
        // Because of ON DELETE CASCADE on your foreign key configurations, 
        // removing the primary record inside 'users' automatically purges the employee data safely!
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