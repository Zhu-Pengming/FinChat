package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FundDetailActivity extends AppCompatActivity {

    private TextView fundName;

    private TextView fundType;
    private TextView inceptionDate;

    private TextView totalAssets;

    private TextView managementFee;
    private TextView performanceFee;

    private TextView annualReturn;

    private TextView risk;
    private TextView sharpeRatio;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_detail);

        Log.d("hhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhh");

        fundName = findViewById(R.id.fund_name);
        fundType = findViewById(R.id.fund_type);
        inceptionDate = findViewById(R.id.inception_date);
        totalAssets = findViewById(R.id.total_assets);
        managementFee = findViewById(R.id.management_fee);
        performanceFee = findViewById(R.id.performance_fee);
        annualReturn = findViewById(R.id.annual_return);

        risk = findViewById(R.id.risk);
        sharpeRatio = findViewById(R.id.sharpe_ratio);
        WebView webView = findViewById(R.id.detail_web);

        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        Log.d("hhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhh");
        webSettings.setJavaScriptEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("WebView", consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.e("WebView", "Error: " + error.toString());
            }
        });

        webView.loadUrl("file:///android_asset/chart.html");

        Fund fund = (Fund) getIntent().getSerializableExtra("fund");

        ImageView returnTo = findViewById(R.id.detail_return);
        returnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FundDetailActivity.this, CycleActivity.class);
                startActivity(intent);
            }
        });




        fundName.setText(fund.getFundName());

        fundType.setText(fund.getFundType());
        inceptionDate.setText(fund.getInceptionDate().toString());

        totalAssets.setText(String.valueOf(fund.getTotalAssets()));

        managementFee.setText(String.valueOf(fund.getManagementFee()));
        performanceFee.setText(String.valueOf(fund.getPerformanceFee()));

        annualReturn.setText(String.valueOf(fund.getAnnualReturn()));

        risk.setText(String.valueOf(fund.getRisk()));
        sharpeRatio.setText(String.valueOf(fund.getSharpeRatio()));




    }
}
