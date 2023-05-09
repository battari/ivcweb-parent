package au.com.attari.ivcweb.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table(name="company_data_other")
public class CompanyDataOther
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
    @Column(name="company_name")
    private String companyName;
    @Column(name="average_annual_pe_ratio")
    private String averageAnnualPeRatio;
    @Column(name="debt_to_equity_ratio")
    private String debtToEquityRatio;
    @Column(name="earning_per_share")
    private String earningPerShare;

    public CompanyDataOther() {
    }

    public CompanyDataOther(int id, String company, String exchange, String date, String companyName, String averageAnnualPeRatio, String debtToEquityRatio, String earningPerShare) {
        this.id = id;
        this.company = company;
        this.exchange = exchange;
        this.date = date;
        this.companyName = companyName;
        this.averageAnnualPeRatio = averageAnnualPeRatio;
        this.debtToEquityRatio = debtToEquityRatio;
        this.earningPerShare = earningPerShare;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAverageAnnualPeRatio() {
        return averageAnnualPeRatio;
    }

    public void setAverageAnnualPeRatio(String averageAnnualPeRatio) {
        this.averageAnnualPeRatio = averageAnnualPeRatio;
    }

    public String getDebtToEquityRatio() {
        return debtToEquityRatio;
    }

    public void setDebtToEquityRatio(String debtToEquityRatio) {
        this.debtToEquityRatio = debtToEquityRatio;
    }

    public String getEarningPerShare() {
        return earningPerShare;
    }

    public void setEarningPerShare(String earningPerShare) {
        this.earningPerShare = earningPerShare;
    }

    @Override
    public String toString() {
        return "CompanyData{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", exchange='" + exchange + '\'' +
                ", date='" + date + '\'' +
                ", capitalization='" + companyName + '\'' +
                ", equity='" + averageAnnualPeRatio + '\'' +
                ", sharesIssued='" + debtToEquityRatio + '\'' +
                ", payOutRatio='" + earningPerShare + '\'' +
                '}';
    }
}