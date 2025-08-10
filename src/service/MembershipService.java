package service;

import dao.MembershipDAO;
import models.Membership;

import java.math.BigDecimal;
import java.util.List;

/**
 * Business logic for memberships.
 */
public class MembershipService {
    private final MembershipDAO membershipDAO = new MembershipDAO();

    /**
     * Purchases a membership for a member.
     *
     * @param membership Membership to create.
     * @return true if created, false otherwise.
     */
    public boolean purchase(Membership membership) {
        return membershipDAO.addMembership(membership);
    }

    /**
     * Lists all memberships in the system.
     *
     * @return List of memberships.
     */
    public List<Membership> listAll() {
        return membershipDAO.getAllMemberships();
    }

    /**
     * Lists memberships for a specific member.
     *
     * @param memberId Member id.
     * @return List of memberships for that member.
     */
    public List<Membership> listForMember(int memberId) {
        return membershipDAO.getMembershipsByMemberId(memberId);
    }

    /**
     * Deletes a membership by id.
     *
     * @param membershipId Membership id.
     * @return true if deleted, false otherwise.
     */
    public boolean delete(int membershipId) {
        return membershipDAO.deleteMembership(membershipId);
    }

    /**
     * Total revenue across memberships.
     *
     * @return Sum of cost field or null on error.
     */
    public BigDecimal totalRevenue() {
        return membershipDAO.getTotalRevenue();
    }
}