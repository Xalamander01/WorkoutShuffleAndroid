package JSONHandler;

import Exercises.Exercise;
import Exercises.ExerciseDictionary;
import Workouts.Workout;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonWriter {

    private final String EXERCISE_DICTIONARY_JSON = "src/main/resources/ExerciseDictionary.json";

    public void initializeExerciseRecency(String filePath, Integer recencyListDepth) throws IOException, JSONException {

        File outputFile = new File(filePath);

        if ( outputFile.length() == 0 ) {

            ObjectMapper mapper = new ObjectMapper();
            ExerciseDictionary exerciseDictionary = new JsonToPOJOConverter().JsonToDictionary(EXERCISE_DICTIONARY_JSON);

            List<Boolean> recencyList = new ArrayList<>();
            HashMap<String, List<Boolean>> initialExerciseAndRepeat = new HashMap<>();

            for ( int i=0; i<recencyListDepth; i++) { recencyList.add(false); }

            for ( String exerciseShortName : exerciseDictionary.getExerciseDictionary().keySet() ) {
                initialExerciseAndRepeat.put(exerciseShortName, recencyList);
            }

            mapper.writeValue(outputFile, initialExerciseAndRepeat);
        }
    }

    public void updateRepeatExercises(Workout workout, String filePath) throws IOException, JSONException {

        ObjectMapper mapper = new ObjectMapper();

        Integer RECENCY_LIST_DEPTH = 2;
        String EXERCISE_RECENCY_JSON = "src/main/resources/ExercisesAndRecency.json";
        HashMap<String, List<Boolean>> exerciseAndRecency = new JsonToPOJOConverter().JsonToExerciseRecency(EXERCISE_RECENCY_JSON, RECENCY_LIST_DEPTH);
        ExerciseDictionary exerciseDictionary = new JsonToPOJOConverter().JsonToDictionary(EXERCISE_DICTIONARY_JSON);
        HashMap<String, List<Boolean>> updatedExerciseAndRecency = new HashMap<>();

        for ( Exercise exercise : exerciseDictionary.getExerciseDictionary().values() ) {

            List<Boolean> exerciseRecency = exerciseAndRecency.get(exerciseDictionary.getShortName(exercise));
            for ( int i=exerciseRecency.size()-1; i>0; i-- ) {
                exerciseRecency.set(i,exerciseRecency.get(i-1));
            }

            if ( workout.getAllExerciseNames(workout).contains(exerciseDictionary.getShortName(exercise)) ) {
                exerciseRecency.set(0,true);
                //System.out.println("FOUND " + exercise.getName() + " WHICH WAS JUST USED");
            } else {
                exerciseRecency.set(0,false);
                //System.out.println("FOUND " + exercise.getName() + " WHICH WAS NOT USED LAST TIME");
            }
            exerciseAndRecency.put(exerciseDictionary.getShortName(exercise), exerciseRecency);
        }

        mapper.writeValue(new File(filePath), exerciseAndRecency);
    }

}
