package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SettlementActivity extends AppCompatActivity {

    private TextView resultTextView;
    private Button retryButton;
    private Button nextButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);

        resultTextView = findViewById(R.id.result_text_view);
        retryButton = findViewById(R.id.retry_button);
        nextButton = findViewById(R.id.next_button);

        double amount = getIntent().getDoubleExtra("amount", 0);
        int duration = getIntent().getIntExtra("duration", 0);
        int cycle = getIntent().getIntExtra("cycle", 1);

        double targetReturn = calculateTargetReturn(cycle, amount);

        // 模拟结算逻辑
        Random random = new Random();
        double profitOrLoss = amount * (0.02 + (random.nextDouble() * 0.06) - 0.03);
        double finalAmount = amount + profitOrLoss;

        String resultText = "投资金额: " + amount + "\n" +
                "投资周期: " + duration + " 个月\n" +
                "结算金额: " + finalAmount + "\n" +
                (finalAmount >= targetReturn ? "结算成功: 获得收益" : "结算失败: 未达到预期收益");

        resultTextView.setText(resultText);

        retryButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        nextButton.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });
    }

    private double calculateTargetReturn(int cycle, double amount) {
        switch (cycle) {
            case 1:
                return amount * 1.04;
            case 2:
                return amount * 1.04;
            case 3:
                return amount * 1.04;
            case 4:
                return amount * 1.04;
            case 5:
                return amount * 1.04;
            default:
                return amount * 1.04;
        }
    }
}
