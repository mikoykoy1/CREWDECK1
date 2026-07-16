package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Request;

public class RequestDAO {

    // 1. Used by Employees to submit a new request ticket
    public boolean add(Request req) throws SQLException {
        String sql = "INSERT INTO employee_requests (user_id, request_type, details, submission_date, status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, req.getUserId());
            stmt.setString(2, req.getRequestType());
            stmt.setString(3, req.getDetails());
            stmt.setDate(4, Date.valueOf(req.getSubmissionDate()));
            stmt.setString(5, req.getStatus());
            
            return stmt.executeUpdate() > 0;
        }
    }

    // 2. Used by HR Admin to pull up all incoming or unprocessed request items
    public List<Request> fetchAllPending() throws SQLException {
        List<Request> pendingList = new ArrayList<>();
        String sql = "SELECT * FROM employee_requests WHERE status = 'Pending' ORDER BY submission_date ASC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Request req = new Request(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("request_type"),
                    rs.getString("details"),
                    rs.getDate("submission_date").toLocalDate(),
                    rs.getString("status"),
                    rs.getString("admin_remarks")
                );
                pendingList.add(req);
            }
        }
        return pendingList;
    }

    // 3. Used by HR Admin to log a final decision (Approve / Reject)
    public boolean processRequest(int requestId, String decisionStatus, String remarks) throws SQLException {
        String sql = "UPDATE employee_requests SET status = ?, admin_remarks = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, decisionStatus);
            stmt.setString(2, remarks);
            stmt.setInt(3, requestId);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Counts pending request
    public int getPendingRequestsCount() {
        String query = "SELECT COUNT(*) AS total FROM employee_requests WHERE status = 'Pending'"; 
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}