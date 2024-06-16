package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button cycle1Button = findViewById(R.id.cycle1_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})Button cycle2Button = findViewById(R.id.cycle2_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})Button cycle3Button = findViewById(R.id.cycle3_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})Button cycle4Button = findViewById(R.id.cycle4_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})Button cycle5Button = findViewById(R.id.cycle5_button);

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
