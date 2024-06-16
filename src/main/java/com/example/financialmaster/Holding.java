package com.example.financialmaster;

public class Holding {
    private String holdingName;
    private double holdingPercentage;
    private double holdingMarketValue;

    // Constructor
    public Holding(String holdingName, double holdingPercentage, double holdingMarketValue) {
        this.holdingName = holdingName;
        this.holdingPercentage = holdingPercentage;
        this.holdingMarketValue = holdingMarketValue;
    }

    // Getters and Setters
    public String getHoldingName() {
        return holdingName;
    }

    public void setHoldingName(String holdingName) {
        this.holdingName = holdingName;
    }

    public double getHoldingPercentage() {
        return holdingPercentage;
    }

    public void setHoldingPercentage(double holdingPercentage) {
        this.holdingPercentage = holdingPercentage;
    }

    public double getHoldingMarketValue() {
        return holdingMarketValue;
    }

    public void setHoldingMarketValue(double holdingMarketValue) {
        this.holdingMarketValue = holdingMarketValue;
    }
}
