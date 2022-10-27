package com.example.workoutshuffleandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutPrinter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.workout_printer);

        //String workoutType = getIntent().getStringExtra("workoutType");
        //TextView firstExerciseText = findViewById(R.id.firstExercise);
        //firstExerciseText.setText(workoutType);
/*
        message = findViewById(R.id.clickCounter);
        ImageView bro = findViewById(R.id.lifterImage);

        //Define and attach click listener
        bro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state[0] = tapDroid(state[0]);
            }
        });

 */
        ImageView lifterImagePrinter = findViewById(R.id.lifterImagePrinter);
        lifterImagePrinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
