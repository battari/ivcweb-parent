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
public class CompanyEvaluation {

    String company;
    String exchange;
    String date;
    BigDecimal intrinsicValue;
    BigDecimal intrinsicValuePerShare;
    BigDecimal requiredRate;

    public CompanyEvaluation() {
    }

    public CompanyEvaluation(String company, String exchange, String date, BigDecimal intrisicValue, BigDecimal intrinsicValuePerShare, BigDecimal requiredRate) {
        this.company = company;
        this.date = date;
        this.intrinsicValue = intrisicValue;
        this.intrinsicValuePerShare = intrinsicValuePerShare;
        this.requiredRate = requiredRate;
        this.exchange = exchange;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getIntrinsicValuePerShare() {
        return intrinsicValuePerShare;
    }

    public void setIntrinsicValuePerShare(BigDecimal intrinsicValuePerShare) {
        this.intrinsicValuePerShare = intrinsicValuePerShare;
    }

    public BigDecimal getRequiredRate() {
        return requiredRate;
    }

    public void setRequiredRate(BigDecimal requiredRate) {
        this.requiredRate = requiredRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getIntrinsicValue() {
        return intrinsicValue;
    }

    public void setIntrinsicValue(BigDecimal intrinsicValue) {
        this.intrinsicValue = intrinsicValue;
    }
    
    

    public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Override
	public String toString() {
		return "CompanyEvaluation [company=" + company + ", exchange="
				+ exchange + ", date=" + date + ", intrinsicValue="
				+ intrinsicValue + ", intrinsicValuePerShare="
				+ intrinsicValuePerShare + ", requiredRate=" + requiredRate
				+ "]";
	}
}
