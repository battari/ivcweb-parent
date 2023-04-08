package au.com.attari.ivcweb.ivcwebmvc.model;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class PortfolioData {
    @NotEmpty
    @Size(min = 3)
    String exchange;

    @NotEmpty
    @Size(min = 3)
    private String portfolioName;

    @Nullable
    @Size(min = 3)
    private String rawPortfolioItems;

    List<PortfolioItem> porfolioItems;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public List<PortfolioItem> getPorfolioItems() {
        return porfolioItems;
    }

    public void setPorfolioItems(List<PortfolioItem> porfolioItems) {
        this.porfolioItems = porfolioItems;
    }

    public String getRawPortfolioItems() {
        return rawPortfolioItems;
    }

    public void setRawPortfolioItems(String rawPortfolioItems) {
        this.rawPortfolioItems = rawPortfolioItems;
    }

    @Override
    public String toString() {
        return "PortfolioData{" +
                "exchange='" + exchange + '\'' +
                ", portfolioName='" + portfolioName + '\'' +
                ", porfolioItems=" + porfolioItems +
                '}';
    }
}
