package ui;

import dao.WorkoutClassDAO;
import models.Trainer;
import models.WorkoutClass;

import java.util.List;
import java.util.Scanner;

/**
 * Console menu for Trainer users. Allows managing assigned workout classes.
 */
public class TrainerMenu {
    private final Trainer trainer;
    private final WorkoutClassDAO classDAO;
    private final Scanner scanner;

    /**
     * Constructs the TrainerMenu with the logged-in trainer.
     *
     * @param trainer The currently logged-in trainer user.
     */
    public TrainerMenu(Trainer trainer) {
        this.trainer = trainer;
        this.classDAO = new WorkoutClassDAO();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the Trainer menu loop.
     */
    public void start() {
        while (true) {
            System.out.println("\n=== Trainer Menu ===");
            System.out.println("1. View My Workout Classes");
            System.out.println("2. Add New Workout Class");
            System.out.println("3. Delete Workout Class");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewMyClasses();
                    break;
                case "2":
                    addWorkoutClass();
                    break;
                case "3":
                    deleteWorkoutClass();
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
     * Displays all classes assigned to the trainer.
     */
    private void viewMyClasses() {
        List<WorkoutClass> classes = classDAO.getWorkoutClassesByTrainer(trainer.getUserId());
        if (classes.isEmpty()) {
            System.out.println("You have no assigned classes.");
            return;
        }

        System.out.println("\n--- Your Workout Classes ---");
        for (WorkoutClass wc : classes) {
            System.out.println(wc);
        }
    }

    /**
     * Adds a new workout class under the trainer's name.
     */
    private void addWorkoutClass() {
        System.out.print("Enter class type (e.g., Yoga, HIIT): ");
        String type = scanner.nextLine();

        System.out.print("Enter class description: ");
        String desc = scanner.nextLine();

        WorkoutClass newClass = new WorkoutClass(0, type, desc, trainer.getUserId());

        if (classDAO.addWorkoutClass(newClass)) {
            System.out.println("Workout class added.");
        } else {
            System.out.println("Failed to add class.");
        }
    }

    /**
     * Deletes one of the trainer's workout classes by ID.
     */
    private void deleteWorkoutClass() {
        viewMyClasses();  // Show available classes

        System.out.print("Enter class ID to delete: ");
        try {
            int classId = Integer.parseInt(scanner.nextLine());
            if (classDAO.deleteWorkoutClass(classId)) {
                System.out.println("Workout class deleted.");
            } else {
                System.out.println("Could not delete class (invalid ID or error).");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }
}