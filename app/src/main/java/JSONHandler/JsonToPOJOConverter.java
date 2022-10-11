package JSONHandler;

import Exercises.Exercise;
import Exercises.ExerciseDictionary;
import Muscles.Muscle;
import Muscles.MuscleGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class JsonToPOJOConverter {
    public JsonToPOJOConverter() {}

    public HashMap<String, List<Boolean>> JsonToExerciseRecency(String filePath, Integer recencyListDepth) throws IOException, JSONException {

        JSONObject jsonObject = JSONUtil.parseJSONFile(filePath);
        HashMap<String, List<Boolean>> exerciseRecency = new HashMap<>();

        for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
            String exerciseName = it.next();

            List<Boolean> recencyList = new ArrayList<>();

            for ( Object recentlyUsed : (ArrayList) jsonObject.get(exerciseName) ) {
                recencyList.add((Boolean) recentlyUsed);
            }
            //System.out.println(exerciseName);
            //System.out.println(jsonObject.get(exerciseName));
            exerciseRecency.put(exerciseName, recencyList);
        }

        return exerciseRecency;
    }

    public List<MuscleGroup> JsonToMuscleGroups(String filePath) throws IOException, JSONException {

        JSONObject jsonObject = JSONUtil.parseJSONFile(filePath);

        List<MuscleGroup> allMuscleGroups = new ArrayList<>();

        for ( int i=0 ; i<jsonObject.getJSONArray("muscleGroups").length(); i++ ) {
            for (Iterator<String> it = jsonObject.getJSONArray("muscleGroups").getJSONObject(i).keys(); it.hasNext(); ) {
                String mainMuscleGroup = it.next();
                for ( int j=0; j <jsonObject.getJSONArray("muscleGroups").getJSONObject(i).getJSONArray(mainMuscleGroup).length(); j++) {
                    MuscleGroup muscleGroupToAdd = new MuscleGroup();
                    muscleGroupToAdd.setMajorMuscleGroup(mainMuscleGroup);
                    muscleGroupToAdd.setSubMuscleGroup((String) jsonObject.getJSONArray("muscleGroups").getJSONObject(i).getJSONArray(mainMuscleGroup).get(j));
                    allMuscleGroups.add(muscleGroupToAdd);
                }
            }
        }

        return allMuscleGroups;
    }

    public Map<String, List<String>> JsonToMovementTypes(String filePath) throws IOException, JSONException {

        JSONObject jsonObject = JSONUtil.parseJSONFile(filePath);

        Map<String,List<String>> movementsToWorkInWorkout = new HashMap<>();

        for (Iterator<String> it = jsonObject.getJSONObject("movementsToWorkInWorkout").keys(); it.hasNext(); ) {
            String workoutType = it.next();
            List<String> movementsList = new ArrayList<>();

            for (int j = 0; j<jsonObject.getJSONObject("movementsToWorkInWorkout").getJSONArray(workoutType).length(); j++) {
                movementsList.add((String) jsonObject.getJSONObject("movementsToWorkInWorkout").getJSONArray(workoutType).get(j));
            }
            movementsToWorkInWorkout.put(workoutType,movementsList);
        }

        return movementsToWorkInWorkout;
    }

    public Map<String, List<String>> JsonToLightOrHeavy(String filePath) throws IOException, JSONException {

        JSONObject jsonObject = JSONUtil.parseJSONFile(filePath);

        Map<String,List<String>> lightOrHeavyInWorkout = new HashMap<>();

        for (Iterator<String> it = jsonObject.getJSONObject("lightOrHeavyInWorkout").keys(); it.hasNext(); ) {
            String workoutType = it.next();
            List<String> lightOrHeavy = new ArrayList<>();

            for (int j = 0; j<jsonObject.getJSONObject("lightOrHeavyInWorkout").getJSONArray(workoutType).length(); j++) {
                lightOrHeavy.add((String) jsonObject.getJSONObject("lightOrHeavyInWorkout").getJSONArray(workoutType).get(j));
            }
            lightOrHeavyInWorkout.put(workoutType,lightOrHeavy);
        }

        return lightOrHeavyInWorkout;
    }

    public ExerciseDictionary JsonToDictionary(String filePath) throws IOException, JSONException {

        JSONObject jsonObject = JSONUtil.parseJSONFile(filePath);
        ExerciseDictionary exerciseDictionary = new ExerciseDictionary();

        for( int i=0; i<jsonObject.getJSONArray("exerciseDictionary").length(); i++) {

            String exerciseShortName = (String) jsonObject.getJSONArray("exerciseDictionary").getJSONObject(i).get("ShortName");

            org.json.JSONObject exercise = jsonObject.getJSONArray("exerciseDictionary").getJSONObject(i).getJSONObject("Exercise");
            org.json.JSONArray mainMuscleGroup = jsonObject.getJSONArray("exerciseDictionary").getJSONObject(i).getJSONObject("Exercise").getJSONArray("muscle").getJSONObject(0).getJSONArray("mainMuscleGroup");
            JSONArray relatedMuscleGroups = jsonObject.getJSONArray("exerciseDictionary").getJSONObject(i).getJSONObject("Exercise").getJSONArray("muscle").getJSONObject(1).getJSONArray("relatedMuscleGroups");

            Exercise exerciseToAdd = new Exercise();
            Muscle muscleToAdd = new Muscle();
            MuscleGroup mainMuscleGroupToAdd = new MuscleGroup();
            List<MuscleGroup> relatedMuscleGroupsToAdd = new ArrayList<>();

            exerciseToAdd.setName((String) exercise.get("name"));
            exerciseToAdd.setMovementType((String) exercise.get("movementType"));
            exerciseToAdd.setLightOrHeavy((String) exercise.get("lightOrHeavy"));

            mainMuscleGroupToAdd.setMajorMuscleGroup((String) mainMuscleGroup.get(0));
            mainMuscleGroupToAdd.setSubMuscleGroup((String) mainMuscleGroup.get(1));
            muscleToAdd.setMainMuscleGroup(mainMuscleGroupToAdd);

            if ( !relatedMuscleGroups.get(0).equals(null)) {
                for ( int j=0; j<relatedMuscleGroups.length(); j++) {
                    MuscleGroup relatedMuscleGroupToAdd = new MuscleGroup();
                    relatedMuscleGroupToAdd.setMajorMuscleGroup((String) relatedMuscleGroups.getJSONArray(j).get(0));
                    relatedMuscleGroupToAdd.setSubMuscleGroup((String) relatedMuscleGroups.getJSONArray(j).get(1));
                    relatedMuscleGroupsToAdd.add(relatedMuscleGroupToAdd);
                }
            }

            muscleToAdd.setRelatedMuscleGroups(relatedMuscleGroupsToAdd);

            exerciseToAdd.setMuscle(muscleToAdd);

            exerciseDictionary.addExercise(exerciseShortName,exerciseToAdd);
        }
        return exerciseDictionary;
    }
}
