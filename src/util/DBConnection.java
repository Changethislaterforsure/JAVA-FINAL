package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing the PostgreSQL database connection.
 */
public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/gymdb";
    private static final String USER = "keyin";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    /**
     * Returns a singleton database connection instance.
     *
     * @return A Connection object to the PostgreSQL database.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    /**
     * Closes the existing database connection if open.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}