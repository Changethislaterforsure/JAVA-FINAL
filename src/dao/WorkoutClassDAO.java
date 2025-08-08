package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.WorkoutClass;
import util.DBConnection;

/**
 * Data Access Object for managing WorkoutClass-related database operations.
 */
public class WorkoutClassDAO {

    /**
     * Adds a new workout class to the database.
     *
     * @param workoutClass The WorkoutClass object to add.
     * @return true if the class was added successfully, false otherwise.
     */
    public boolean addWorkoutClass(WorkoutClass workoutClass) {
        String sql = "INSERT INTO workout_classes (class_name, description, date, time, trainer_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, workoutClass.getClassName());
            stmt.setString(2, workoutClass.getDescription());
            stmt.setDate(3, Date.valueOf(workoutClass.getDate()));
            stmt.setTime(4, Time.valueOf(workoutClass.getTime()));
            stmt.setInt(5, workoutClass.getTrainerId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding workout class: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all workout classes from the database.
     *
     * @return A list of WorkoutClass objects.
     */
    public List<WorkoutClass> getAllWorkoutClasses() {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                    rs.getInt("class_id"),
                    rs.getString("class_name"),
                    rs.getString("description"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime(),
                    rs.getInt("trainer_id")
                );
                classes.add(workoutClass);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving workout classes: " + e.getMessage());
        }

        return classes;
    }

    /**
     * Deletes a workout class by its ID.
     *
     * @param classId The ID of the workout class to delete.
     * @return true if the class was deleted successfully, false otherwise.
     */
    public boolean deleteWorkoutClassById(int classId) {
        String sql = "DELETE FROM workout_classes WHERE class_id = ?";

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
     * Retrieves all workout classes for a specific trainer.
     *
     * @param trainerId The trainer ID to filter by.
     * @return A list of WorkoutClass objects for that trainer.
     */
    public List<WorkoutClass> getClassesByTrainerId(int trainerId) {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, trainerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                    rs.getInt("class_id"),
                    rs.getString("class_name"),
                    rs.getString("description"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime(),
                    rs.getInt("trainer_id")
                );
                classes.add(workoutClass);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving classes by trainer ID: " + e.getMessage());
        }

        return classes;
    }

    public List<WorkoutClass> getWorkoutClassesByTrainer(int trainerId) {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, trainerId);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                WorkoutClass wc = new WorkoutClass(
                    rs.getInt("class_id"),
                    rs.getString("class_type"),
                    rs.getString("description"),
                    rs.getDate("class_date").toLocalDate(),
                    rs.getTime("class_time").toLocalTime(),
                    rs.getInt("trainer_id")
                );
                classes.add(wc);
            }
    
        } catch (SQLException e) {
            System.err.println("Error retrieving classes: " + e.getMessage());
        }
    
        return classes;
    }

    public boolean deleteWorkoutClass(int classId) {
        String sql = "DELETE FROM workout_classes WHERE class_id = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, classId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting workout class: " + e.getMessage());
            return false;
        }
    }
}