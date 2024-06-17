package com.example.fundapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fundapp.R;

public class CycleFailureActivity extends AppCompatActivity {

    private Button retryButton;

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
