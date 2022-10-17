package com.example.workoutshuffleandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Selector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.selector);
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
    }
}
