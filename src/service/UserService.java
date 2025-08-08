package service;

import dao.UserDAO;
import models.User;
import models.Admin;
import models.Member;
import models.Trainer;

/**
 * Provides user-related business logic and interaction with the UserDAO.
 */
public class UserService {

    private UserDAO userDAO;

    /**
     * Constructs a UserService with a new UserDAO instance.
     */
    public UserService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Registers a new user if the username does not already exist.
     *
     * @param user The user to register.
     * @return true if the user was added successfully, false if the username already exists or the operation failed.
     */
    public boolean registerUser(User user) {
        User existing = userDAO.getUserByUsername(user.getUsername());
        if (existing != null) {
            System.out.println("Username already taken. Please choose another.");
            return false;
        }

        return userDAO.addUser(user);
    }

    /**
     * Attempts to log in a user by username and password.
     *
     * @param username The username.
     * @param password The raw password to validate.
     * @return The User object if login is successful, null otherwise.
     */
    public User loginUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);

        if (user != null && org.mindrot.jbcrypt.BCrypt.checkpw(password, user.getPasswordHash())) {
            return user;
        }

        return null;
    }

    /**
     * Returns the appropriate role label based on a User instance.
     *
     * @param user The user to check.
     * @return A role label string: "Admin", "Trainer", "Member", or "Unknown".
     */
    public String getRoleLabel(User user) {
        if (user instanceof Admin) {
            return "Admin";
        } else if (user instanceof Trainer) {
            return "Trainer";
        } else if (user instanceof Member) {
            return "Member";
        } else {
            return "Unknown";
        }
    }
}