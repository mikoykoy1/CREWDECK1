package Service;


import DAO.EvaluationDAO;
import model.Evaluation;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
