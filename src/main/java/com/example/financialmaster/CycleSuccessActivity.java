package com.example.fundapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fundapp.R;

public class CycleSuccessActivity extends AppCompatActivity {

    private Button nextCycleButton;

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
