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
public class CompanyIntrinsicValue {
    String date;
    BigDecimal intrinsicValue;

    public CompanyIntrinsicValue(String date, BigDecimal intrisicValue) {
        this.date = date;
        this.intrinsicValue = intrisicValue;
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

    @Override
	public String toString() {
		return "CompanyIntrinsicValue [date=" + date + ", intrinsicValue="
				+ intrinsicValue + "]";
	}



}
