package models;

import java.math.BigDecimal;

/**
 * Represents a merchandise item sold at the gym.
 */
public class Merchandise {
    private int itemId;
    private String itemName;
    private String description;
    private BigDecimal price;

    /**
     * Constructs a Merchandise object with all fields.
     *
     * @param itemId      The ID of the merchandise item.
     * @param itemName    The name of the item.
     * @param description A brief description of the item.
     * @param price       The item's price.
     */
    public Merchandise(int itemId, String itemName, String description, BigDecimal price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }

    // --- Getters ---

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    // --- Setters ---

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId +
               ", Name: " + itemName +
               ", Description: " + description +
               ", Price: $" + price;
    }
}