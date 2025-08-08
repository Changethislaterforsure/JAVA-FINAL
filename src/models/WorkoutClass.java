package models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a workout class in the gym.
 */
public class WorkoutClass {
    private int classId;
    private String className;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private int trainerId;

    /**
     * Constructs a new WorkoutClass with all fields.
     *
     * @param classId    The unique ID of the class.
     * @param className  The name/type of the workout class.
     * @param description A brief description of the class.
     * @param date       The date of the class.
     * @param time       The time the class takes place.
     * @param trainerId  The ID of the trainer conducting the class.
     */
    public WorkoutClass(int classId, String className, String description, LocalDate date, LocalTime time, int trainerId) {
        this.classId = classId;
        this.className = className;
        this.description = description;
        this.date = date;
        this.time = time;
        this.trainerId = trainerId;
    }

    // ==== Getters ====

    /**
     * @return The class ID.
     */
    public int getClassId() {
        return classId;
    }

    /**
     * @return The name/type of the workout class.
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return The description of the class.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The date of the class.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return The time of the class.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * @return The trainer's ID.
     */
    public int getTrainerId() {
        return trainerId;
    }
}