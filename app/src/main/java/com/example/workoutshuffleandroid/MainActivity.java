package com.example.workoutshuffleandroid;

import android.content.Intent;
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
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

        ImageView bro = findViewById(R.id.lifterImage);
        bro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSelector(null, null);
            }
        });
    }

    private void goToSelector(String key, String value) {
        Intent goToSelector = new Intent(this, Selector.class);
        goToSelector.putExtra(key,value);
        startActivity(goToSelector);
    }
}