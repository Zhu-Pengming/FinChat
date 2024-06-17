package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FundAdapter fundAdapter;
    private List<Fund> fundList;
    private EditText investmentAmount;
    private EditText investmentDuration;
    private Button investButton;

    private int cycle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        cycle = getIntent().getIntExtra("cycle", 1);

        recyclerView = findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fundList = new ArrayList<>();
        fundList.add(new Fund("001", "Vanguard 500 Index Fund", "Index Fund", "John Doe", "1976-08-31",
                300.25, 5000000, 1000000, 0.15, 0.05, 0.10, 1.5, 10.5, 8.2, 0.8, 1.2, 0.5, 1.0,0.95,"S&P 500", "Capital Growth", "Index Strategy", "Market risk, inflation risk"));
        fundList.add(new Fund("002", "Fidelity Contrafund", "Mutual Fund", "Will Danoff", "1967-05-17",
                200.10, 10000000, 2000000, 0.50, 0.20, 0.15, 2.0, 12.0, 10.0, 0.9, 1.3, 0.6, 1.1,0.90,"Russell 1000", "Capital Growth", "Contrarian Strategy", "Market risk, liquidity risk"));
        fundList.add(new Fund("003", "T. Rowe Price Blue Chip Growth Fund", "Mutual Fund", "Larry Puglia", "1993-06-30",
                150.75, 7500000, 1500000, 0.25, 0.10, 0.20, 1.8, 15.2, 12.5, 1.0, 1.4, 0.7, 1.2,0.92,"S&P 500", "Capital Growth", "Growth Strategy", "Market risk, interest rate risk"));
        fundList.add(new Fund("004", "American Funds Growth Fund", "Mutual Fund", "Martin Romo", "1973-12-01",
                180.50, 8500000, 1700000, 0.35, 0.15, 0.25, 1.9, 11.3, 9.0, 0.85, 1.25, 0.65, 1.15,0.90,"S&P 500", "Capital Growth", "Growth Strategy", "Market risk, credit risk"));
        fundList.add(new Fund("005", "Dodge & Cox Stock Fund", "Mutual Fund", "Charles Pohl", "1965-01-04",
                175.00, 9500000, 1900000, 0.40, 0.20, 0.30, 2.1, 9.8, 7.5, 0.75, 1.15, 0.55, 1.1,0.88,"S&P 500", "Capital Growth", "Value Strategy", "Market risk, interest rate risk"));
        fundList.add(new Fund("006", "Franklin DynaTech Fund", "Mutual Fund", "Rupert H. Johnson, Jr.", "1968-01-01",
                210.25, 9200000, 1800000, 0.45, 0.25, 0.35, 2.3, 13.5, 11.2, 0.95, 1.35, 0.75, 1.3,0.90,"NASDAQ 100", "Capital Growth", "Tech Strategy", "Market risk, liquidity risk"));
        fundList.add(new Fund("007", "Invesco QQQ Trust", "ETF", "John Q. Doe", "1999-03-10",
                280.50, 6000000, 1200000, 0.20, 0.10, 0.25, 1.7, 14.7, 12.0, 0.85, 1.20, 0.65, 1.2,0.91,"NASDAQ 100", "Capital Growth", "Index Strategy", "Market risk, inflation risk"));
        fundList.add(new Fund("008", "SPDR S&P 500 ETF Trust", "ETF", "Jane Smith", "1993-01-22",
                250.75, 7000000, 1400000, 0.25, 0.15, 0.20, 1.6, 10.4, 8.5, 0.8, 1.1, 0.55, 1.1,0.93,"S&P 500", "Capital Growth", "Index Strategy", "Market risk, liquidity risk"));
        fundList.add(new Fund("009", "iShares Russell 2000 ETF", "ETF", "Mary Johnson", "2000-05-22",
                220.50, 8000000, 1600000, 0.30, 0.20, 0.25, 1.9, 12.1, 9.5, 0.9, 1.2, 0.6, 1.15,0.89,"Russell 2000", "Capital Growth", "Index Strategy", "Market risk, credit risk"));
        fundList.add(new Fund("010", "Vanguard Total Stock Market Index Fund", "Index Fund", "Jack Bogle", "1992-04-27",
                230.75, 6500000, 1300000, 0.15, 0.10, 0.20, 1.5, 11.0, 9.0, 0.85, 1.15, 0.65, 1.1,0.90,"CRSP US Total Market", "Capital Growth", "Index Strategy", "Market risk, inflation risk"));

        fundAdapter = new FundAdapter(fundList, this);
        recyclerView.setAdapter(fundAdapter);

        investButton.setOnClickListener(v -> {
            String amountStr = investmentAmount.getText().toString();
            String durationStr = investmentDuration.getText().toString();
            if (!amountStr.isEmpty() && !durationStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                int duration = Integer.parseInt(durationStr);
                Intent intent = new Intent(CycleActivity.this, SettlementActivity.class);
                intent.putExtra("amount", amount);
                intent.putExtra("duration", duration);
                intent.putExtra("cycle", cycle);
                startActivityForResult(intent, cycle);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}

