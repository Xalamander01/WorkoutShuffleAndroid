package Muscles;

public class MuscleGroup {

    private String majorMuscleGroup;
    private String subMuscleGroup;

    public MuscleGroup() {
    }

    public String getMajorMuscleGroup() { return majorMuscleGroup; }
    public void setMajorMuscleGroup(String majorMuscleGroup) { this.majorMuscleGroup = majorMuscleGroup; }

    public String getSubMuscleGroup() { return subMuscleGroup; }
    public void setSubMuscleGroup(String subMuscleGroup) { this.subMuscleGroup = subMuscleGroup; }

    public void printMuscleGroup(String prefix) {
        System.out.println( prefix + "Muscle Group: " + this.subMuscleGroup + " " + this.majorMuscleGroup);
    }
}
