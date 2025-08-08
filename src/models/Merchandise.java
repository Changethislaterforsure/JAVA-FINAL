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
    private int quantity;

    /**
     * Constructs a Merchandise object with all fields.
     *
     * @param itemId      The ID of the merchandise item.
     * @param itemName    The name of the item.
     * @param description A brief description of the item.
     * @param price       The item's price.
     */
    public Merchandise(int id, String name, String type, BigDecimal price, int quantity) {
        this.itemId = id;
        this.itemName = name;
        this.description = type;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
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