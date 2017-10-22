package com.spiegel.stock.pojo;

import com.spiegel.stock.contants.Constants;
import com.spiegel.stock.utility.Utility;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Stock {

    private Symbol symbol;
    private List<MonthDataBO> monthData;

    public Stock setMonthData(List<MonthDataBO> monthData) {
        this.monthData = monthData;
        return this;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Stock setSymbol(Symbol symbol) {
        this.symbol = symbol;
        return this;
    }

    public List<MonthDataBO> getMonthData() {
        return monthData;
    }

    public Stock setMonthData(ArrayList<MonthDataBO> monthData) {
        this.monthData = monthData;
        return this;
    }

    public List<MonthDataBO> getLastXMonths(int X)
    {
        Collections.sort(monthData, new Comparator<MonthDataBO>() {
            public int compare(MonthDataBO o1, MonthDataBO o2) {
                return o1.getLastTradingDate().compareTo(o2.getLastTradingDate());
            }
        });

        if(monthData.size()-1-X >= 0)
            return monthData.subList(monthData.size()-1-X,monthData.size());
        else
            return monthData;
    }

    public double get52WeekHigh(int year)
    {
        Collections.sort(monthData, new Comparator<MonthDataBO>() {
            public int compare(MonthDataBO o1, MonthDataBO o2) {
                return o1.getLastTradingDate().compareTo(o2.getLastTradingDate());
            }
        });
        ArrayList<MonthDataBO> currentYearDataList = new ArrayList<MonthDataBO>();
        for(MonthDataBO monthDataBO : monthData)
        {

            LocalDate localDate = monthDataBO.getLastTradingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int yearLocal  = localDate.getYear();
            int month = localDate.getMonthValue();
            int day   = localDate.getDayOfMonth();
            if(year == yearLocal)
                currentYearDataList.add(monthDataBO);
        }

        return Utility.getHighestStockPrice(currentYearDataList);
    }

    public double get52WeekLow(int year)
    {
        Collections.sort(monthData, new Comparator<MonthDataBO>() {
            public int compare(MonthDataBO o1, MonthDataBO o2) {
                return o1.getLastTradingDate().compareTo(o2.getLastTradingDate());
            }
        });
        ArrayList<MonthDataBO> currentYearDataList = new ArrayList<MonthDataBO>();
        for(MonthDataBO monthDataBO : monthData)
        {

            LocalDate localDate = monthDataBO.getLastTradingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int yearLocal  = localDate.getYear();
            int month = localDate.getMonthValue();
            int day   = localDate.getDayOfMonth();
            if(year == yearLocal)
                currentYearDataList.add(monthDataBO);
        }

        return Utility.getLowestStockPrice(currentYearDataList);
    }
}
