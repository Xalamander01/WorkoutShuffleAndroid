package Muscles;

import java.util.List;

public class Muscle {

    private MuscleGroup mainMuscleGroup;
    private List<MuscleGroup> relatedMuscleGroups;

    public Muscle() {
    }

    public MuscleGroup getMainMuscleGroup() { return mainMuscleGroup; }
    public void setMainMuscleGroup(MuscleGroup mainMuscleGroup) { this.mainMuscleGroup = mainMuscleGroup; }

    public List<MuscleGroup> getRelatedMuscleGroups() { return relatedMuscleGroups; }
    public void setRelatedMuscleGroups(List<MuscleGroup> relatedMuscleGroups) { this.relatedMuscleGroups = relatedMuscleGroups; }

    public void printMuscleGroups() {
        this.getMainMuscleGroup().printMuscleGroup("Main ");
        for ( MuscleGroup relatedMuscleGroup : this.getRelatedMuscleGroups()) {
            relatedMuscleGroup.printMuscleGroup("Related ");
        }
    }
}
