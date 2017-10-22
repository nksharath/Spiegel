package com.spiegel.stock.report;

import com.spiegel.stock.pojo.Symbol;

public class ReportDataBO {

    private Symbol symbol;
    private double currentPrice;
    private double fiftyTwoWeekLow;
    private double fiftyTwoWeekHigh;
    private double percentChange;
    private double lowestInXMonths;
    private double highestInXMonths;
    private double averageInXmonths;
    private int lastXMonths;

    public Symbol getSymbol() {
        return symbol;
    }

    public ReportDataBO setSymbol(Symbol symbol) {
        this.symbol = symbol;
        return this;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public ReportDataBO setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public ReportDataBO setFiftyTwoWeekLow(double fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        return this;
    }

    public double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public ReportDataBO setFiftyTwoWeekHigh(double fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
        return this;
    }


    public int getLastXMonths() {
        return lastXMonths;
    }

    public ReportDataBO setLastXMonths(int lastXMonths) {
        this.lastXMonths = lastXMonths;
        return this;
    }

    public double getLowestInXMonths() {
        return lowestInXMonths;
    }

    public ReportDataBO setLowestInXMonths(double lowestInXMonths) {
        this.lowestInXMonths = lowestInXMonths;
        return this;
    }

    public double getHighestInXMonths() {
        return highestInXMonths;
    }

    public ReportDataBO setHighestInXMonths(double highestInXMonths) {
        this.highestInXMonths = highestInXMonths;
        return this;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public ReportDataBO setPercentChange(double percentChange) {
        this.percentChange = percentChange;
        return this;
    }

    public double getAverageInXmonths() {
        return averageInXmonths;
    }

    public ReportDataBO setAverageInXmonths(double averageInXmonths) {
        this.averageInXmonths = averageInXmonths;
        return this;
    }
}
