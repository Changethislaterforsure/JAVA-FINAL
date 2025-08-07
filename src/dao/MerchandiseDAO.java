package dao;

import models.Merchandise;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for managing gym merchandise in the database.
 */
public class MerchandiseDAO {

    /**
     * Adds a new merchandise item to the database.
     *
     * @param merch The merchandise item to add.
     * @return true if added successfully, false otherwise.
     */
    public boolean addMerchandise(Merchandise merch) {
        String sql = "INSERT INTO gym_merch (merch_name, merch_type, merch_price, quantity_in_stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, merch.getMerchName());
            stmt.setString(2, merch.getMerchType());
            stmt.setBigDecimal(3, merch.getMerchPrice());
            stmt.setInt(4, merch.getQuantityInStock());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding merchandise: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all merchandise items from the database.
     *
     * @return A list of all merchandise items.
     */
    public List<Merchandise> getAllMerchandise() {
        List<Merchandise> merchList = new ArrayList<>();
        String sql = "SELECT * FROM gym_merch";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                merchList.add(new Merchandise(
                        rs.getInt("merch_id"),
                        rs.getString("merch_name"),
                        rs.getString("merch_type"),
                        rs.getBigDecimal("merch_price"),
                        rs.getInt("quantity_in_stock")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving merchandise: " + e.getMessage());
        }

        return merchList;
    }

    /**
     * Calculates the total value of all merchandise in stock.
     *
     * @return The total value as a BigDecimal, or null if an error occurs.
     */
    public java.math.BigDecimal getTotalMerchValue() {
        String sql = "SELECT SUM(merch_price * quantity_in_stock) AS total_value FROM gym_merch";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getBigDecimal("total_value");
            }

        } catch (SQLException e) {
            System.err.println("Error calculating total merchandise value: " + e.getMessage());
        }

        return null;
    }
}