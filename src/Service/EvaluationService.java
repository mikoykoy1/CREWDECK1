package Service;


import DAO.EvaluationDAO;
import Model.Evaluation;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class EvaluationService {
    private final EvaluationDAO evalDAO;
    
    public EvaluationService() {
        this.evalDAO = new EvaluationDAO(); 
    }
    // Validates inputs and handles submission execution logs
    public boolean submitEvaluation(int targetEmployeeId, int adminId, String period, int score, String remarks) {
        // Business Rules Validation Checkpoints
        if (remarks == null || remarks.trim().isEmpty() || period == null || period.trim().isEmpty()) {
            return false; 
        }
        if (score < 0 || score > 100) {
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
    
/**
     * Context A: Used for the dynamic Combo Box view.
     * Shows filtered evaluation interactions between a selected target and the current user.
     * @param selectedUserId
     * @param currentUserId
     * @return 
     * @throws java.sql.SQLException
     */
    public DefaultTableModel getFilteredEvaluationsModel(int selectedUserId, int currentUserId) {
        try{
        List<Evaluation> evals = evalDAO.getEvaluationsFiltered(selectedUserId, currentUserId);
        
        String[] columns = {"Eval No.", "Evaluee", "Evaluator", "Period", "Score", "Remarks", "Evaluation Date"};
        Object[][] data = new Object[evals.size()][7];
        
        for (int i = 0; i < evals.size(); i++) {
            Evaluation eval = evals.get(i);
            data[i][0] = eval.getId(); //
            data[i][1] = eval.getUserId() == currentUserId ? "You" : eval.getUserId(); //[cite: 5]
            data[i][2] = eval.getEvaluatorId() == currentUserId ? "You" : eval.getEvaluatorId(); //[cite: 5]
            data[i][3] = eval.getPeriod(); //
            data[i][4] = eval.getScore() + " / 100"; //[cite: 5]
            data[i][5] = eval.getFeedbackRemarks(); //[cite: 5]
            data[i][6] = eval.getEvaluationDate().toString(); //[cite: 5]
        }

        return createUneditableModel(data, columns);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error loading evaluation histor"+e.getMessage());
            return null;
        }
    }

    /**
     * Context B: Used for the absolute History Log table.
     * Fetches the total list timeline of every review written about an individual employee.
     * @param employeeId
     * @return 
     * @throws java.sql.SQLException
     */

    public DefaultTableModel getEvaluationHistoryTableModel(int employeeId) {
        try {
            Service.UserSession session = Service.UserSession.getInstance();
            int currentUserId = session.getLoggedInUser().getId();
            String currentRole = session.getRole();

            // ONLY restrict the query if the user logged into the system is a standard base employee.
            // If an Admin/Manager is viewing, do NOT overwrite the employeeId parameter!
            if ("Employee".equalsIgnoreCase(currentRole) || "Standard_Employee".equalsIgnoreCase(currentRole)) {
                employeeId = currentUserId;
            }

            List<Evaluation> historyList = evalDAO.fetchHistoryByEmployee(employeeId);

            // Dynamically match your custom CellRenderer array index count
            String[] columns = {"Eval No.", "Evaluee", "Evaluator", "Score / Rating", "Remarks", "Evaluation Date"};
            Object[][] data = new Object[historyList.size()][6];

            for (int i = 0; i < historyList.size(); i++) {
                Evaluation eval = historyList.get(i);
                data[i][0] = eval.getId(); 
                data[i][1] = eval.getEvaluatorId() == currentUserId ? "You" : eval.getEvaluatorId(); 
                data[i][2] = eval.getPeriod(); 
                data[i][3] = eval.getScore() + " / 100"; 
                data[i][4] = eval.getFeedbackRemarks(); 
                data[i][5] = eval.getEvaluationDate().toString(); 
            }

            return createUneditableModel(data, columns);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading evaluation history: " + e.getMessage());
            return null;
        }
    }

    /**
     * Private helper to keep code DRY (Don't Repeat Yourself) 
     * by wrapping the non editable table structure rule in one place.
     */
    private DefaultTableModel createUneditableModel(Object[][] data, String[] columns) {
        return new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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

