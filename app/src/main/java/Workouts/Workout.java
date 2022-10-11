package Workouts;

import Exercises.Exercise;
import Exercises.ExerciseDictionary;
import JSONHandler.JsonToPOJOConverter;
import JSONHandler.JsonWriter;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Workout {
    private List<Exercise> workout;
    private final String EXERCISE_DICTIONARY_JSON = "src/main/resources/ExerciseDictionary.json";
    private final String WORKOUT_PROPERTIES_JSON = "src/main/resources/WorkoutProperties.json";
    private final String EXERCISE_RECENCY_JSON = "src/main/resources/ExercisesAndRecency.json";

    public Workout() { List<Exercise> workout = new ArrayList<>(); }
    public List<Exercise> getWorkout() { return workout; }
    public void setWorkout(List<Exercise> workout) { this.workout = workout; }

    public List<String> getAllExerciseNames(Workout workout) throws IOException, JSONException {

        ExerciseDictionary exerciseDictionary = new JsonToPOJOConverter().JsonToDictionary(EXERCISE_DICTIONARY_JSON);
        List<String> listOfNamesToReturn = new ArrayList<>();

        for (Exercise exercise : workout.getWorkout()) {
            listOfNamesToReturn.add(exerciseDictionary.getShortName(exercise));
        }
        return listOfNamesToReturn;
    }

    public void printExercises() {
        for ( Exercise exercise : this.getWorkout()) {
            System.out.print(exercise.getName() + "; ");
        }
        System.out.println();
    }

    //randomly select muscle groups to work depending on workout type
    public List<Exercise> getListOfPossibleExercises(String workoutType) throws IOException, JSONException {

        ExerciseDictionary exerciseDictionary = new JsonToPOJOConverter().JsonToDictionary(EXERCISE_DICTIONARY_JSON);

        List<String> movementsToWorkInWorkout = new JsonToPOJOConverter().JsonToMovementTypes(WORKOUT_PROPERTIES_JSON).get(workoutType);
        List<String> lightOrHeavyInWorkout = new JsonToPOJOConverter().JsonToLightOrHeavy(WORKOUT_PROPERTIES_JSON).get(workoutType);

        List<Exercise> exercisesList = new ArrayList<>();

        for ( int i=0 ; i<movementsToWorkInWorkout.size(); i++ ) {

            for (Exercise exercise : exerciseDictionary.getExerciseDictionary().values() ) {
                if ( exercise.getLightOrHeavy().equals(lightOrHeavyInWorkout.get(i)) && exercise.getMovementType().equals(movementsToWorkInWorkout.get(i)) ) {
                    exercisesList.add(exercise);
                }
            }
        }

        return exercisesList;
    }

    public List<Exercise> removeRepeatExercises (List<Exercise> exerciseList) throws IOException, JSONException {

        JsonWriter jsonWriter = new JsonWriter();
        Integer RECENCY_LIST_DEPTH = 2;
        jsonWriter.initializeExerciseRecency(EXERCISE_RECENCY_JSON, RECENCY_LIST_DEPTH);
        ExerciseDictionary exerciseDictionary = new JsonToPOJOConverter().JsonToDictionary(EXERCISE_DICTIONARY_JSON);
        HashMap<String, List<Boolean>> exerciseAndRecency = new JsonToPOJOConverter().JsonToExerciseRecency(EXERCISE_RECENCY_JSON, RECENCY_LIST_DEPTH);
        List<Exercise> exercisesToBeRemoved = new ArrayList<>();

        for ( Exercise exercise : exerciseList ) {

            boolean recentlyUsed = false;

            for ( Boolean recentlyExercised : exerciseAndRecency.get(exerciseDictionary.getShortName(exercise)) ) {
                if (recentlyExercised) {
                    recentlyUsed = true;
                    break;
                }
            }

            if (recentlyUsed) {
                exercisesToBeRemoved.add(exercise);
            }
        }

        for ( Exercise exerciseToRemove : exercisesToBeRemoved) {
            exerciseList.remove(exerciseToRemove);
        }

        return exerciseList;
    }

    public Workout chooseExercises(List<Exercise> possibleExerciseList, String workoutType) throws IOException, JSONException {

        Workout workoutToReturn = new Workout();
        List<Exercise> finalWorkout = new ArrayList<>();
        Random rand = new Random();

        List<String> movementsToWorkInWorkout = new JsonToPOJOConverter().JsonToMovementTypes(WORKOUT_PROPERTIES_JSON).get(workoutType);
        List<String> lightOrHeavyInWorkout = new JsonToPOJOConverter().JsonToLightOrHeavy(WORKOUT_PROPERTIES_JSON).get(workoutType);

        for ( int i=0 ; i<movementsToWorkInWorkout.size(); i++ ) {

            List<Exercise> possibleExercisesPerGroupList = new ArrayList<>();

            for (Exercise exercise : possibleExerciseList ) {
                if ( exercise.getLightOrHeavy().equals(lightOrHeavyInWorkout.get(i)) && exercise.getMovementType().equals(movementsToWorkInWorkout.get(i)) ) {
                    possibleExercisesPerGroupList.add(exercise);
                }
            }
            int randomIndex = rand.nextInt(possibleExercisesPerGroupList.size());
            finalWorkout.add(possibleExercisesPerGroupList.get(randomIndex));
        }
        workoutToReturn.setWorkout(finalWorkout);
        return workoutToReturn;
    }

    //create final workout
    public Workout createWorkout(String workoutType) throws IOException, JSONException {

        JsonWriter jsonWriter = new JsonWriter();

        List<Exercise> exercisesInWorkout = getListOfPossibleExercises(workoutType);
        exercisesInWorkout = removeRepeatExercises(exercisesInWorkout);
        Workout workoutToReturn = chooseExercises(exercisesInWorkout, workoutType);
        jsonWriter.updateRepeatExercises(workoutToReturn, EXERCISE_RECENCY_JSON);

        return workoutToReturn;
    }
}

