package ui;

import dao.MembershipDAO;
import dao.MerchandiseDAO;
import dao.WorkoutClassDAO;
import models.Member;
import models.Membership;
import models.Merchandise;
import models.WorkoutClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Console menu for Member users. Allows viewing classes and purchasing memberships.
 */
public class MemberMenu {
    private final Member member;
    private final Scanner scanner;
    private final WorkoutClassDAO classDAO;
    private final MerchandiseDAO merchandiseDAO;
    private final MembershipDAO membershipDAO;

    /**
     * Constructs the MemberMenu with the logged-in member.
     *
     * @param member The currently logged-in member user.
     */
    public MemberMenu(Member member) {
        this.member = member;
        this.scanner = new Scanner(System.in);
        this.classDAO = new WorkoutClassDAO();
        this.merchandiseDAO = new MerchandiseDAO();
        this.membershipDAO = new MembershipDAO();
    }

    /**
     * Starts the Member menu loop.
     */
    public void start() {
        while (true) {
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. View Workout Classes");
            System.out.println("2. View Merchandise");
            System.out.println("3. Purchase Membership");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewWorkoutClasses();
                    break;
                case "2":
                    viewMerchandise();
                    break;
                case "3":
                    purchaseMembership();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays all available workout classes.
     */
    private void viewWorkoutClasses() {
        List<WorkoutClass> classes = classDAO.getAllWorkoutClasses();
        if (classes.isEmpty()) {
            System.out.println("No workout classes available.");
            return;
        }

        System.out.println("\n--- Available Workout Classes ---");
        for (WorkoutClass wc : classes) {
            System.out.println(wc);
        }
    }

    /**
     * Displays all available merchandise.
     */
    private void viewMerchandise() {
        List<Merchandise> merchList = merchandiseDAO.getAllMerchandise();
        if (merchList.isEmpty()) {
            System.out.println("No merchandise available.");
            return;
        }

        System.out.println("\n--- Gym Merchandise ---");
        for (Merchandise item : merchList) {
            System.out.println(item);
        }
    }

    /**
     * Allows a member to purchase a new membership.
     */
    private void purchaseMembership() {
        try {
            System.out.print("Enter membership type (e.g., Monthly, Annual): ");
            String type = scanner.nextLine();

            System.out.print("Enter membership description: ");
            String desc = scanner.nextLine();

            System.out.print("Enter cost: ");
            BigDecimal cost = new BigDecimal(scanner.nextLine());

            Membership newMembership = new Membership(0, type, desc, cost, member.getUserId());

            if (membershipDAO.addMembership(newMembership)) {
                System.out.println("Membership purchased successfully!");
            } else {
                System.out.println("Failed to purchase membership.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Membership not added.");
        }
    }
}