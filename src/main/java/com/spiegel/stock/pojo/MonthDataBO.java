package com.spiegel.stock.pojo;

import java.util.Date;

public class MonthDataBO {
    private Date lastTradingDate;
    private double monthlyOpen;
    private double monthlyLow;
    private double monthlyHigh;
    private double monthlyClose;
    private double monthlyVolume;

    public Date getLastTradingDate() {
        return lastTradingDate;
    }

    public MonthDataBO setLastTradingDate(Date lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
        return this;
    }

    public double getMonthlyOpen() {
        return monthlyOpen;
    }

    public MonthDataBO setMonthlyOpen(double monthlyOpen) {
        this.monthlyOpen = monthlyOpen;
        return this;
    }

    public double getMonthlyLow() {
        return monthlyLow;
    }

    public MonthDataBO setMonthlyLow(double monthlyLow) {
        this.monthlyLow = monthlyLow;
        return this;
    }

    public double getMonthlyHigh() {
        return monthlyHigh;
    }

    public MonthDataBO setMonthlyHigh(double monthlyHigh) {
        this.monthlyHigh = monthlyHigh;
        return this;
    }

    public double getMonthlyClose() {
        return monthlyClose;
    }

    public MonthDataBO setMonthlyClose(double monthlyClose) {
        this.monthlyClose = monthlyClose;
        return this;
    }

    public double getMonthlyVolume() {
        return monthlyVolume;
    }

    public MonthDataBO setMonthlyVolume(double monthlyVolume) {
        this.monthlyVolume = monthlyVolume;
        return this;
    }
}
