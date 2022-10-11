package com.example.workoutshuffleandroid;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private TextView message;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String[] state = {"initial"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.clickCounter);
        ImageView bro = findViewById(R.id.lifterImage);

        //Define and attach click listener
        bro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state[0] = tapDroid(state[0]);
            }
        });
    }

    private String tapDroid(String state) {
        String messageString = null;

        switch (state) {
            case "initial" : {
                messageString = "Hey bro, you wanna work out bro?";
                state = "chooseType";
                break;
            }
            case "chooseType": {
                messageString = "Yeeeh, let's go bro, what are we hittin' today bro?";
                state = "printedWorkout";
                break;
            }
            case "printedWorkout": {
                messageString = "Here you go bro, let the gains begin.\nHit me up again if you wanna try something else";
                state = "reset";
                break;
            }
            case "reset": {
                state = "chooseType";
                break;
            }
        }
        message.setText(String.format(messageString));
        return state;
    }
}