package com.example.workoutshuffleandroid;

import Exercises.Exercise;
import Workouts.Workout;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPrinter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            setContentView(R.layout.workout_printer);

            List<TextView> exerciseTextList = new ArrayList<>();

            TextView firstExerciseText = findViewById(R.id.firstExerciseText);
            String workoutType = getIntent().getStringExtra("workoutType");
            Workout workout = new Workout().createWorkout(workoutType);

            firstExerciseText.setText(workoutType);

            ImageView lifterImagePrinter = findViewById(R.id.lifterImagePrinter);
            lifterImagePrinter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
