package com.example.financialmaster;


import java.io.Serializable;
import java.util.Date;

public class Fund implements Serializable {
    private String fundId;
    private String fundName;
    private String fundType;
    private String fundManager;
    private String inceptionDate;
    private double netAssetValue;
    private double totalAssets;
    private double totalLiabilities;
    private double expenseRatio;
    private double managementFee;
    private double performanceFee;
    private double dividendYield;
    private double annualReturn;
    private double ytdReturn;
    private double risk;
    private double sharpeRatio;
    private double alpha;
    private double beta;
    private double rSquared;
    private String benchmarkIndex;
    private String investmentObjective;
    private String investmentStrategy;
    private String risks;

    // Constructor
    public Fund(String fundId, String fundName, String fundType, String fundManager, String inceptionDate,
                double netAssetValue, double totalAssets, double totalLiabilities, double expenseRatio,
                double managementFee, double performanceFee, double dividendYield, double annualReturn,
                double ytdReturn, double risk, double sharpeRatio, double alpha, double beta, double rSquared,
                String benchmarkIndex, String investmentObjective, String investmentStrategy, String risks) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.fundType = fundType;
        this.fundManager = fundManager;
        this.inceptionDate = inceptionDate;
        this.netAssetValue = netAssetValue;
        this.totalAssets = totalAssets;
        this.totalLiabilities = totalLiabilities;
        this.expenseRatio = expenseRatio;
        this.managementFee = managementFee;
        this.performanceFee = performanceFee;
        this.dividendYield = dividendYield;
        this.annualReturn = annualReturn;
        this.ytdReturn = ytdReturn;
        this.risk = risk;
        this.sharpeRatio = sharpeRatio;
        this.alpha = alpha;
        this.beta = beta;
        this.rSquared = rSquared;
        this.benchmarkIndex = benchmarkIndex;
        this.investmentObjective = investmentObjective;
        this.investmentStrategy = investmentStrategy;
        this.risks = risks;
    }

    // Getters and Setters
    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    public String getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = String.valueOf(inceptionDate);
    }

    public double getNetAssetValue() {
        return netAssetValue;
    }

    public void setNetAssetValue(double netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public double getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public double getExpenseRatio() {
        return expenseRatio;
    }

    public void setExpenseRatio(double expenseRatio) {
        this.expenseRatio = expenseRatio;
    }

    public double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(double managementFee) {
        this.managementFee = managementFee;
    }

    public double getPerformanceFee() {
        return performanceFee;
    }

    public void setPerformanceFee(double performanceFee) {
        this.performanceFee = performanceFee;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public double getAnnualReturn() {
        return annualReturn;
    }

    public void setAnnualReturn(double annualReturn) {
        this.annualReturn = annualReturn;
    }

    public double getYtdReturn() {
        return ytdReturn;
    }

    public void setYtdReturn(double ytdReturn) {
        this.ytdReturn = ytdReturn;
    }

    public double getRisk() {
        return risk;
    }

    public void setRisk(double risk) {
        this.risk = risk;
    }

    public double getSharpeRatio() {
        return sharpeRatio;
    }

    public void setSharpeRatio(double sharpeRatio) {
        this.sharpeRatio = sharpeRatio;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getRSquared() {
        return rSquared;
    }

    public void setRSquared(double rSquared) {
        this.rSquared = rSquared;
    }

    public String getBenchmarkIndex() {
        return benchmarkIndex;
    }

    public void setBenchmarkIndex(String benchmarkIndex) {
        this.benchmarkIndex = benchmarkIndex;
    }

    public String getInvestmentObjective() {
        return investmentObjective;
    }

    public void setInvestmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
    }

    public String getInvestmentStrategy() {
        return investmentStrategy;
    }

    public void setInvestmentStrategy(String investmentStrategy) {
        this.investmentStrategy = investmentStrategy;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }
}

