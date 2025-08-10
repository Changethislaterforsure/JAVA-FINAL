package ui;

import dao.WorkoutClassDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import models.Trainer;
import models.WorkoutClass;
import util.LoggerUtil;

/**
 * Menu for trainer actions.
 */
public class TrainerMenu {

    private final Trainer trainer;
    private final WorkoutClassDAO classDAO;
    private final Scanner scanner;
    private static final Logger logger = LoggerUtil.getLogger();

    /**
     * Creates a menu instance for the given trainer.
     *
     * @param trainer The logged in trainer.
     */
    public TrainerMenu(Trainer trainer) {
        this.trainer = trainer;
        this.classDAO = new WorkoutClassDAO();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the trainer menu loop.
     */
    public void start() {
        while (true) {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. View My Workout Classes");
            System.out.println("2. Add New Workout Class");
            System.out.println("3. Delete Workout Class");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

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
                case "0":
                    System.out.println("Logging out...");
                    logger.info(String.format("Trainer '%s' logged out of TrainerMenu.", trainer.getUsername()));
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Shows all classes owned by this trainer.
     */
    private void viewMyClasses() {
        List<WorkoutClass> classes = classDAO.getWorkoutClassesByTrainer(trainer.getUserId());
        if (classes.isEmpty()) {
            System.out.println("You have no workout classes assigned.");
        } else {
            System.out.println("\n--- Your Workout Classes ---");
            for (WorkoutClass wc : classes) {
                System.out.printf(
                        "ID: %d | Name: %s | Description: %s | Date: %s | Time: %s%n",
                        wc.getClassId(), wc.getClassName(), wc.getDescription(),
                        wc.getDate(), wc.getTime()
                );
            }
        }
        logger.info(String.format("Trainer '%s' viewed their classes. Count=%d",
                trainer.getUsername(), classes.size()));
    }

    /**
     * Prompts for details and creates a new workout class.
     */
    private void addWorkoutClass() {
        System.out.print("Enter class name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter class description: ");
        String desc = scanner.nextLine().trim();

        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print("Enter class date (YYYY-MM-DD): ");
                date = LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        LocalTime time = null;
        while (time == null) {
            try {
                System.out.print("Enter class time (HH:MM): ");
                time = LocalTime.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please try again.");
            }
        }

        WorkoutClass newClass = new WorkoutClass(0, name, desc, date, time, trainer.getUserId());
        boolean ok = classDAO.addWorkoutClass(newClass);

        if (ok) {
            System.out.println("Workout class created.");
            logger.info(String.format(
                    "Trainer '%s' created class '%s' on %s at %s.",
                    trainer.getUsername(), name, date, time));
        } else {
            System.out.println("Failed to create workout class.");
            logger.warning(String.format(
                    "Trainer '%s' failed to create class '%s' on %s at %s.",
                    trainer.getUsername(), name, date, time));
        }
    }

    /**
     * Deletes a class by ID if it exists.
     */
    private void deleteWorkoutClass() {
        System.out.print("Enter the class ID to delete: ");
        String raw = scanner.nextLine().trim();

        try {
            int classId = Integer.parseInt(raw);
            boolean ok = classDAO.deleteWorkoutClass(classId);

            if (ok) {
                System.out.println("Class deleted.");
                logger.info(String.format(
                        "Trainer '%s' deleted class id %d.",
                        trainer.getUsername(), classId));
            } else {
                System.out.println("Could not delete class. Check the ID.");
                logger.warning(String.format(
                        "Trainer '%s' attempted to delete missing class id %d.",
                        trainer.getUsername(), classId));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            logger.warning(String.format(
                    "Trainer '%s' entered invalid class id '%s' for deletion.",
                    trainer.getUsername(), raw));
        }
    }
}