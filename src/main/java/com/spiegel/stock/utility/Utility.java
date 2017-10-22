package com.spiegel.stock.utility;

import com.spiegel.stock.pojo.MonthDataBO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utility {

    public static double getHighestStockPrice(List<MonthDataBO> monthDataBOList)
    {
        List<Double> lowestValList = new ArrayList<Double>();
        for(MonthDataBO monthDataBO : monthDataBOList)
        {
            lowestValList.add(monthDataBO.getMonthlyClose());
            lowestValList.add(monthDataBO.getMonthlyHigh());
            lowestValList.add(monthDataBO.getMonthlyLow());
            lowestValList.add(monthDataBO.getMonthlyOpen());
        }
        Collections.sort(lowestValList);
        return lowestValList.get(lowestValList.size()-1);
    }

    public static double getLowestStockPrice(List<MonthDataBO> monthDataBOList)
    {
        List<Double> lowestValList = new ArrayList<Double>();
        for(MonthDataBO monthDataBO : monthDataBOList)
        {
            lowestValList.add(monthDataBO.getMonthlyClose());
            lowestValList.add(monthDataBO.getMonthlyHigh());
            lowestValList.add(monthDataBO.getMonthlyLow());
            lowestValList.add(monthDataBO.getMonthlyOpen());
        }
        Collections.sort(lowestValList);
        double roundedVal = Math.round(lowestValList.get(0) * 100);
        roundedVal = roundedVal / 100;
        return roundedVal;
    }

    public static double getCurrentStockPrice(List<MonthDataBO> monthDataBOList)
    {
        Collections.sort(monthDataBOList, new Comparator<MonthDataBO>() {
            public int compare(MonthDataBO o1, MonthDataBO o2) {
                return o1.getLastTradingDate().compareTo(o2.getLastTradingDate());
            }
        });
        double roundedVal = Math.round(monthDataBOList.get(monthDataBOList.size()-1).getMonthlyClose()*100);
        roundedVal = roundedVal / 100;
        return roundedVal;
    }

    public static double getAverageStockPrice(List<MonthDataBO> monthDataBOList)
    {
        double sum = 0.0;
        for(MonthDataBO monthDataBO : monthDataBOList)
        {
            sum+=monthDataBO.getMonthlyClose();
            sum+=monthDataBO.getMonthlyHigh();
            sum+=monthDataBO.getMonthlyLow();
            sum+=monthDataBO.getMonthlyOpen();
        }

        int dataPoints = monthDataBOList.size() * 4;
        double avg = sum/dataPoints;
        avg = Math.round(avg*100);
        avg = avg / 100;
        return avg;
    }
}
