package com.example.workoutshuffleandroid;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Selector extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            setContentView(R.layout.selector);

            ImageView backButton = findViewById(R.id.backButton);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        /*
    private void retrieveMessage() {
        result = findViewById(R.id.activity_second_result);
        result.setText(getIntent().getStringExtra("message"));
    }

         */
}
