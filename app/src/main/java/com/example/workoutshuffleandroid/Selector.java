package com.example.workoutshuffleandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Selector extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            setContentView(R.layout.selector);

            ImageView backButton = findViewById(R.id.backButton);

            ArrayList<Button> buttonArrayList = new ArrayList<>();
            buttonArrayList.add(findViewById(R.id.fullBodyButton));
            buttonArrayList.add(findViewById(R.id.upperButton));
            buttonArrayList.add(findViewById(R.id.lowerButton));
            buttonArrayList.add(findViewById(R.id.pushButton));
            buttonArrayList.add(findViewById(R.id.pullButton));
            buttonArrayList.add(findViewById(R.id.legsButton));

            for ( Button workoutTypeButton : buttonArrayList ) {
                workoutTypeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToWorkoutPrinter("workoutType", getButtonIdString(workoutTypeButton));
                    }
                });
            }

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        private String getButtonIdString(Button workoutTypeButton) {
            String workoutButtonId = null;
            switch (workoutTypeButton.getId()) {
                case R.id.fullBodyButton: workoutButtonId = "fullBody"; break;
                case R.id.upperButton: workoutButtonId = "upper"; break;
                case R.id.lowerButton: workoutButtonId = "lower"; break;
                case R.id.pushButton: workoutButtonId = "push"; break;
                case R.id.pullButton: workoutButtonId = "pull"; break;
                case R.id.legsButton: workoutButtonId = "legs"; break;
            }
            return workoutButtonId;
        }

        private void goToWorkoutPrinter(String key, String value) {
            Intent goToWorkoutPrinter = new Intent(getApplicationContext(), WorkoutPrinter.class);
            goToWorkoutPrinter.putExtra(key,value);
            startActivity(goToWorkoutPrinter);
        }
}
