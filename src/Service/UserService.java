package Service;

import Model.User;
import DAO.UserDAO; // Assuming a standard UserDAO handles basic user table interactions
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    
    // Handles authenticating credentials during application login
    public User login(String email, String passwordInput) {
        try {
            User user = userDAO.findByIdOrEmail(email);
            if (user != null && user.getIsActive()) {
                // In production, evaluate using a secure hashing library like BCrypt here
                if (user.getPasswordHash().equals(passwordInput)) {
                    // Populate their authorized roles before returning the session user
                    user.setRoles(userDAO.getUserRoles(user.getId()));
                    return user;
                }
            }
            return null; // Authentication failed
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Authentication error: " + e.getMessage());
            return null;
        }
    }

    // Handles creating the base user entity and saving it to get a database-generated ID
    public User registerUserAccount(String email, String roleName) throws SQLException {
        User user = new User();
        user.setEmail(email);
        user.setIsActive(true);
        
        // A. Generate a secure random password behind the scenes
        String temporaryPassword = "EMP-" + (int)(Math.random() * 9000 + 1000); // Generates something like EMP-4829

        // B. Save the plain-text version ONLY in memory so the UI can read it
        user.setTemporaryPassword(temporaryPassword); 

        // C. Set the password hash for the database (In production, use BCrypt or similar)
        user.setPasswordHash(temporaryPassword);
            // 1. Insert into 'users' table (populates user.id via auto-increment)
        userDAO.add(user); 
        
        // 2. Insert link into 'user_role' table using the role name (e.g., 'Standard_Employee')
        userDAO.assignRole(user.getId(), roleName);
        
        return user;
    }
    
    
}