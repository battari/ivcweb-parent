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

import au.com.attari.ivcweb.task.bean.CompanyDataOther;
import au.com.attari.ivcweb.task.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author battari
 */
public class CompanyDataOtherReader implements CompanyDataOtherReaderIface {

    private static final Logger logger = LoggerFactory.getLogger(CompanyDataOtherReader.class);

    private String lineId = null;


    /**
     * read & process file into an ArrayList of Company data
     * @return
     */
    public ArrayList<CompanyDataOther> read(String fileNameIn, String exchange) {

        String company = null;
        String companyName = null;

        HashMap<Integer, String> datesHm = new HashMap<Integer, String>();
        int datesRank = 0;

        HashMap<Integer, String> averageAnnualPERatioHm = new HashMap<Integer, String>();
        int averageAnnualPERatioRank = 0;
        boolean averageAnnualPERatioStart = false;

        final HashMap<Integer, String> equityHm = new HashMap<Integer, String>();
        int equityRank = 0;
        boolean debtToEquityStart = false;

        int debtRank = 0;
        boolean debtToEquityRatioEnd = false;
        final HashMap<Integer, String> debtToEquityRatioHm = new HashMap<Integer, String>();

        final HashMap<Integer, String> debtHm = new HashMap<Integer, String>();
        int debtToEquityRatioRank = 0;
        boolean debtToEquityRatioStart = false;

        HashMap<Integer, String> epsHm = new HashMap<Integer, String>();
        int epsRank = 0;
        boolean epsStart = false;

        ArrayList<CompanyDataOther> companyDataOtherList = new ArrayList<CompanyDataOther>();

        BufferedReader in = null;
        
        try {

            in = new BufferedReader(new FileReader(fileNameIn));
            int numYearsSampled = 0;
            String tmp;
            String line;
            while ((tmp = in.readLine()) != null) {

                line = tmp.toUpperCase();
                
                // Added  Dec 2013

                if (company == null && line.indexOf("STOCKCODE=") >= 0) {
                    final String[] tmpArr = line.split("=");
                    logger.debug("----- tmpArr[0] " + tmpArr[0] + " tmpArr[1] " + tmpArr[1]);
                    company = tmpArr[1];
                    logger.info("company {}", company);
                }

                if (companyName == null && line.indexOf("COMPANYNAME") >= 0) {
                    final String[] tmpArr = line.split("=");
                    logger.debug("----- tmpArr[0] " + tmpArr[0] + " tmpArr[1] " + tmpArr[1]);
                    companyName = tmpArr[1];
                    logger.info("companyName {}", companyName);
                }

                if (line.startsWith("Per Share".toUpperCase())) {
                    final String[] tmpArr = line.split(",");
                    numYearsSampled = tmpArr.length - 1;
                    if(!line.endsWith("TREND")) {
                        numYearsSampled ++;
                    }
                    logger.info("numYearsSampled: {}", numYearsSampled);
                    for (int i = 1; i < numYearsSampled; ++i) {
                        ++datesRank;
                        datesHm.put(Integer.valueOf(datesRank), tmpArr[i]);
                    }
                }

                if (line.indexOf("Average Annual P/E Ratio".toUpperCase()) >= 0) {
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        ++averageAnnualPERatioRank;
                        String tmp2 = tmpArr[i];
                        if (tmp2.indexOf(",") > 0) {
                            tmp2 = tmp2.replace(",", "");
                        }
                        if (tmp2.indexOf("--") >= 0) {
                            tmp2 = tmp2.replace("--", "0");
                        }
                        final BigDecimal tmp1BigDecimal = BigDecimalUtils.convertToBigDecimal(tmp2);
                        logger.info("averageAnnualPERatioHm: {} ", tmp1BigDecimal);
                        averageAnnualPERatioHm.put(Integer.valueOf(averageAnnualPERatioRank), tmp1BigDecimal + "");
                    }
                }

                if (line.startsWith("Shareholders Equity (m)".toUpperCase())) {
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        ++equityRank;
                        equityHm.put(Integer.valueOf(equityRank), tmpArr[i] + "M");
                        logger.info("equity: {} ", tmpArr[i]);
                        debtToEquityRatioStart = true;
                    }
                }
                if (line.startsWith("Long-Term Debt (m)".toUpperCase())) {
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        ++debtRank;
                        debtHm.put(Integer.valueOf(debtRank), tmpArr[i] + "M");
                        logger.info("long term debt: {} ", tmpArr[i]);
                        debtToEquityRatioEnd = true;
                    }
                }

                if (debtToEquityRatioStart && debtToEquityRatioEnd) {
                    for (int j = 1; j < numYearsSampled; ++j) {
                        final BigDecimal debt = BigDecimalUtils.convertToBigDecimal((String)debtHm.get(Integer.valueOf(j)));
                        final BigDecimal equity = BigDecimalUtils.convertToBigDecimal((String)equityHm.get(Integer.valueOf(j)));
                        logger.info("rank: {}, debt {}, eauity {}", j, debt, equity);
                        if (debt.compareTo(new BigDecimal(0)) == 0) {
                            debtToEquityRatioHm.put(Integer.valueOf(j), "0.0");
                        }
                        else if (equity.compareTo(new BigDecimal(0)) == 0) {
                            debtToEquityRatioHm.put(Integer.valueOf(j), "0.0");
                        }
                        else {
                            final BigDecimal tmp3 = debt.divide(equity, 2, RoundingMode.CEILING);
                            debtToEquityRatioHm.put(Integer.valueOf(j), tmp3 + "");
                        }
                    }
                }

                if (line.indexOf("Earnings ($)".toUpperCase()) >= 0) {
                    final String[] tmpArr = line.split(",");
                    for (int i = 1; i < numYearsSampled; ++i) {
                        ++epsRank;
                        epsHm.put(Integer.valueOf(epsRank), tmpArr[i] );
                        epsStart = true;
                    }
                }
                
            }
            
            in.close();
            in = null;
            
            Set<Integer> keySet0 = datesHm.keySet();
            Iterator<Integer> it0 = keySet0.iterator();
            while (it0.hasNext()){
            	Integer rank = it0.next();
            	if(averageAnnualPERatioHm.get(rank) == null) {
            		averageAnnualPERatioHm.put(rank, new BigDecimal("0") + "");
            	}
            	if(debtToEquityRatioHm.get(rank) == null) {
            		debtToEquityRatioHm.put(rank, new BigDecimal("0") + "");
            	}
            	if(epsHm.get(rank) == null) {
            		epsHm.put(rank, new BigDecimal("0") + "");
            	}
            }
            
            Set<Integer> keySet = datesHm.keySet();
            Iterator<Integer> it = keySet.iterator();
            while(it.hasNext()) {
                Integer rank = it.next();

                logger.debug("--------------- Processing rank " + rank + ", " + datesHm.get(rank));
                logger.debug("--------------- Processing averageAnnualPERatio of " + rank + ", " + averageAnnualPERatioHm.get(rank));
                logger.debug("--------------- Processing debtToEquityRatio of " + rank + ", " + debtToEquityRatioHm.get(rank));
                logger.debug("--------------- Processing eps of " + rank + ", " + epsHm.get(rank));

				CompanyDataOther companyDataOther = new CompanyDataOther(
						company, exchange, 
						BigDecimalUtils
								.convertToBigDecimal(averageAnnualPERatioHm
										.get(rank)),
						BigDecimalUtils.convertToBigDecimal(debtToEquityRatioHm
								.get(rank)), datesHm.get(rank),
						BigDecimalUtils.convertToBigDecimal(epsHm.get(rank)),
						companyName);
				companyDataOtherList.add(companyDataOther);
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
        } catch (Exception e3) {
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
        return companyDataOtherList;

    }
}
