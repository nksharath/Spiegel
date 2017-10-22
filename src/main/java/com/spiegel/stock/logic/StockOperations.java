package com.spiegel.stock.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiegel.stock.client.AlphaAdvantageClient;
import com.spiegel.stock.contants.Constants;
import com.spiegel.stock.pojo.MonthDataBO;
import com.spiegel.stock.pojo.Stock;
import com.spiegel.stock.pojo.Symbol;
import com.spiegel.stock.report.ReportDataBO;
import com.spiegel.stock.report.ReportWriter;
import com.spiegel.stock.utility.Utility;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockOperations {

    private final AlphaAdvantageClient alphaAdvantageClient;

    public StockOperations()
    {
        alphaAdvantageClient = new AlphaAdvantageClient();
    }

    private ArrayList<MonthDataBO> convertJsonToPojo(final String jsonResponse)
    {
        ArrayList<MonthDataBO> convertedDataList = new ArrayList<MonthDataBO>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            List<JsonNode> monthData = rootNode.findValues("Monthly Time Series");
            for(JsonNode node : monthData)
            {
                Iterator<Map.Entry<String, JsonNode>> iter = node.fields();
                while(iter.hasNext())
                {
                    Map.Entry<String, JsonNode> entry = iter.next();
                    String lastTradingDateStr = entry.getKey();
                    JsonNode monthNode = entry.getValue();
                    Date lastTradingDate = null;
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        lastTradingDate = dateFormat.parse(lastTradingDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    JsonNode monthlyOpen = monthNode.get("1. open");
                    JsonNode monthlyHigh = monthNode.get("2. high");
                    JsonNode monthlyLow = monthNode.get("3. low");
                    JsonNode monthlyClose = monthNode.get("4. close");
                    JsonNode monthlyVolume = monthNode.get("5. volume");
                    MonthDataBO monthDataBO = new MonthDataBO()
                            .setLastTradingDate(lastTradingDate)
                            .setMonthlyOpen(monthlyOpen.asDouble())
                            .setMonthlyHigh(monthlyHigh.asDouble())
                            .setMonthlyLow(monthlyLow.asDouble())
                            .setMonthlyClose(monthlyClose.asDouble())
                            .setMonthlyVolume(monthlyVolume.asDouble());
                    convertedDataList.add(monthDataBO);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedDataList;
    }

    private ArrayList<MonthDataBO> getTimeSeriesMonthly(final Symbol symbol)
    {
        Map<Object,Object> responseMap;
        String uri = Constants.ALPHA_ADVANTAGE_END_POINT+Constants.TIME_SERIES_MONTHLY_FUNCTION+"&symbol="+symbol.getSymbol_name();
        String jsonResponse = alphaAdvantageClient.executeGet(uri);
        return convertJsonToPojo(jsonResponse);
    }
    private ArrayList<Stock> getStockData()
    {
        ArrayList<Stock> stockData = new ArrayList<Stock>();
        List<Symbol> symbolList = getSymbolList("ALL");
        int count =0;
        for(Symbol symbol : symbolList)
        {
            //if(count == 50)
                //break;
            count++;
            List<MonthDataBO> monthDataList= getTimeSeriesMonthly(symbol);
            if(monthDataList.isEmpty())
                continue;
            stockData.add(new Stock().setSymbol(symbol).setMonthData(monthDataList));
        }
        return stockData;
    }

    private List<Symbol> getSymbolList(String sectorFilter)
    {
        List<Symbol> symbolList = new ArrayList<Symbol>();
        try {

            File file = new File(Constants.SYMBOL_FILE_NAME);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String splitArray[] = line.split("<->");
                String symbol = splitArray[0].trim().replaceAll(",","-");
                String companyName = splitArray[1].trim().replaceAll(",","-");;
                String sector = splitArray[2].trim().replaceAll(",","-");;
                String industry = splitArray[3].trim().replaceAll(",","-");;
                if(sectorFilter.equalsIgnoreCase("ALL"))
                    symbolList.add(new Symbol(symbol,companyName,sector,industry));
                else if(sector.equalsIgnoreCase(sectorFilter))
                    symbolList.add(new Symbol(symbol,companyName,sector,industry));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return symbolList;
    }

    private List<ReportDataBO> getPercentChangeLastXMonths(int X)
    {
        List<Stock> stockData = getStockData();
        List<ReportDataBO> reportData = new ArrayList<ReportDataBO>();
        for(Stock symbol : stockData)
        {
            List<MonthDataBO> monthDataBOList = symbol.getLastXMonths(X);
            double lowestPrice = Utility.getLowestStockPrice(monthDataBOList);
            double highestPrice = Utility.getHighestStockPrice(monthDataBOList);
            double currentPrice = Utility.getCurrentStockPrice(monthDataBOList);
            double weightedAverage = Utility.getAverageStockPrice(monthDataBOList);

            double percentChange = ((currentPrice - weightedAverage)/weightedAverage)*100;
            percentChange = Math.round(percentChange*100);
            percentChange = percentChange/100;

            reportData.add(new ReportDataBO()
                    .setCurrentPrice(currentPrice)
                    .setLowestInXMonths(lowestPrice)
                    .setHighestInXMonths(highestPrice)
                    .setAverageInXmonths(weightedAverage)
                    .setFiftyTwoWeekLow(symbol.get52WeekLow(Calendar.getInstance().get(Calendar.YEAR)))
                    .setFiftyTwoWeekHigh(symbol.get52WeekHigh(Calendar.getInstance().get(Calendar.YEAR)))
                    .setLastXMonths(X)
                    .setPercentChange(percentChange)
                    .setSymbol(symbol.getSymbol()));
        }
        return reportData;
    }
    public void GenerateMonthlyReport()
    {
        ReportWriter.writeCSVData(getPercentChangeLastXMonths(3),"monthly_report.csv");
        System.out.println("Success");
    }
}
