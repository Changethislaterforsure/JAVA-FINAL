package ui;

import util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Entry point for the Gym Management System application.
 */
public class Main {

    /**
     * Main method to start the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("Gym Management System Starting...");

        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Database connection successful!");
            DBConnection.closeConnection();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }

        // Future: Start LoginMenu here
    }
}