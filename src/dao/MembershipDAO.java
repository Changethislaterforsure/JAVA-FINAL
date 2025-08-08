package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Membership;
import util.DBConnection;

/**
 * Data Access Object for managing Membership-related database operations.
 */
public class MembershipDAO {

    /**
     * Adds a new membership record to the database.
     *
     * @param membership The Membership object to add.
     * @return true if the membership was successfully added, false otherwise.
     */
    public boolean addMembership(Membership membership) {
        String sql = "INSERT INTO memberships (membership_type, description, cost, member_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membership.getMembershipType());
            stmt.setString(2, membership.getDescription());
            stmt.setBigDecimal(3, membership.getCost());
            stmt.setInt(4, membership.getMemberId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding membership: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a list of all membership records in the database.
     *
     * @return A list of Membership objects.
     */
    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                memberships.add(new Membership(
                    rs.getInt("membership_id"),
                    rs.getString("membership_type"),
                    rs.getString("description"),
                    rs.getBigDecimal("cost"),
                    rs.getInt("member_id")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving memberships: " + e.getMessage());
        }

        return memberships;
    }

    /**
     * Deletes a membership record by its ID.
     *
     * @param membershipId The ID of the membership to delete.
     * @return true if the membership was successfully deleted, false otherwise.
     */
    public boolean deleteMembership(int membershipId) {
        String sql = "DELETE FROM memberships WHERE membership_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, membershipId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting membership: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all memberships assigned to a specific member.
     *
     * @param memberId The ID of the member.
     * @return A list of Membership objects for that member.
     */
    public List<Membership> getMembershipsByMemberId(int memberId) {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                memberships.add(new Membership(
                    rs.getInt("membership_id"),
                    rs.getString("membership_type"),
                    rs.getString("description"),
                    rs.getBigDecimal("cost"),
                    rs.getInt("member_id")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving memberships by member ID: " + e.getMessage());
        }

        return memberships;
    }
}