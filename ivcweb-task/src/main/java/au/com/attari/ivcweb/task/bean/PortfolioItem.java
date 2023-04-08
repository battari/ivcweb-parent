package au.com.attari.ivcweb.task.bean;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class PortfolioItem {

    @NotEmpty
    @Size(min = 3)
    private int id;
    @NotEmpty
    @Size(min = 3)
    private String portfolioName;
    @NotEmpty
    @Size(min = 3)
    private String company;
    @NotEmpty
    @Size(min = 3)
    private String exchange;
    @NotEmpty
    @Size(min = 3)
    private Date recommendationDate;
    @NotEmpty
    @Size(min = 3)
    private BigDecimal requiredRate;
    @NotEmpty
    @Size(min = 3)
    private BigDecimal actualPurchaseValue;
    @NotEmpty
    @Size(min = 3)
    private int actualPurchaseCount;
    @NotEmpty
    @Size(min = 3)
    private BigDecimal recommendedSellValue;
    @Nullable
    private String recommendation;

    public PortfolioItem() {
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
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

