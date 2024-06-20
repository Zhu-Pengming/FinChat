package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.widget.Button;


public class CycleSuccessActivity extends AppCompatActivity {

    private Button nextCycleButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_success);

        nextCycleButton = findViewById(R.id.next_cycle_button);

        nextCycleButton.setOnClickListener(v -> {
            int cycle = getIntent().getIntExtra("cycle", 1);
            Intent intent = new Intent(CycleSuccessActivity.this, CycleActivity.class);
            intent.putExtra("cycle", cycle);
            startActivity(intent);
            finish();
        });
    }
}