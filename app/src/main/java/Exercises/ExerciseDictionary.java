package Exercises;

import java.util.HashMap;
import java.util.Map;

public class ExerciseDictionary {
    private HashMap<String, Exercise> exerciseDictionary;

    public ExerciseDictionary() {
        this.exerciseDictionary = new HashMap<>();
    }

    public HashMap<String, Exercise> getExerciseDictionary() {
        return exerciseDictionary;
    }

    public void setExerciseDictionary(HashMap<String, Exercise> exerciseDictionary) {
        this.exerciseDictionary = exerciseDictionary;
    }

    public void addExercise(String shortName, Exercise exercise) {
        this.exerciseDictionary.put(shortName, exercise);
    }

    public Exercise getExercise(String shortName) {
        return this.exerciseDictionary.get(shortName);
    }

    public String getShortName(Exercise exercise) {

        String shortNameToReturn = null;

        for ( Map.Entry<String,Exercise> entry : this.getExerciseDictionary().entrySet() ) {
            if ( entry.getValue().getName().equals(exercise.getName()) ) {
                shortNameToReturn = entry.getKey();
            }
        }
        return shortNameToReturn;
    }

    public void editExercise(String shortName, Exercise exercise) {
        this.exerciseDictionary.remove(shortName);
        this.exerciseDictionary.put(shortName, exercise);
    }

    public void deleteExercise(String shortName) {
        this.exerciseDictionary.remove(shortName);
    }
}
