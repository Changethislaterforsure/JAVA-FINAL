package models;

import java.math.BigDecimal;

/**
 * Represents an item of gym merchandise.
 */
public class Merchandise {
    private int merchId;
    private String merchName;
    private String merchType;
    private BigDecimal merchPrice;
    private int quantityInStock;

    public Merchandise(int merchId, String merchName, String merchType, BigDecimal merchPrice, int quantityInStock) {
        this.merchId = merchId;
        this.merchName = merchName;
        this.merchType = merchType;
        this.merchPrice = merchPrice;
        this.quantityInStock = quantityInStock;
    }

    public int getMerchId() {
        return merchId;
    }

    public String getMerchName() {
        return merchName;
    }

    public String getMerchType() {
        return merchType;
    }

    public BigDecimal getMerchPrice() {
        return merchPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setMerchId(int merchId) {
        this.merchId = merchId;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public void setMerchType(String merchType) {
        this.merchType = merchType;
    }

    public void setMerchPrice(BigDecimal merchPrice) {
        this.merchPrice = merchPrice;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Merchandise{" +
                "ID=" + merchId +
                ", Name='" + merchName + '\'' +
                ", Type='" + merchType + '\'' +
                ", Price=" + merchPrice +
                ", Quantity=" + quantityInStock +
                '}';
    }
}