package models;

/**
 * Represents an Admin user in the gym management system.
 * Admins can manage users, view revenue, and handle merchandise.
 */
public class Admin extends User {

    /**
     * Constructs a new Admin user.
     *
     * @param userId        The unique ID of the admin.
     * @param username      The admin's username.
     * @param passwordHash  The admin's hashed password.
     * @param email         The admin's email address.
     * @param phoneNumber   The admin's phone number.
     * @param address       The admin's address.
     */
    public Admin(int userId, String username, String passwordHash, String email, String phoneNumber, String address) {
        super(userId, username, passwordHash, email, phoneNumber, address, "Admin");
    }

    /**
     * Displays the role-specific menu for an admin.
     * This method should be expanded later to handle admin input.
     */
    @Override
    public void displayMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View All Users");
        System.out.println("2. Delete User");
        System.out.println("3. View All Memberships");
        System.out.println("4. View Total Revenue");
        System.out.println("5. Manage Gym Merchandise");
        System.out.println("6. Logout");
        System.out.print("Select an option: ");
    }
}