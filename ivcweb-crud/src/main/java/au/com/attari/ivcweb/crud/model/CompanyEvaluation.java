package au.com.attari.ivcweb.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table(name="company_evaluation")
public class CompanyEvaluation
{
    //Defining book id as primary key
    @Id
    @Column
    private int id;
    @Column
    private String company;
    @Column
    private String exchange;
    @Column(name="s_date")
    private String date;
    @Column(name="intrinsic_value")
    private String intrinsicValue;
    @Column(name="intrinsic_value_per_share")
    private String intrinsicValuePerShare;
    @Column(name="required_rate")
    private String requiredRate;
    @Column(name="generation_date")
    private Date generationDate;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
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

    public String getIntrinsicValue() {
        return intrinsicValue;
    }

    public void setIntrinsicValue(String intrinsicValue) {
        this.intrinsicValue = intrinsicValue;
    }

    public String getIntrinsicValuePerShare() {
        return intrinsicValuePerShare;
    }

    public void setIntrinsicValuePerShare(String intrinsicValuePerShare) {
        this.intrinsicValuePerShare = intrinsicValuePerShare;
    }

    public String getRequiredRate() {
        return requiredRate;
    }

    public void setRequiredRate(String requiredRate) {
        this.requiredRate = requiredRate;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    @Override
    public String toString() {
        return "CompanyData{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", exchange='" + exchange + '\'' +
                ", date='" + date + '\'' +
                ", capitalization='" + intrinsicValue + '\'' +
                ", equity='" + intrinsicValuePerShare + '\'' +
                ", sharesIssued='" + requiredRate + '\'' +
                ", payOutRatio='" + generationDate + '\'' +
                '}';
    }
}