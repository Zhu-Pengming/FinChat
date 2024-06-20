package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class CycleFailureActivity extends AppCompatActivity {

    private Button retryButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_failure);

        retryButton = findViewById(R.id.retry_button);

        retryButton.setOnClickListener(v -> {
            Intent intent = new Intent(CycleFailureActivity.this, CycleActivity.class);
            intent.putExtra("cycle", 1); // 重新开始周期1
            startActivity(intent);
            finish();
        });
    }
}