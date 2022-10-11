import Workouts.Workout;
import org.json.JSONException;

import java.io.IOException;

public class WorkoutShuffle {
    public static void main(String[] args) throws IOException, JSONException {

            for ( int i=0; i<5; i++) {
                Workout fullBodyWorkout = new Workout().createWorkout("fullBody");
                fullBodyWorkout.printExercises();
                Workout upperWorkout = new Workout().createWorkout("upper");
                upperWorkout.printExercises();
                Workout lowerWorkout = new Workout().createWorkout("lower");
                lowerWorkout.printExercises();
                Workout pushWorkout = new Workout().createWorkout("push");
                pushWorkout.printExercises();
                Workout pullWorkout = new Workout().createWorkout("pull");
                pullWorkout.printExercises();
                Workout legsWorkout = new Workout().createWorkout("legs");
                legsWorkout.printExercises();
            }
    }

    /*
    object structure:
    - workouts
        - splits
            - full body, push, pull etc. ( String )
            - for each split, randomly pick exercises with:
                - movements to work ( String )
                - light or heavy ( String )
                - muscles to work ( Muscles.Muscle )
    - exercises
        - name
        - movement type ( String )
        - light or heavy ( String )
        - muscles to work ( Muscles.Muscle )
    - muscles
        - main muscle group ( list of strings )
        - sub muscle group ( list of strings )
        - related muscles ( list of strings )
     */
}