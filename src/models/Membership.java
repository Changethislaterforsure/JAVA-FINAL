package models;

import java.math.BigDecimal;

/**
 * Represents a gym membership purchased by a member.
 */
public class Membership {
    private int membershipId;
    private String membershipType;
    private String description;
    private BigDecimal cost;
    private int memberId;

    /**
     * Constructs a new Membership object.
     *
     * @param membershipId   The ID of the membership.
     * @param membershipType The type of membership (e.g., Monthly, Annual).
     * @param description    A description of the membership.
     * @param cost           The cost of the membership.
     * @param memberId       The ID of the member who owns this membership.
     */
    public Membership(int membershipId, String membershipType, String description, BigDecimal cost, int memberId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.description = description;
        this.cost = cost;
        this.memberId = memberId;
    }

    // --- Getters ---

    /**
     * Gets the membership ID.
     *
     * @return The membership ID.
     */
    public int getMembershipId() {
        return membershipId;
    }

    /**
     * Gets the membership type.
     *
     * @return The membership type.
     */
    public String getMembershipType() {
        return membershipType;
    }

    /**
     * Gets the membership description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the membership cost.
     *
     * @return The cost.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Gets the member ID associated with this membership.
     *
     * @return The member ID.
     */
    public int getMemberId() {
        return memberId;
    }

    // --- Setters ---

    /**
     * Sets the membership type.
     *
     * @param membershipType The new membership type.
     */
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    /**
     * Sets the description of the membership.
     *
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the cost of the membership.
     *
     * @param cost The new cost.
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Sets the member ID for this membership.
     *
     * @param memberId The new member ID.
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Membership ID: " + membershipId +
               ", Type: " + membershipType +
               ", Description: " + description +
               ", Cost: $" + cost +
               ", Member ID: " + memberId;
    }
}