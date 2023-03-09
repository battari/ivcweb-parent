package au.com.attari.ivcweb.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//mark class as an Entity
@Entity
//defining class name as Table name
@Table(name="company_data")
public class CompanyData
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
    @Column
    private String capitalization;
    @Column
    private String equity;
    @Column(name="shares_issued")
    private String sharesIssued;
    @Column
    private String roe;
    @Column(name="pay_out_ratio")
    private String payOutRatio;
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

    public void setDate(String sDate) {
        this.date = sDate;
    }

    public String getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(String capitalization) {
        this.capitalization = capitalization;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getSharesIssued() {
        return sharesIssued;
    }

    public void setSharesIssued(String sharesIssued) {
        this.sharesIssued = sharesIssued;
    }

    public String getRoe() {
        return roe;
    }

    public void setRoe(String roe) {
        this.roe = roe;
    }

    public String getPayOutRatio() {
        return payOutRatio;
    }

    public void setPayOutRatio(String payOutRatio) {
        this.payOutRatio = payOutRatio;
    }

    @Override
    public String toString() {
        return "CompanyData{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", exchange='" + exchange + '\'' +
                ", sDate='" + date + '\'' +
                ", capitalization='" + capitalization + '\'' +
                ", equity='" + equity + '\'' +
                ", sharesIssued='" + sharesIssued + '\'' +
                ", roe='" + roe + '\'' +
                ", payOutRatio='" + payOutRatio + '\'' +
                '}';
    }
}