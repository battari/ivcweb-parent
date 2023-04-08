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
@Table(name = "company_value")
public class CompanyValue {

    @Id
    @Column
    private int id;
    @Column
    String company;
    @Column
    String exchange;
    @Column(name = "day_value")
    BigDecimal value;
    @Column(name = "datetime")
    private Date dateTime;


    public CompanyValue() {
    }

    public CompanyValue(int id, String company, String exchange, BigDecimal value, Date dateTime) {
        this.id = id;
        this.company = company;
        this.exchange = exchange;
        this.value = value;
        this.dateTime = dateTime;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "CompanyValue{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", exchange='" + exchange + '\'' +
                ", value=" + value +
                ", dateTime=" + dateTime +
                '}';
    }
}
