package Model;

import java.time.LocalDate;

public class Evaluation {
    private int id;
    private int userId;          // The Employee being evaluated
    private int evaluatorId;     // The Admin/Manager conducting the review
    private LocalDate evaluationDate;
    private String period;      
    private int score;          // Numerical rating (e.g., 1-5)
    private String feedbackRemarks;

    // Constructors
    public Evaluation() {}

    public Evaluation(int id, int userId, int evaluatorId, LocalDate evaluationDate, String period, int score, String feedbackRemarks) {
        this.id = id;
        this.userId = userId;
        this.evaluatorId = evaluatorId;
        this.evaluationDate = evaluationDate;
        this.period = period;
        this.score = score;
        this.feedbackRemarks = feedbackRemarks;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getEvaluatorId() { return evaluatorId; }
    public void setEvaluatorId(int evaluatorId) { this.evaluatorId = evaluatorId; }

    public LocalDate getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(LocalDate evaluationDate) { this.evaluationDate = evaluationDate; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getFeedbackRemarks() { return feedbackRemarks; }
    public void setFeedbackRemarks(String feedbackRemarks) { this.feedbackRemarks = feedbackRemarks; }
}
