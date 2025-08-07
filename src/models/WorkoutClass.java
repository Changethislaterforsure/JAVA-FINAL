package models;

/**
 * Represents a workout class in the gym system.
 */
public class WorkoutClass {
    private int classId;
    private String classType;
    private String classDescription;
    private int trainerId;

    public WorkoutClass(int classId, String classType, String classDescription, int trainerId) {
        this.classId = classId;
        this.classType = classType;
        this.classDescription = classDescription;
        this.trainerId = trainerId;
    }

    public int getClassId() {
        return classId;
    }

    public String getClassType() {
        return classType;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public String toString() {
        return "WorkoutClass{" +
                "ID=" + classId +
                ", Type='" + classType + '\'' +
                ", Description='" + classDescription + '\'' +
                ", TrainerID=" + trainerId +
                '}';
    }
}