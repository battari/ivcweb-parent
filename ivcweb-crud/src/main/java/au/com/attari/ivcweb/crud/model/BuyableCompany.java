package au.com.attari.ivcweb.crud.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.*;

@Entity
public class BuyableCompany {

    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;
    private String company;
    private String exchange;
    private String date;
    private BigDecimal intrinsicValuePerShare;
    private String requiredRate;
    private BigDecimal value;
    private String dateValue;

    public BuyableCompany() {
    }

    public BuyableCompany(int id, String company, String exchange, String date, BigDecimal intrinsicValuePerShare, String requiredRate, BigDecimal value, String dateValue) {
        this.id = id;
        this.company = company;
        this.exchange = exchange;
        this.date = date;
        this.intrinsicValuePerShare = intrinsicValuePerShare;
        this.requiredRate = requiredRate;
        this.value = value;
        this.dateValue = dateValue;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getIntrinsicValuePerShare() {
        return intrinsicValuePerShare;
    }

    public void setIntrinsicValuePerShare(BigDecimal intrinsicValuePerShare) {
        this.intrinsicValuePerShare = intrinsicValuePerShare;
    }

    public String getRequiredRate() {
        return requiredRate;
    }

    public void setRequiredRate(String requiredRate) {
        this.requiredRate = requiredRate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    @Override
    public String toString() {
        return "BuyableCompany{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", exchange='" + exchange + '\'' +
                ", date='" + date + '\'' +
                ", intrinsicValuePerShare=" + intrinsicValuePerShare +
                ", requiredRate='" + requiredRate + '\'' +
                ", value=" + value +
                ", dateValue='" + dateValue + '\'' +
                '}';
    }
}
