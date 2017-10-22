package com.spiegel.stock.Runner;

import com.spiegel.stock.logic.StockOperations;

public class StockReportMain {

    public static void main(String args[])
    {
        StockOperations stockOperations = new StockOperations();
        stockOperations.GenerateMonthlyReport();
    }
}
