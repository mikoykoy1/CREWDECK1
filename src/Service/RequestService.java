package Service;

import DAO.RequestDAO;
import Model.Request;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RequestService {
    private final RequestDAO requestDAO = new RequestDAO();

    // Validates inputs and lodges an employee's ticket request
    public boolean createEmployeeRequest(int employeeId, String type, String details) {
        if (type == null || type.trim().isEmpty() || details == null || details.trim().isEmpty()) {
            return false; // Rejects empty forms cleanly
        }

        try {
            Request req = new Request();
            req.setUserId(employeeId);
            req.setRequestType(type);
            req.setDetails(details);
            req.setSubmissionDate(LocalDate.now()); // Automatically stamp execution date
            req.setStatus("Pending"); // Always defaults to Pending state
            
            return requestDAO.add(req);
        } catch (SQLException ex) {
            System.err.println("Error saving employee request ticket: " + ex.getMessage());
            return false;
        }
    }

    // Safely reads the database pending array block for UI Table population
    public List<Request> getPendingReviewQueue() {
        try {
            return requestDAO.fetchAllPending();
        } catch (SQLException ex) {
            System.err.println("Error pulling application request queue: " + ex.getMessage());
            return new ArrayList<>(); // Prevent visual view screens from crashing
        }
    }

    // Standardizes the logic for HR resolution workflows
    public boolean updateRequestStatus(int requestId, boolean approve, String adminRemarks) {
        String finalStatus = approve ? "Approved" : "Rejected";
        String cleanedRemarks = (adminRemarks == null) ? "" : adminRemarks.trim();
        
        try {
            return requestDAO.processRequest(requestId, finalStatus, cleanedRemarks);
        } catch (SQLException ex) {
            System.err.println("Error documenting admin request decision: " + ex.getMessage());
            return false;
        }
    }
    
    
    public DefaultTableModel getTableModel() {
        // 1. Define the UI table columns
        String[] columns = {"Request ID", "Employee ID", "Request Type", "Status", "Date Submitted", "Action"};

        // 2. Create the non-editable model
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        // 3. Extract credentials via UserSession safely
        UserSession session = UserSession.getInstance();
        String currentRole = session.getRole();
        int currentUserId = session.getLoggedInUser().getId();

        List<Request> requests;
        try {
            // 4. Evaluate access rules
            if ("Admin".equalsIgnoreCase(currentRole) || "Manager".equalsIgnoreCase(currentRole)) {
                // Privileged view: load the absolute history table logs
                requests = requestDAO.getRequestsFiltered(0); 
            } else {
                // Employee view: lock rows tightly down to their session ID 
                requests = requestDAO.getRequestsFiltered(currentUserId);
            }
        } catch (SQLException ex) {
            System.err.println("Error binding table rows: " + ex.getMessage());
            requests = new ArrayList<>();
        }

        // 5. Populate the UI elements safely
        for (Request req : requests) {
            Object[] rowData = {
                req.getId(),                
                req.getUserId(),      
                req.getRequestType(),       
                req.getStatus(),            
                req.getSubmissionDate(),    
                "Review"                    
            };
            model.addRow(rowData);
        }

        return model;
    }
}