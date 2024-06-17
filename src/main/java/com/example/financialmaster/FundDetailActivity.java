package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FundDetailActivity extends AppCompatActivity {

    private TextView fundName;
    private TextView fundManager;
    private TextView fundType;
    private TextView inceptionDate;
    private TextView netAssetValue;
    private TextView totalAssets;
    private TextView totalLiabilities;
    private TextView expenseRatio;
    private TextView managementFee;
    private TextView performanceFee;
    private TextView dividendYield;
    private TextView annualReturn;
    private TextView ytdReturn;
    private TextView risk;
    private TextView sharpeRatio;
    private TextView alpha;
    private TextView beta;
    private TextView rSquared;
    private TextView benchmarkIndex;
    private TextView investmentObjective;
    private TextView investmentStrategy;
    private TextView risks;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_detail);

        fundName = findViewById(R.id.fund_name);
        fundManager = findViewById(R.id.fund_manager);
        fundType = findViewById(R.id.fund_type);
        inceptionDate = findViewById(R.id.inception_date);
        netAssetValue = findViewById(R.id.net_asset_value);
        totalAssets = findViewById(R.id.total_assets);
        totalLiabilities = findViewById(R.id.total_liabilities);
        expenseRatio = findViewById(R.id.expense_ratio);
        managementFee = findViewById(R.id.management_fee);
        performanceFee = findViewById(R.id.performance_fee);
        dividendYield = findViewById(R.id.dividend_yield);
        annualReturn = findViewById(R.id.annual_return);
        ytdReturn = findViewById(R.id.ytd_return);
        risk = findViewById(R.id.risk);
        sharpeRatio = findViewById(R.id.sharpe_ratio);
        alpha = findViewById(R.id.alpha);
        beta = findViewById(R.id.beta);
        rSquared = findViewById(R.id.r_squared);
        benchmarkIndex = findViewById(R.id.benchmark_index);
        investmentObjective = findViewById(R.id.investment_objective);
        investmentStrategy = findViewById(R.id.investment_strategy);
        risks = findViewById(R.id.risks);

        Fund fund = (Fund) getIntent().getSerializableExtra("fund");




        fundName.setText(fund.getFundName());
        fundManager.setText(fund.getFundManager());
        fundType.setText(fund.getFundType());
        inceptionDate.setText(fund.getInceptionDate().toString());
        netAssetValue.setText(String.valueOf(fund.getNetAssetValue()));
        totalAssets.setText(String.valueOf(fund.getTotalAssets()));
        totalLiabilities.setText(String.valueOf(fund.getTotalLiabilities()));
        expenseRatio.setText(String.valueOf(fund.getExpenseRatio()));
        managementFee.setText(String.valueOf(fund.getManagementFee()));
        performanceFee.setText(String.valueOf(fund.getPerformanceFee()));
        dividendYield.setText(String.valueOf(fund.getDividendYield()));
        annualReturn.setText(String.valueOf(fund.getAnnualReturn()));
        ytdReturn.setText(String.valueOf(fund.getYtdReturn()));
        risk.setText(String.valueOf(fund.getRisk()));
        sharpeRatio.setText(String.valueOf(fund.getSharpeRatio()));
        alpha.setText(String.valueOf(fund.getAlpha()));
        beta.setText(String.valueOf(fund.getBeta()));
        rSquared.setText(String.valueOf(fund.getRSquared()));
        benchmarkIndex.setText(fund.getBenchmarkIndex());
        investmentObjective.setText(fund.getInvestmentObjective());
        investmentStrategy.setText(fund.getInvestmentStrategy());
        risks.setText(String.join(", ", fund.getRisks()));
    }
}
