package models;

/**
 * Represents a Trainer user in the gym management system.
 * Trainers can manage workout classes and view products.
 */
public class Trainer extends User {

    /**
     * Constructs a new Trainer user.
     *
     * @param userId        The unique ID of the trainer.
     * @param username      The trainer's username.
     * @param passwordHash  The trainer's hashed password.
     * @param email         The trainer's email address.
     * @param phoneNumber   The trainer's phone number.
     * @param address       The trainer's address.
     */
    public Trainer(int userId, String username, String passwordHash, String email, String phoneNumber, String address) {
        super(userId, username, passwordHash, email, phoneNumber, address, "Trainer");
    }

    /**
     * Displays the role-specific menu for a trainer.
     */
    @Override
    public void displayMenu() {
        System.out.println("\n=== Trainer Menu ===");
        System.out.println("1. Create Workout Class");
        System.out.println("2. Update Workout Class");
        System.out.println("3. Delete Workout Class");
        System.out.println("4. View My Classes");
        System.out.println("5. View Products");
        System.out.println("6. Logout");
        System.out.print("Select an option: ");
    }
}