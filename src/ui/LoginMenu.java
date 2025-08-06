package ui;

import dao.UserDAO;
import models.Admin;
import models.Member;
import models.Trainer;
import models.User;

import java.util.Scanner;

/**
 * Handles user login and registration.
 */
public class LoginMenu {
    private final UserDAO userDAO;
    private final Scanner scanner;

    /**
     * Constructs a LoginMenu with a UserDAO.
     */
    public LoginMenu() {
        this.userDAO = new UserDAO();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the login menu loop.
     */
    public void start() {
        while (true) {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    /**
     * Handles the login process.
     */
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDAO.getUserByUsername(username);

        if (user != null && user.getPasswordHash().equals(password)) { // TODO: Replace with BCrypt check later
            System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
            user.displayMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    /**
     * Handles the registration process.
     */
    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine(); // TODO: Hash with BCrypt later

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        User newUser;
        switch (role.toLowerCase()) {
            case "admin":
                newUser = new Admin(0, username, password, email, phone, address);
                break;
            case "trainer":
                newUser = new Trainer(0, username, password, email, phone, address);
                break;
            case "member":
                newUser = new Member(0, username, password, email, phone, address);
                break;
            default:
                System.out.println("Invalid role.");
                return;
        }

        boolean success = userDAO.addUser(newUser);
        if (success) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed. Username might already exist.");
        }
    }
}