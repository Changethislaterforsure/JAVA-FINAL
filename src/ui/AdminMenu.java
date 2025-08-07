package ui;

import dao.MembershipDAO;
import dao.MerchandiseDAO;
import dao.UserDAO;
import models.Admin;
import models.Merchandise;
import models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Console menu for Admin users. Allows managing users and viewing gym stats.
 */
public class AdminMenu {
    private final Admin admin;
    private final Scanner scanner;
    private final UserDAO userDAO;
    private final MembershipDAO membershipDAO;
    private final MerchandiseDAO merchandiseDAO;

    /**
     * Constructs the AdminMenu with the logged-in admin.
     *
     * @param admin The currently logged-in admin user.
     */
    public AdminMenu(Admin admin) {
        this.admin = admin;
        this.scanner = new Scanner(System.in);
        this.userDAO = new UserDAO();
        this.membershipDAO = new MembershipDAO();
        this.merchandiseDAO = new MerchandiseDAO();
    }

    /**
     * Starts the Admin menu loop.
     */
    public void start() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View All Users");
            System.out.println("2. Delete User");
            System.out.println("3. View Membership Revenue");
            System.out.println("4. View Total Merchandise Value");
            System.out.println("5. View All Merchandise");
            System.out.println("6. Add New Merchandise");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewAllUsers();
                    break;
                case "2":
                    deleteUser();
                    break;
                case "3":
                    viewMembershipRevenue();
                    break;
                case "4":
                    viewMerchandiseValue();
                    break;
                case "5":
                    viewAllMerchandise();
                    break;
                case "6":
                    addMerchandise();
                    break;
                case "7":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays all users in the system.
     */
    private void viewAllUsers() {
        List<User> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        System.out.println("\n--- All Users ---");
        for (User u : users) {
            System.out.println(u);
        }
    }

    /**
     * Deletes a user by username.
     */
    private void deleteUser() {
        System.out.print("Enter the username of the user to delete: ");
        String username = scanner.nextLine().trim();

        if (userDAO.deleteUserByUsername(username)) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User could not be deleted (not found or error occurred).");
        }
    }

    /**
     * Displays total revenue from all memberships.
     */
    private void viewMembershipRevenue() {
        BigDecimal revenue = membershipDAO.getTotalRevenue();
        if (revenue != null) {
            System.out.println("Total Membership Revenue: $" + revenue);
        } else {
            System.out.println("Failed to retrieve revenue.");
        }
    }

    /**
     * Displays total value of all merchandise in stock.
     */
    private void viewMerchandiseValue() {
        BigDecimal value = merchandiseDAO.getTotalMerchValue();
        if (value != null) {
            System.out.println("Total Merchandise Stock Value: $" + value);
        } else {
            System.out.println("Failed to retrieve merchandise value.");
        }
    }

    /**
     * Lists all merchandise items.
     */
    private void viewAllMerchandise() {
        List<Merchandise> items = merchandiseDAO.getAllMerchandise();
        if (items.isEmpty()) {
            System.out.println("No merchandise in stock.");
            return;
        }

        System.out.println("\n--- Merchandise List ---");
        for (Merchandise m : items) {
            System.out.println(m);
        }
    }

    /**
     * Prompts admin to enter and add new merchandise to stock.
     */
    private void addMerchandise() {
        try {
            System.out.print("Enter merchandise name: ");
            String name = scanner.nextLine();

            System.out.print("Enter merchandise type: ");
            String type = scanner.nextLine();

            System.out.print("Enter price: ");
            BigDecimal price = new BigDecimal(scanner.nextLine());

            System.out.print("Enter quantity in stock: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            Merchandise merch = new Merchandise(0, name, type, price, quantity);

            if (merchandiseDAO.addMerchandise(merch)) {
                System.out.println("Merchandise added.");
            } else {
                System.out.println("Failed to add merchandise.");
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        }
    }
}