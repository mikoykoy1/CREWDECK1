package Service;

import Model.User;

public class UserSession {
    private static UserSession instance;
    private User loggedInUser;
    private String role; // e.g., "HR_Admin", "Standard_Employee"

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void login(User user, String role) {
        this.loggedInUser = user;
        this.role = role;
    }

    public void logout() {
        this.loggedInUser = null;
        this.role = null;
    }

    public User getLoggedInUser() { return loggedInUser; }
    public String getRole() { return role; }
    public boolean isLoggedIn() { return loggedInUser != null; }
}