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
            ImageView lifterButton = findViewById(R.id.lifterImageSelector);
            Button fullBodyButton = findViewById(R.id.fullBodyButton);

            ArrayList<Button> buttonArrayList = new ArrayList<>();
            buttonArrayList.add(findViewById(R.id.fullBodyButton));
            buttonArrayList.add(findViewById(R.id.upperButton));
            buttonArrayList.add(findViewById(R.id.lowerButton));
            buttonArrayList.add(findViewById(R.id.pushButton));
            buttonArrayList.add(findViewById(R.id.pullButton));
            buttonArrayList.add(findViewById(R.id.legsButton));

            fullBodyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToWorkoutPrinter(null, null);
                        //System.out.println(String.valueOf(fullBodyButton.getId()));
                    }
            });

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

    private void goToWorkoutPrinter(String key, String value) {
        Intent goToWorkoutPrinter = new Intent(this, WorkoutPrinter.class);
        goToWorkoutPrinter.putExtra(key,value);
        startActivity(goToWorkoutPrinter);
    }
}
