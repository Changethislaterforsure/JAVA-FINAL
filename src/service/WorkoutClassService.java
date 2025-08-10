package service;

import dao.WorkoutClassDAO;
import models.WorkoutClass;

import java.util.List;

/**
 * Business logic for workout classes.
 */
public class WorkoutClassService {
    private final WorkoutClassDAO workoutClassDAO = new WorkoutClassDAO();

    /**
     * Creates a workout class.
     *
     * @param wc WorkoutClass to add.
     * @return true if created, false otherwise.
     */
    public boolean create(WorkoutClass wc) {
        return workoutClassDAO.addWorkoutClass(wc);
    }

    /**
     * Deletes a workout class by id.
     *
     * @param classId Class id.
     * @return true if deleted, false otherwise.
     */
    public boolean delete(int classId) {
        return workoutClassDAO.deleteWorkoutClass(classId);
    }

    /**
     * Lists all classes.
     *
     * @return List of workout classes.
     */
    public List<WorkoutClass> listAll() {
        return workoutClassDAO.getAllWorkoutClasses();
    }

    /**
     * Lists classes for a trainer.
     *
     * @param trainerId Trainer id.
     * @return List of classes for the trainer.
     */
    public List<WorkoutClass> listForTrainer(int trainerId) {
        return workoutClassDAO.getWorkoutClassesByTrainer(trainerId);
    }
}