package models;

/**
 * Represents a Member user in the gym management system.
 * Members can browse classes, buy memberships, and view products.
 */
public class Member extends User {

    /**
     * Constructs a new Member user.
     *
     * @param userId        The unique ID of the member.
     * @param username      The member's username.
     * @param passwordHash  The member's hashed password.
     * @param email         The member's email address.
     * @param phoneNumber   The member's phone number.
     * @param address       The member's address.
     */
    public Member(int userId, String username, String passwordHash, String email, String phoneNumber, String address) {
        super(userId, username, passwordHash, email, phoneNumber, address, "Member");
    }

    /**
     * Displays the role-specific menu for a member.
     */
    @Override
    public void displayMenu() {
        System.out.println("\n=== Member Menu ===");
        System.out.println("1. Browse Workout Classes");
        System.out.println("2. View Membership Expenses");
        System.out.println("3. Purchase Membership");
        System.out.println("4. View Products");
        System.out.println("5. Logout");
        System.out.print("Select an option: ");
    }
}