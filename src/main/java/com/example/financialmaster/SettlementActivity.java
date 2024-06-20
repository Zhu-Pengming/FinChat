package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class SettlementActivity extends AppCompatActivity {

    private TextView settlementResult;
    private double annualReturnRate;
    private double initialAmount;
    private int duration;
    private Fund selectedFund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);

        settlementResult = findViewById(R.id.settlement_result);

        Intent intent = getIntent();
        initialAmount = intent.getDoubleExtra("amount", 0);
        duration = intent.getIntExtra("duration", 0);
        selectedFund = (Fund) intent.getSerializableExtra("selectedFund");

        // 模拟计算收益或亏损
        double finalAmount = calculateSettlement(initialAmount, duration, selectedFund);
        displayResult(finalAmount);
    }

    private double calculateSettlement(double amount, int duration, Fund fund) {
        double annualReturnRate = fund.getAnnualReturn() / 100;
        double dailyReturnRate = annualReturnRate / 365;
        double finalAmount = amount * Math.pow((1 + dailyReturnRate), duration * 30); // 近似每月30天
        return finalAmount;
    }

    private void displayResult(double finalAmount) {
        double profit = finalAmount - initialAmount;
        double profitPercentage = (profit / initialAmount) * 100;

        if (profitPercentage >= 4) {
            settlementResult.setText("结算成功: 达到或超过4%目标收益。盈利: " + profit + " (" + profitPercentage + "%)");
            setResult(RESULT_OK, new Intent().putExtra("finalAmount", finalAmount));
        } else {
            settlementResult.setText("结算失败: 未达到目标收益。亏损: " + profit + " (" + profitPercentage + "%)");
            setResult(RESULT_CANCELED, new Intent().putExtra("finalAmount", finalAmount));
        }
        finish();
    }
}