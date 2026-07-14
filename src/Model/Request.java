package Model;

import java.time.LocalDate;

public class Request {
    private int id;
    private int userId;              // The employee making the request
    private String requestType;      // e.g., "Leave", "Profile Correction", "Salary Inquiry"
    private String details;          // Message or explanation from the employee
    private LocalDate submissionDate;
    private String status;           // "Pending", "Approved", "Rejected"
    private String adminRemarks;     // Explanatory note left by the reviewing HR Admin

    // Constructors
    public Request() {}

    public Request(int id, int userId, String requestType, String details, LocalDate submissionDate, String status, String adminRemarks) {
        this.id = id;
        this.userId = userId;
        this.requestType = requestType;
        this.details = details;
        this.submissionDate = submissionDate;
        this.status = status;
        this.adminRemarks = adminRemarks;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getRequestType() { return requestType; }
    public void setRequestType(String requestType) { this.requestType = requestType; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public LocalDate getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDate submissionDate) { this.submissionDate = submissionDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAdminRemarks() { return adminRemarks; }
    public void setAdminRemarks(String adminRemarks) { this.adminRemarks = adminRemarks; }
}