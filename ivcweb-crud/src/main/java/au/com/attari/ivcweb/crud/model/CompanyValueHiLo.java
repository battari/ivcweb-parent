/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.attari.ivcweb.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
//defining class name as Table name
@Table(name = "company_value_hi_lo")
public class CompanyValueHiLo {

    @Id
    @Column
    private int id;
    @Column
    String company;
    @Column
    String exchange;
    @Column
    BigDecimal hiYearValue;
    @Column
    BigDecimal loYearValue;
    @Column(name="s_date")
    private String date;


    public CompanyValueHiLo() {
    }

    public CompanyValueHiLo(int id, String company, String exchange, BigDecimal hiYearValue, BigDecimal loYearValue, String date) {
        this.id = id;
        this.company = company;
        this.exchange = exchange;
        this.hiYearValue = hiYearValue;
        this.loYearValue = loYearValue;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public BigDecimal getHiYearValue() {
        return hiYearValue;
    }

    public void setHiYearValue(BigDecimal hiYearValue) {
        this.hiYearValue = hiYearValue;
    }

    public BigDecimal getLoYearValue() {
        return loYearValue;
    }

    public void setLoYearValue(BigDecimal loYearValue) {
        this.loYearValue = loYearValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CompanyValueHiLo{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", exchange='" + exchange + '\'' +
                ", hiYearValue=" + hiYearValue +
                ", loYearValue=" + loYearValue +
                ", date='" + date + '\'' +
                '}';
    }
}
