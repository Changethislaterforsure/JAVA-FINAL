package dao;

import models.Membership;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for managing Membership database operations.
 */
public class MembershipDAO {

    /**
     * Adds a new membership to the database.
     *
     * @param membership The membership to add.
     * @return true if the membership was added successfully, false otherwise.
     */
    public boolean addMembership(Membership membership) {
        String sql = "INSERT INTO memberships (membership_type, membership_description, membership_cost, member_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membership.getMembershipType());
            stmt.setString(2, membership.getMembershipDescription());
            stmt.setBigDecimal(3, membership.getMembershipCost());
            stmt.setInt(4, membership.getMemberId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding membership: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all memberships from the database.
     *
     * @return A list of all memberships.
     */
    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                memberships.add(new Membership(
                        rs.getInt("membership_id"),
                        rs.getString("membership_type"),
                        rs.getString("membership_description"),
                        rs.getBigDecimal("membership_cost"),
                        rs.getInt("member_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving memberships: " + e.getMessage());
        }
        return memberships;
    }

    /**
     * Calculates the total revenue from all memberships.
     *
     * @return The total revenue as a BigDecimal, or null if an error occurs.
     */
    public java.math.BigDecimal getTotalRevenue() {
        String sql = "SELECT SUM(membership_cost) AS total_revenue FROM memberships";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getBigDecimal("total_revenue");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total revenue: " + e.getMessage());
        }
        return null;
    }
}