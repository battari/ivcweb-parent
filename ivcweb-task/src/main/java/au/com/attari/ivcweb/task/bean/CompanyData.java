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
public class CompanyData {

    String company;
    String exchange;
    BigDecimal  capitalization;
    BigDecimal equity;
    Long sharesIssued;
    String date;
    BigDecimal roe;
    BigDecimal payOutRatio;
    

    public CompanyData() {
        
    }

    public CompanyData(String company, String exchange, BigDecimal capitalization, BigDecimal equity, Long sharesIssued, String date, BigDecimal roe, BigDecimal payOutRatio) {

        this.company = company;
        this.exchange = exchange;
        this.capitalization = capitalization;
        this.equity = equity;
        this.sharesIssued = sharesIssued;
        this.date = date;
        this.roe = roe;
        this.payOutRatio = payOutRatio;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(BigDecimal capitalization) {
        this.capitalization = capitalization;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getEquity() {
        return equity;
    }

    public void setEquity(BigDecimal equity) {
        this.equity = equity;
    }

    public BigDecimal getPayOutRatio() {
        return payOutRatio;
    }

    public void setPayOutRatio(BigDecimal payOutRatio) {
        this.payOutRatio = payOutRatio;
    }

    public BigDecimal getRoe() {
        return roe;
    }

    public void setRoe(BigDecimal roe) {
        this.roe = roe;
    }

    public Long getSharesIssued() {
        return sharesIssued;
    }

    public void setSharesIssued(Long sharesIssued) {
        this.sharesIssued = sharesIssued;
    }

    public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Override
	public String toString() {
		return "CompanyData [company=" + company + ", exchange=" + exchange
				+ ", capitalization=" + capitalization + ", equity=" + equity
				+ ", sharesIssued=" + sharesIssued + ", date=" + date
				+ ", roe=" + roe + ", payOutRatio=" + payOutRatio + "]";
	}

	

}
