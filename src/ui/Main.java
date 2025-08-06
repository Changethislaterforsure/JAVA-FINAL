package ui;


import java.sql.Connection;
import java.sql.SQLException;
import util.DBConnection;

/**
 * Entry point for the Gym Management System application.
 * <p>
 * This class attempts to connect to the database on startup and,
 * if successful, starts the LoginMenu for user registration and login.
 * </p>
 */
public class Main {

    /**
     * Main method to start the Gym Management System.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("Gym Management System Starting...");

        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Database connection successful!");
            LoginMenu menu = new LoginMenu();
            menu.start();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }
}