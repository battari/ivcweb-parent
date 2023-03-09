/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.com.attari.ivcweb.task.business;

import au.com.attari.ivcweb.task.bean.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author battari
 */
public class Evaluator {

    private static Logger logger = LoggerFactory.getLogger(Evaluator.class);

    BigDecimal ROE_STEPS = new BigDecimal(0.025f);

    BigDecimal multiplierPayoutAll = new BigDecimal(0);
    BigDecimal multiplierPayoutNone = new BigDecimal(0);

    BigDecimal roeSelected = new BigDecimal(0);

    public CompanyIntrinsicValue evaluate(CompanyData companyData, BigDecimal requiredReturn) {

        roeSelected = getSelectedRoe(companyData);
        multiplierPayoutAll = getMultiplierPayoutAll(companyData, requiredReturn);
        multiplierPayoutNone = getMultiplierPayoutNone(companyData, requiredReturn);

        logger.info("Company {}, date {}, requiredReturn {}, oeSelected: {}", companyData.getCompany(), companyData.getDate(),requiredReturn, roeSelected);
        logger.debug("multiplierPayoutAll : " + multiplierPayoutAll );
        logger.debug("rmultiplierPayoutNone: " + multiplierPayoutNone);

        BigDecimal value = new BigDecimal(0);
        if(companyData.getPayOutRatio().compareTo(new BigDecimal(1)) == 0) { // companyData.getPayOutRatio() == 1
           value = multiplierPayoutAll.multiply(companyData.getEquity());
        }
        else if(companyData.getPayOutRatio().compareTo(new BigDecimal(0)) == 0) { // (companyData.getPayOutRatio() == 0
           value = multiplierPayoutNone.multiply(companyData.getEquity());
        }
        else {
        	// value = multiplierPayoutAll * companyData.getEquity() * companyData.getPayOutRatio() +
            //        multiplierPayoutNone * companyData.getEquity() * (1 - companyData.getPayOutRatio());
            value = (multiplierPayoutAll.multiply(companyData.getEquity()).multiply(companyData.getPayOutRatio()))
            		.add(
            				(multiplierPayoutNone.multiply(companyData.getEquity()).multiply(new BigDecimal(1).subtract(companyData.getPayOutRatio())))
            		);
            value = value.setScale(2, RoundingMode.CEILING);
        }

        logger.debug("---- value:" + value);

        CompanyIntrinsicValue companyEvaluation = new CompanyIntrinsicValue(companyData.getDate(), value);

        logger.debug("---- companyEvaluation:" + companyEvaluation);
        
        return companyEvaluation;
    }

    BigDecimal getSelectedRoe(CompanyData companyData) {
    	
    	logger.debug("select roe");
    	
        if(companyData.getRoe().compareTo(new BigDecimal("0.05")) <= 0) {
            roeSelected = new BigDecimal("0.05");
        } else if(companyData.getRoe().compareTo(new BigDecimal(0.6)) >= 0) {
            roeSelected = new BigDecimal(0.6f);
        } else {
        	
            // A way of avoiding BigDecimal inprecision
            BigDecimal fArr[] = {new BigDecimal("0.05"), 
            		new BigDecimal("0.075"), 
            		new BigDecimal("0.1"), 
            		new BigDecimal("0.125"), 
            		new BigDecimal("0.15"), 
            		new BigDecimal("0.175"), 
            		new BigDecimal("0.2"), 
            		new BigDecimal("0.225"),
            		new BigDecimal("0.25"), 
            		new BigDecimal("0.275"), 
            		new BigDecimal("0.3"), 
            		new BigDecimal("0.325"), 
            		new BigDecimal("0.35"), 
            		new BigDecimal("0.375"), 
            		new BigDecimal("0.4"), 
            		new BigDecimal("0.425"), 
            		new BigDecimal("0.45"), 
            		new BigDecimal("0.475"), 
            		new BigDecimal("0.5"), 
            		new BigDecimal("0.525"), 
            		new BigDecimal("0.55"), 
            		new BigDecimal("0.575"), 
            		new BigDecimal("0.6")};
            int i = 0;
            BigDecimal f = fArr[0];
            for (;f.compareTo(fArr[fArr.length -1]) < 0;) { // f < fArr[fArr.length -1]
            	
                BigDecimal fLow = fArr[i];
                BigDecimal fHigh = fArr[i+1];
                logger.debug("companyRoe" + companyData.getRoe() + "f: " + f + " fLow: " + fLow + " fHigh: " + fHigh);
                // System.out.println("fLow: " + fLow + " fHigh: " + fHigh);
                if (companyData.getRoe().compareTo(fLow) >=0 && companyData.getRoe().compareTo(fHigh) < 0) { // companyData.getRoe() >= fLow && companyData.getRoe() < fHigh
                    roeSelected = fLow;
                	logger.debug("selected 1: " + roeSelected);
                    if ( (fHigh.subtract(companyData.getRoe())).compareTo((companyData.getRoe().subtract(fLow))) < 0) { // (fHigh - companyData.getRoe()) < (companyData.getRoe() - fLow)
                        roeSelected = fHigh;
                        logger.debug("selected 2: " + roeSelected);
                        
                    }
                }
                ++i;
                f = fArr[i];

            }
        }

        logger.debug("roeSelected : " + roeSelected);

        return roeSelected;

    }

    BigDecimal getMultiplierPayoutAll(CompanyData companyData, BigDecimal requiredReturn) {
    	
    	logger.debug(" getMultiplierPayoutAll started");

        if (roeSelected.compareTo(new BigDecimal(0)) == 0) {
            getSelectedRoe(companyData);
        }

        int selectedRow = 0;
        for (int row = 0; row < MultiplierPayoutAll.multipliers.length; ) {
            if(roeSelected.compareTo(MultiplierPayoutAll.multipliers[row][0]) == 0) { // roeSelected.BigDecimalValue() == MultiplierPayoutAll.multipliers[row][0]
                selectedRow = row;
                break;
            }
            ++row;
        }

        int selectedColumn = 0;
        for(int column = 0; column < MultiplierPayoutAll.multipliers[0].length;) {
            if(requiredReturn.compareTo(MultiplierPayoutAll.multipliers[0][column]) == 0) { // requiredReturn.BigDecimalValue() == MultiplierPayoutAll.multipliers[0][column]
                selectedColumn = column;
                break;
            }
            ++ column;
        }
        
        logger.debug(" getMultiplierPayoutAll: " + MultiplierPayoutAll.multipliers[selectedRow][selectedColumn]);

        return MultiplierPayoutAll.multipliers[selectedRow][selectedColumn];

    }

    BigDecimal getMultiplierPayoutNone(CompanyData companyData, BigDecimal requiredReturn) {
    	
    	logger.debug("getMultiplierPayoutNone started");

        if (roeSelected.compareTo(new BigDecimal(0)) == 0) { // roeSelected == 0 
            getSelectedRoe(companyData);
        }

        int selectedRow = 0;
        for (int row = 0; row < MultiplierPayoutNone.multipliers.length; ) {
            if(roeSelected.compareTo(MultiplierPayoutNone.multipliers[row][0]) == 0) { // roeSelected.BigDecimalValue() == MultiplierPayoutNone.multipliers[row][0]
                selectedRow = row;
                break;
            }
            ++row;
        }

        int selectedColumn = 0;
        for(int column = 0; column < MultiplierPayoutNone.multipliers[0].length;) {
            if(requiredReturn.compareTo(MultiplierPayoutNone.multipliers[0][column]) == 0) { // roeSelected.BigDecimalValue() == MultiplierPayoutNone.multipliers[row][0]
                selectedColumn = column;
                break;
            }
            ++ column;
        }
        
        logger.debug("getMultiplierPayoutNone: " + MultiplierPayoutNone.multipliers[selectedRow][selectedColumn]);

        return MultiplierPayoutNone.multipliers[selectedRow][selectedColumn];

    }

}
