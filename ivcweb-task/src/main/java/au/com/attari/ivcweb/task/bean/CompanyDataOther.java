/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.com.attari.ivcweb.task.bean;

import java.math.BigDecimal;
/**
 *
 * @author battari
 */
public class CompanyDataOther {

    String company;
    String exchange;
    String date;
    BigDecimal averageAnnualPeRatio;
    BigDecimal debtToEquityRatio;
    BigDecimal earningPerShare;
    String companyName;

    public CompanyDataOther() {
        
    }

    public CompanyDataOther(String company, String exchange, BigDecimal averageAnnualPeRatio, BigDecimal debtToEquityRatio, String date, BigDecimal earningPerShare, String companyName) {

        this.company = company;
        this.exchange = exchange;
        this.averageAnnualPeRatio = averageAnnualPeRatio;
        this.debtToEquityRatio = debtToEquityRatio;
        this.date = date;
        this.earningPerShare = earningPerShare;
        this.companyName = companyName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getAverageAnnualPeRatio() {
        return averageAnnualPeRatio;
    }

    public void setAverageAnnualPeRatio(BigDecimal averageAnnualPeRatio) {
        this.averageAnnualPeRatio = averageAnnualPeRatio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getDebtToEquityRatio() {
        return debtToEquityRatio;
    }

    public void setDebtToEquityRatio(BigDecimal debtToEquityRatio) {
        this.debtToEquityRatio = debtToEquityRatio;
    }

    public BigDecimal getEarningPerShare() {
        return earningPerShare;
    }
    
	public void setEarningPerShare(BigDecimal earningPerShare) {
        this.earningPerShare = earningPerShare;
	}

    public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Override
	public String toString() {
		return "CompanyDataOther [company=" + company 
                + ", exchange=" + exchange 
                + ", date=" + date 
                + ", averageAnnualPeRatio=" + averageAnnualPeRatio 
                + ", debtToEquityRatio=" + debtToEquityRatio 
                + ", earningPerShare=" + earningPerShare 
                + ", companyName=" + companyName + "]";
	} 
}
