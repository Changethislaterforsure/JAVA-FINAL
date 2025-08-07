package models;

import java.math.BigDecimal;

/**
 * Represents a gym membership.
 */
public class Membership {
    private int membershipId;
    private String membershipType;
    private String membershipDescription;
    private BigDecimal membershipCost;
    private int memberId;

    public Membership(int membershipId, String membershipType, String membershipDescription, BigDecimal membershipCost, int memberId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
    }

    public int getMembershipId() { return membershipId; }
    public String getMembershipType() { return membershipType; }
    public String getMembershipDescription() { return membershipDescription; }
    public BigDecimal getMembershipCost() { return membershipCost; }
    public int getMemberId() { return memberId; }

    public void setMembershipId(int membershipId) { this.membershipId = membershipId; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
    public void setMembershipDescription(String membershipDescription) { this.membershipDescription = membershipDescription; }
    public void setMembershipCost(BigDecimal membershipCost) { this.membershipCost = membershipCost; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    @Override
    public String toString() {
        return "Membership{" +
                "ID=" + membershipId +
                ", Type='" + membershipType + '\'' +
                ", Description='" + membershipDescription + '\'' +
                ", Cost=" + membershipCost +
                ", MemberID=" + memberId +
                '}';
    }
}