/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.attari.ivcweb.task.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.io.*;
import java.lang.Integer;

import java.math.BigDecimal;
import java.math.RoundingMode;

import au.com.attari.ivcweb.task.bean.CompanyData;
import au.com.attari.ivcweb.task.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author battari
 */
public class CompanyDataReader implements CompanyDataReaderIface {

    private static Logger logger = LoggerFactory.getLogger(CompanyDataReader.class);

    private String lineId = null;


    /**
     * read & process file into an ArrayList of Company data
     * @param fileNameIn
     * @param exchange the id - in this case ASX
     * @return
     */
    public ArrayList<CompanyData> read(String fileNameIn, String exchange) {

        String company = null;

        HashMap<Integer, String> datesHm = new HashMap<Integer, String>();
        int datesRank = 0;

        HashMap<Integer, String> equityHm = new HashMap<Integer, String>();
        int equityRank = 0;
        boolean equityStart = false;

        HashMap<Integer, String> roeHm = new HashMap<Integer, String>();
        int roeRank = 0;
        boolean roeStart = false;

        HashMap<Integer, String> payOutRatioHm = new HashMap<Integer, String>();
        HashMap<Integer, BigDecimal> earningsHm = new HashMap<Integer, BigDecimal>();
        HashMap<Integer, BigDecimal> dividendsHm = new HashMap<Integer, BigDecimal>();
        int payOutRatioRank = 0;
        boolean payOutRatioStart = false;
        boolean payOutRatioEnd = false;

        HashMap<Integer, String> sharesIssuedHm = new HashMap<Integer, String>();
        int sharesIssuedRank = 0;
        boolean sharesIssuedStart = false;

        ArrayList<CompanyData> companyDataList = new ArrayList<CompanyData>();
        
        BufferedReader in = null;

        try {

            in = new BufferedReader(new FileReader(fileNameIn));

            int numYearsSampled = 0;
            String tmp;
            String line;
            while ((tmp = in.readLine()) != null) {

                line = tmp.toUpperCase();

                if (company == null && line.indexOf("STOCKCODE=") >= 0) {
                    final String[] tmpArr = line.split("=");
                    logger.debug("----- tmpArr[0] " + tmpArr[0] + " tmpArr[1] " + tmpArr[1]);
                    company = tmpArr[1];
                    logger.info("company: {} ", company);
                }

  
                if (line.startsWith("Per Share".toUpperCase())) {
                	final String tmpArr[] =  line.split(",");
                    numYearsSampled = tmpArr.length - 1;
                    if(!line.endsWith("TREND")) {
                        numYearsSampled ++;
                    }
                    logger.info("numYearsSampled: {}", numYearsSampled);
                	for(int i = 1; i < numYearsSampled; i++) {
                		datesRank++;
                		datesHm.put(Integer.valueOf(datesRank), tmpArr[i]);
                	}
                }

                if (line.startsWith("Shareholders Equity (m)".toUpperCase())) {
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        ++equityRank;
                        equityHm.put(Integer.valueOf(equityRank), tmpArr[i] + "M");
                    }
                }

                if (line.startsWith("Return on Equity (%)".toUpperCase())) {
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        ++roeRank;
                        final String tmp2 = this.prvHandleNegative(tmpArr[i]);
                        if (BigDecimalUtils.isLessThanTen(tmp2)) {
                            roeHm.put(Integer.valueOf(roeRank), "0.0" + tmp2.replace(".", ""));
                        }
                        else {
                            roeHm.put(Integer.valueOf(roeRank), "0." + tmp2.replace(".", ""));
                        }
                    }
                }

                if (line.indexOf("Earnings".toUpperCase()) >= 0) {
                    payOutRatioStart = true;
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        earningsHm.put(Integer.valueOf(i), BigDecimalUtils.convertJavaBigDecimalStringToBigDecimal(tmpArr[i]));
                    }
                }

                if (line.indexOf("Dividends".toUpperCase()) >= 0) {
                    payOutRatioEnd = true;
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        dividendsHm.put(Integer.valueOf(i), BigDecimalUtils.convertJavaBigDecimalStringToBigDecimal(tmpArr[i]));
                    }
                }

                if (payOutRatioStart && payOutRatioEnd) {
                    for (int j = 1; j < numYearsSampled; ++j) {
                        final BigDecimal earnings = earningsHm.get(Integer.valueOf(j));
                        final BigDecimal dividends = dividendsHm.get(Integer.valueOf(j));
                        if (dividends.compareTo(new BigDecimal(0)) == 0) {
                            payOutRatioHm.put(Integer.valueOf(j), "0.0");
                        }
                        else if (earnings.compareTo(new BigDecimal(0)) == 0) {
                            payOutRatioHm.put(Integer.valueOf(j), "1.0");
                        }
                        else {
                            final BigDecimal tmp3 = dividends.divide(earnings, 2, RoundingMode.CEILING);
                            payOutRatioHm.put(Integer.valueOf(j), tmp3 + "");
                        }
                    }
                }

                if (line.indexOf("SHARES OUTSTANDING (M)") >= 0) {
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        ++sharesIssuedRank;
                        sharesIssuedHm.put(Integer.valueOf(sharesIssuedRank), tmpArr[i] + "M");
                    }
                }

                //TODO: Add capitalization: Market Capitalisation (m),0.0,0.0,0.0,0.0,107761.00,133009.00,129428.00,162762.00,184190.00
                //TODO: It is hard coded below.
            }
            in.close();
            in = null;

            Set<Integer> keySet = datesHm.keySet();
            Iterator<Integer> it = keySet.iterator();
            while(it.hasNext()) {
                Integer rank = it.next();

                logger.debug("--------------- Processing rank " + rank + ", " + datesHm.get(rank));
                logger.debug("--------------- Processing equity of " + rank + ", " + equityHm.get(rank));
                logger.debug("--------------- Processing shares issued of " + rank + ", " + sharesIssuedHm.get(rank));
                logger.debug("--------------- Processing roe of " + rank + ", " + roeHm.get(rank));
                logger.debug("--------------- Processing pay out ratio of " + rank + ", " + payOutRatioHm.get(rank));

                if(equityHm.get(rank) == null) {
                    continue;
                }

                CompanyData companyData = new CompanyData(company, exchange,
                        new BigDecimal(100000000.00),  // dummy Capitalisation
                        BigDecimalUtils.convertToBigDecimal(equityHm.get(rank)),
                        IntUtils.convertToInt(sharesIssuedHm.get(rank)),
                        datesHm.get(rank),
                        BigDecimalUtils.convertToBigDecimal(roeHm.get(rank)),
                        BigDecimalUtils.convertToBigDecimal(payOutRatioHm.get(rank)));
                companyDataList.add(companyData);
            }

        } catch (FileNotFoundException e1) {
            logger.error("[read] error reading financial data " + e1.getMessage());
            e1.printStackTrace();
            if(in != null) {
            	try {
            		in.close();
            	} catch(IOException e){
            	}
            }
        } catch (IOException e2) {
            logger.error("[read] error reading financial data " + e2.getMessage());
            e2.printStackTrace();
            if(in != null) {
            	try {
            		in.close();
            	} catch(IOException e){
            	}
            }
        }  catch (Exception e3) {
        	// TODO: put it in dead letter archive.
            logger.error("[read] error reading financial data " + e3.getMessage());
            e3.printStackTrace();
            if(in != null) {
            	try {
            		in.close();
            	} catch(IOException e){
            	}
            }
        } 

        return companyDataList;

    }

    private String prvHandleNegative(String lineSubStr) {

        String tmp1 = new String(lineSubStr);
        if(tmp1.equals("--")) {
            tmp1 = "0";
        }
        if(tmp1.startsWith("-")) {
            tmp1 = tmp1.replace("-", "");
        }
        return tmp1;
    }
}
