package Exercises;

import Muscles.Muscle;

public class Exercise {
    private String name;
    private String movementType;
    private String lightOrHeavy;
    private Muscle muscle;

    public Exercise() {
        String name = "";
        String movementType = "";
        String lightOrHeavy = "";
        Muscle muscle = new Muscle() ;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMovementType() { return movementType; }
    public void setMovementType(String movementType) { this.movementType = movementType; }

    public String getLightOrHeavy() { return lightOrHeavy; }
    public void setLightOrHeavy(String lightOrHeavy) { this.lightOrHeavy = lightOrHeavy; }

    public Muscle getMuscle() { return muscle; }
    public void setMuscle(Muscle muscle) { this.muscle = muscle; }
}
