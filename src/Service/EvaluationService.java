package Service;


import DAO.EvaluationDAO;
import model.Evaluation;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class EvaluationService {
    private final EvaluationDAO evalDAO = new EvaluationDAO();

    // Validates inputs and handles submission execution logs
    public boolean submitEvaluation(int targetEmployeeId, int adminId, String period, int score, String remarks) {
        // Business Rules Validation Checkpoints
        if (remarks == null || remarks.trim().isEmpty() || period == null || period.trim().isEmpty()) {
            return false; 
        }
        if (score < 1 || score > 5) { // Assuming a 1-5 rating standard
            return false;
        }

        try {
            Evaluation eval = new Evaluation();
            eval.setUserId(targetEmployeeId);
            eval.setEvaluatorId(adminId);
            eval.setEvaluationDate(LocalDate.now()); // Automatically stamp current date
            eval.setPeriod(period);
            eval.setScore(score);
            eval.setFeedbackRemarks(remarks);

            return evalDAO.add(eval);
        } catch (SQLException ex) {
            System.err.println("Error saving evaluation record context: " + ex.getMessage());
            return false;
        }
    }

    // Requests history arrays and safely provides empty collections upon database issues
    public List<Evaluation> getEmployeePerformanceHistory(int employeeId) {
        try {
            return evalDAO.fetchHistoryByEmployee(employeeId);
        } catch (SQLException ex) {
            System.err.println("Error pulling performance history array: " + ex.getMessage());
            return new ArrayList<>(); // Return safe empty array to prevent UI crashes
        }
    }
    
    public DefaultTableModel getEvaluationHistoryTableModel(int employeeId) {
        try {
            // Define your UI header columns
            String[] columns = {"ID", "Score", "Remarks", "Evaluation Date"};
            
            // Retrieve raw list from database
            List<Evaluation> historyList = evalDAO.fetchHistoryByEmployee(employeeId);
            Object[][] data = new Object[historyList.size()][4];

            for (int i = 0; i < historyList.size(); i++) {
                Evaluation eval = historyList.get(i);
                
                data[i][0] = eval.getId();
                data[i][1] = eval.getScore() + "/100 (" + getRatingString(eval.getScore()) + ")";
                data[i][2] = eval.getFeedbackRemarks();
                data[i][3] = eval.getEvaluationDate().toString(); 
            }

            // Return table model with cell editing disabled
            return new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading evaluation history: " + e.getMessage());
            return null;
        }
    }

    // Rating text logic remains safely encapsulated inside the service layer
    private String getRatingString(int score) {
        if (score >= 90) return "Outstanding";
        if (score >= 80) return "Very Satisfactory";
        if (score >= 70) return "Satisfactory";
        if (score >= 60) return "Needs Improvement";
        return "Unsatisfactory";
    }
}

