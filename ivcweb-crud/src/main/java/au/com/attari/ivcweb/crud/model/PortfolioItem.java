package au.com.attari.ivcweb.crud.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

import static javax.persistence.GenerationType.AUTO;
@Entity
//defining class name as Table name
@Table(name="portfolio")
public class PortfolioItem {
    @Id
    @Column
    private int id;
    @Column
    private String portfolioName;
    @Column
    private String company;
    @Column
    private String exchange;
    @Column
    private Date recommendationDate;
    @Column
    private BigDecimal requiredRate;
    @Column
    private BigDecimal actualPurchaseValue;
    @Column
    private int actualPurchaseCount;
    @Column
    private BigDecimal recommendedSellValue;
    @Column
    private String recommendation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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

    public Date getRecommendationDate() {
        return recommendationDate;
    }

    public void setRecommendationDate(Date recommendationDate) {
        this.recommendationDate = recommendationDate;
    }

    public BigDecimal getRequiredRate() {
        return requiredRate;
    }

    public void setRequiredRate(BigDecimal requiredRate) {
        this.requiredRate = requiredRate;
    }

    public BigDecimal getActualPurchaseValue() {
        return actualPurchaseValue;
    }

    public void setActualPurchaseValue(BigDecimal actualPurchaseValue) {
        this.actualPurchaseValue = actualPurchaseValue;
    }

    public int getActualPurchaseCount() {
        return actualPurchaseCount;
    }

    public void setActualPurchaseCount(int actualPurchaseCount) {
        this.actualPurchaseCount = actualPurchaseCount;
    }

    public BigDecimal getRecommendedSellValue() {
        return recommendedSellValue;
    }

    public void setRecommendedSellValue(BigDecimal recommendedSellValue) {
        this.recommendedSellValue = recommendedSellValue;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    @Override
    public String toString() {
        return "PortfolioItem{" +
                "id='" + id + '\'' +
                ", portfolioName='" + portfolioName + '\'' +
                ", company='" + company + '\'' +
                ", exchange='" + exchange + '\'' +
                ", recommendationDate=" + recommendationDate +
                ", requiredRate=" + requiredRate +
                ", actualPurchaseValue=" + actualPurchaseValue +
                ", actualPurchaseCount=" + actualPurchaseCount +
                ", recommendedSellValue=" + recommendedSellValue +
                ", recommendation='" + recommendation + '\'' +
                '}';
    }
}
