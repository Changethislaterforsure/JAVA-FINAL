package dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Merchandise;
import util.DBConnection;

/**
 * Data Access Object for managing Merchandise-related database operations.
 */
public class MerchandiseDAO {

    /**
     * Adds a new merchandise item to the database.
     *
     * @param item The Merchandise object to add.
     * @return true if the item was successfully added, false otherwise.
     */
    public boolean addMerchandise(Merchandise item) {
        String sql = "INSERT INTO merchandise (item_name, description, price) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getItemName());
            stmt.setString(2, item.getDescription());
            stmt.setBigDecimal(3, item.getPrice());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding merchandise: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all merchandise items from the database.
     *
     * @return A list of Merchandise objects.
     */
    public List<Merchandise> getAllMerchandise() {
        List<Merchandise> items = new ArrayList<>();
        String sql = "SELECT * FROM merchandise";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Merchandise item = new Merchandise(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getString("description"),
                    rs.getBigDecimal("price"),
                    rs.getInt("quantity")
                );
                items.add(item);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving merchandise: " + e.getMessage());
        }

        return items;
    }

    /**
     * Deletes a merchandise item by its ID.
     *
     * @param itemId The ID of the merchandise item to delete.
     * @return true if the item was successfully deleted, false otherwise.
     */
    public boolean deleteMerchandiseById(int itemId) {
        String sql = "DELETE FROM merchandise WHERE item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting merchandise: " + e.getMessage());
            return false;
        }
    }

    /**
 * Calculates the total value of all merchandise (price × quantity).
 *
 * @return Total merchandise value as BigDecimal, or null if error occurs.
 */
public BigDecimal getTotalMerchValue() {
    String sql = "SELECT SUM(price * quantity) AS total FROM merchandise";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            return rs.getBigDecimal("total");
        }
    } catch (SQLException e) {
        System.err.println("Error calculating total merchandise value: " + e.getMessage());
    }
    return null;
}
}