package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import models.WorkoutClass;
import util.DBConnection;


public class WorkoutClassDAO {

    /**
     * Inserts a new workout class.
     *
     * @param workoutClass The class to insert.
     * @return true if one or more rows were inserted, false otherwise.
     */
    public boolean addWorkoutClass(WorkoutClass workoutClass) {
        final String sql = "INSERT INTO workout_classes " +
                "(class_name, description, date, time, trainer_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, workoutClass.getClassName());
            ps.setString(2, workoutClass.getDescription());
            // date and time are allowed to be null in the schema; handle gracefully
            if (workoutClass.getDate() != null) {
                ps.setDate(3, Date.valueOf(workoutClass.getDate()));
            } else {
                ps.setDate(3, null);
            }
            if (workoutClass.getTime() != null) {
                ps.setTime(4, Time.valueOf(workoutClass.getTime()));
            } else {
                ps.setTime(4, null);
            }
            ps.setInt(5, workoutClass.getTrainerId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding workout class: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all workout classes.
     *
     * @return List of all classes, or empty list on error.
     */
    public List<WorkoutClass> getAllWorkoutClasses() {
        final String sql = "SELECT class_id, class_name, description, date, time, trainer_id " +
                           "FROM workout_classes ORDER BY date NULLS LAST, time NULLS LAST, class_id";
        List<WorkoutClass> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving workout classes: " + e.getMessage());
        }

        return list;
    }

    /**
     * Retrieves all classes for a specific trainer.
     *
     * @param trainerId The trainer user_id.
     * @return List of classes for that trainer, or empty list on error.
     */
    public List<WorkoutClass> getWorkoutClassesByTrainer(int trainerId) {
        final String sql = "SELECT class_id, class_name, description, date, time, trainer_id " +
                           "FROM workout_classes WHERE trainer_id = ? " +
                           "ORDER BY date NULLS LAST, time NULLS LAST, class_id";
        List<WorkoutClass> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, trainerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving classes by trainer: " + e.getMessage());
        }

        return list;
    }

    /**
     * Deletes a class by its id.
     *
     * @param classId The class id.
     * @return true if a row was deleted, false otherwise.
     */
    public boolean deleteWorkoutClass(int classId) {
        final String sql = "DELETE FROM workout_classes WHERE class_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, classId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting workout class: " + e.getMessage());
            return false;
        }
    }

    /**
     * Maps the current ResultSet row to a WorkoutClass model.
     *
     * @param rs ResultSet positioned at a row.
     * @return WorkoutClass built from columns.
     * @throws SQLException If column access fails.
     */
    private WorkoutClass mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("class_id");
        String name = rs.getString("class_name");
        String description = rs.getString("description");

        LocalDate date = null;
        LocalTime time = null;

        Date sqlDate = rs.getDate("date");
        if (sqlDate != null) {
            date = sqlDate.toLocalDate();
        }

        Time sqlTime = rs.getTime("time");
        if (sqlTime != null) {
            time = sqlTime.toLocalTime();
        }

        int trainerId = rs.getInt("trainer_id");

        return new WorkoutClass(id, name, description, date, time, trainerId);
    }
}
