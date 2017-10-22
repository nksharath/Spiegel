package com.spiegel.stock.report;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReportWriter {

    public static void writeCSVData(List<ReportDataBO> dataObjList, String fileName)
    {
        try {
            Collections.sort(dataObjList, new Comparator<ReportDataBO>() {
                public int compare(ReportDataBO o1, ReportDataBO o2) {
                    if (o1.getPercentChange() < o2.getPercentChange())
                            return -1;
                    else if(o1.getPercentChange() > o2.getPercentChange())
                            return 1;
                    else
                        return 0;
                }
            });
            File file = new File(fileName);
            boolean result = Files.deleteIfExists(file.toPath());
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"));
            StringBuffer lineEntryHeader = new StringBuffer();
            lineEntryHeader.append("Symbol,Company Name,Sector,Industry,Current Price,Lowest in X Months, Highest in X Months,Average in X Months,Percent Change with Average,52 Week Low,52 Week High,Last X Months");
            bufferedWriter.write(lineEntryHeader.toString());
            bufferedWriter.newLine();
            for(ReportDataBO dataBO : dataObjList)
            {
                StringBuffer lineEntry = new StringBuffer();

                lineEntry.append(dataBO.getSymbol().getSymbol_name());
                lineEntry.append(",");

                lineEntry.append(dataBO.getSymbol().getCompany_name());
                lineEntry.append(",");

                lineEntry.append(dataBO.getSymbol().getSector());
                lineEntry.append(",");

                lineEntry.append(dataBO.getSymbol().getIndustry());
                lineEntry.append(",");

                lineEntry.append(dataBO.getCurrentPrice());
                lineEntry.append(",");

                lineEntry.append(dataBO.getLowestInXMonths());
                lineEntry.append(",");

                lineEntry.append(dataBO.getHighestInXMonths());
                lineEntry.append(",");

                lineEntry.append(dataBO.getAverageInXmonths());
                lineEntry.append(",");

                lineEntry.append(dataBO.getPercentChange());
                lineEntry.append(",");

                lineEntry.append(dataBO.getFiftyTwoWeekLow());
                lineEntry.append(",");

                lineEntry.append(dataBO.getFiftyTwoWeekHigh());
                lineEntry.append(",");

                lineEntry.append(dataBO.getLastXMonths());
                lineEntry.append(",");


                bufferedWriter.write(lineEntry.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
