package ui;

import dao.WorkoutClassDAO;
import java.util.List;
import java.util.Scanner;
import models.Trainer;
import models.WorkoutClass;

/**
 * Handles the user interface for trainer-specific operations.
 */
public class TrainerMenu {

    private final Trainer trainer;
    private final WorkoutClassDAO classDAO;
    private final Scanner scanner;

    /**
     * Constructs a TrainerMenu for the given trainer.
     *
     * @param trainer The Trainer object representing the logged-in trainer.
     */
    public TrainerMenu(Trainer trainer) {
        this.trainer = trainer;
        this.classDAO = new WorkoutClassDAO();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the trainer menu and displays available actions.
     */
    public void start() {
        int choice;

        do {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. View My Workout Classes");
            System.out.println("2. Add New Workout Class");
            System.out.println("3. Delete Workout Class");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewWorkoutClasses();
                    break;
                case 2:
                    addWorkoutClass();
                    break;
                case 3:
                    deleteWorkoutClass();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    /**
     * Displays all workout classes assigned to the current trainer.
     */
    private void viewWorkoutClasses() {
        List<WorkoutClass> classes = classDAO.getWorkoutClassesByTrainer(trainer.getUserId());

        if (classes.isEmpty()) {
            System.out.println("You have no workout classes assigned.");
        } else {
            System.out.println("--- Your Workout Classes ---");
            for (WorkoutClass wc : classes) {
                System.out.println(wc);
            }
        }
    }

    /**
     * Prompts the trainer to enter new workout class details and saves it.
     */
    private void addWorkoutClass() {
        System.out.print("Enter class type: ");
        String type = scanner.nextLine();

        System.out.print("Enter class description: ");
        String desc = scanner.nextLine();

        // Optionally prompt for date and time later
        WorkoutClass newClass = new WorkoutClass(0, type, desc, null, null, trainer.getUserId());

        if (classDAO.addWorkoutClass(newClass)) {
            System.out.println("Workout class added successfully.");
        } else {
            System.out.println("Failed to add workout class.");
        }
    }

    /**
     * Prompts the trainer to delete a workout class by its ID.
     */
    private void deleteWorkoutClass() {
        System.out.print("Enter the ID of the class to delete: ");
        int classId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (classDAO.deleteWorkoutClass(classId)) {
            System.out.println("Class deleted successfully.");
        } else {
            System.out.println("Failed to delete class.");
        }
    }
}