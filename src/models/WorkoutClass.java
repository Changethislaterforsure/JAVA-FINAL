package models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a workout class offered at the gym.
 */
public class WorkoutClass {
    private int classId;
    private String className;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private int trainerId;

    /**
     * Constructs a WorkoutClass with all fields.
     *
     * @param classId     The class ID.
     * @param className   The name of the workout class.
     * @param description A brief description of the class.
     * @param date        The scheduled date of the class.
     * @param time        The scheduled time of the class.
     * @param trainerId   The ID of the trainer running the class.
     */
    public WorkoutClass(int classId, String className, String description, LocalDate date, LocalTime time, int trainerId) {
        this.classId = classId;
        this.className = className;
        this.description = description;
        this.date = date;
        this.time = time;
        this.trainerId = trainerId;
    }

    // --- Getters ---

    public int getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getTrainerId() {
        return trainerId;
    }

    // --- Setters ---

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public String toString() {
        return "WorkoutClass ID: " + classId +
               ", Name: " + className +
               ", Description: " + description +
               ", Date: " + date +
               ", Time: " + time +
               ", Trainer ID: " + trainerId;
    }
}