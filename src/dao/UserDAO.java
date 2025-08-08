package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Admin;
import models.Member;
import models.Trainer;
import models.User;
import util.DBConnection;

/**
 * Data Access Object for managing User-related database operations.
 */
public class UserDAO {

    /**
     * Inserts a new user into the database.
     *
     * @param user The User object to insert.
     * @return true if the user was successfully added, false otherwise.
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, password_hash, email, phone_number, address, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getRole());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a user from the database by username.
     *
     * @param username The username to search for.
     * @return A User object if found, otherwise null.
     */
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                int userId = rs.getInt("user_id");
                String passwordHash = rs.getString("password_hash");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");

                return switch (role.toLowerCase()) {
                    case "admin" -> new Admin(userId, username, passwordHash, email, phone, address);
                    case "trainer" -> new Trainer(userId, username, passwordHash, email, phone, address);
                    case "member" -> new Member(userId, username, passwordHash, email, phone, address);
                    default -> null;
                };
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all User objects.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String role = rs.getString("role");
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String passwordHash = rs.getString("password_hash");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");

                User user = switch (role.toLowerCase()) {
                    case "admin" -> new Admin(userId, username, passwordHash, email, phone, address);
                    case "trainer" -> new Trainer(userId, username, passwordHash, email, phone, address);
                    case "member" -> new Member(userId, username, passwordHash, email, phone, address);
                    default -> null;
                };

                if (user != null) {
                    users.add(user);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving users: " + e.getMessage());
        }

        return users;
    }

    /**
     * Deletes a user from the database by their username.
     *
     * @param username The username of the user to delete.
     * @return true if the user was deleted, false otherwise.
     */
    public boolean deleteUserByUsername(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }
}