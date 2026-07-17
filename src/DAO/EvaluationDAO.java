package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Evaluation;

public class EvaluationDAO {

    // Saves a review session record down into the database log
    public boolean add(Evaluation eval) throws SQLException {
        String sql = "INSERT INTO performance_evaluations (user_id, evaluator_id, evaluation_date, period, score, feedback_remarks) VALUES (?, ?, ?, ?, ?, ?)";
        DBConnection dbcconnection = new DBConnection();
        
        try (Connection conn = dbcconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, eval.getUserId());
            stmt.setInt( 2, eval.getEvaluatorId());
            stmt.setDate(3, Date.valueOf(eval.getEvaluationDate()));
            stmt.setString(4, eval.getPeriod());
            stmt.setInt(5, eval.getScore());
            stmt.setString(6, eval.getFeedbackRemarks());
            
            return stmt.executeUpdate() > 0;
        }
    }

    // Fetches every evaluation row tied to a specific employee ID
    public List<Evaluation> fetchHistoryByEmployee(int employeeId) throws SQLException {
        List<Evaluation> historyList = new ArrayList<>();
        String sql = "SELECT * FROM performance_evaluations WHERE user_id = ? ORDER BY evaluation_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Evaluation eval = new Evaluation(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("evaluator_id"),
                        rs.getDate("evaluation_date").toLocalDate(),
                        rs.getString("period"),
                        rs.getInt("score"),
                        rs.getString("feedback_remarks")
                    );
                    historyList.add(eval);
                }
            }
        }
        return historyList;
    }
    
public List<Evaluation> getEvaluationsFiltered(int targetUserId, int currentUserId) throws SQLException {
        List<Evaluation> list = new ArrayList<>();
        String sql;
        
        // Case A: Selected themselves -> Show evaluations ABOUT them
        if (targetUserId == currentUserId) {
            sql = "SELECT * FROM `performance_evaluations` WHERE `user_id` = ?";
        } 
        // Case B: Selected someone else -> Show evaluations written BY the current user about that target
        else {
            sql = "SELECT * FROM `performance_evaluations` WHERE `evaluator_id` = ? AND `user_id` = ?";
        }

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            if (targetUserId == currentUserId) {
                stmt.setInt(1, currentUserId);
            } else {
                stmt.setInt(1, currentUserId); // evaluator_id
                stmt.setInt(2, targetUserId);  // user_id
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Evaluation eval = new Evaluation(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("evaluator_id"),
                        rs.getDate("evaluation_date").toLocalDate(), 
                        rs.getString("period"),
                        rs.getInt("score"),
                        rs.getString("feedback_remarks") 
                    );
                    list.add(eval);
                }
            }
        }
        return list;
    }
    
    
    public int getPendingEvaluationsCount() {
        String query = "SELECT COUNT(*) AS total FROM performance_evaluations ";
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
