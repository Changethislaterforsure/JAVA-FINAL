package dao;

import models.User;
import models.Admin;
import models.Trainer;
import models.Member;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                switch (role.toLowerCase()) {
                    case "admin":
                        return new Admin(userId, username, passwordHash, email, phone, address);
                    case "trainer":
                        return new Trainer(userId, username, passwordHash, email, phone, address);
                    case "member":
                        return new Member(userId, username, passwordHash, email, phone, address);
                    default:
                        return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }
        return null;
    }
}