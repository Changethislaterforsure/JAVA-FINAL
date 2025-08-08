package ui;

import dao.UserDAO;
import models.Admin;
import models.Member;
import models.Trainer;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

/**
 * Handles user login and registration for the Gym Management System.
 */
public class LoginMenu {
    private final UserDAO userDAO;
    private final Scanner scanner;

    /**
     * Constructs a LoginMenu instance.
     */
    public LoginMenu() {
        this.userDAO = new UserDAO();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the login/registration menu loop.
     */
    public void start() {
        while (true) {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

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
     * Handles user login.
     */
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return;
        }

        User user = userDAO.getUserByUsername(username);

        if (user instanceof Admin) {
            new AdminMenu((Admin) user).start();
        } else if (user instanceof Trainer) {
            new TrainerMenu((Trainer) user).start();
        } else if (user instanceof Member) {
            new MemberMenu((Member) user).start();
        } else {
            System.out.println("Unknown user role. Access denied.");
        }

        if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
            System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
            user.displayMenu(); // Later: Call role-specific menu here
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    /**
     * Handles user registration.
     */
    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String rawPassword = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine().trim();

        if (username.isEmpty() || rawPassword.isEmpty() || email.isEmpty() || role.isEmpty()) {
            System.out.println("Username, password, email, and role cannot be empty.");
            return;
        }

        String passwordHash = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        User newUser;
        switch (role.toLowerCase()) {
            case "admin":
                newUser = new Admin(0, username, passwordHash, email, phone, address);
                break;
            case "trainer":
                newUser = new Trainer(0, username, passwordHash, email, phone, address);
                break;
            case "member":
                newUser = new Member(0, username, passwordHash, email, phone, address);
                break;
            default:
                System.out.println("Invalid role. Please choose Admin, Trainer, or Member.");
                return;
        }

        boolean success = userDAO.addUser(newUser);
        if (success) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed. Username may already exist.");
        }
    }
}