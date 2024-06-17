package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);





        ImageView gameReturn = findViewById(R.id.game_return);
        ImageView cycle1Button = findViewById(R.id.cycle1_button);
        ImageView cycle2Button = findViewById(R.id.cycle2_button);
        ImageView cycle3Button = findViewById(R.id.cycle3_button);
        ImageView cycle4Button = findViewById(R.id.cycle4_button);
        ImageView cycle5Button = findViewById(R.id.cycle5_button);

        gameReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(GameActivity.this, MainActivity.class);
               startActivity(intent);
            }
        });

        cycle1Button.setOnClickListener(v -> startCycleActivity(1));
        cycle2Button.setOnClickListener(v -> startCycleActivity(2));
        cycle3Button.setOnClickListener(v -> startCycleActivity(3));
        cycle4Button.setOnClickListener(v -> startCycleActivity(4));
        cycle5Button.setOnClickListener(v -> startCycleActivity(5));
    }

    private void startCycleActivity(int cycle) {
        Intent intent = new Intent(GameActivity.this, CycleActivity.class);
        intent.putExtra("cycle", cycle);
        startActivity(intent);
    }
}
