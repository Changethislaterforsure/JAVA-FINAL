package dao;

import models.WorkoutClass;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for managing workout class operations in the database.
 */
public class WorkoutClassDAO {

    /**
     * Adds a new workout class to the database.
     *
     * @param workoutClass The class to add.
     * @return true if successful, false otherwise.
     */
    public boolean addWorkoutClass(WorkoutClass workoutClass) {
        String sql = "INSERT INTO workout_classes (class_type, class_description, trainer_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, workoutClass.getClassType());
            stmt.setString(2, workoutClass.getClassDescription());
            stmt.setInt(3, workoutClass.getTrainerId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding workout class: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all workout classes from the database.
     *
     * @return A list of all workout classes.
     */
    public List<WorkoutClass> getAllWorkoutClasses() {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                classes.add(new WorkoutClass(
                        rs.getInt("workout_class_id"),
                        rs.getString("class_type"),
                        rs.getString("class_description"),
                        rs.getInt("trainer_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving workout classes: " + e.getMessage());
        }
        return classes;
    }

    /**
     * Deletes a workout class by ID.
     *
     * @param classId The ID of the class to delete.
     * @return true if deleted, false otherwise.
     */
    public boolean deleteWorkoutClass(int classId) {
        String sql = "DELETE FROM workout_classes WHERE workout_class_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting workout class: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all workout classes assigned to a specific trainer.
     *
     * @param trainerId The trainer's user ID.
     * @return A list of workout classes.
     */
    public List<WorkoutClass> getWorkoutClassesByTrainer(int trainerId) {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, trainerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                classes.add(new WorkoutClass(
                        rs.getInt("workout_class_id"),
                        rs.getString("class_type"),
                        rs.getString("class_description"),
                        rs.getInt("trainer_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving classes by trainer: " + e.getMessage());
        }

        return classes;
    }
}