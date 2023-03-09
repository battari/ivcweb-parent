package  au.com.attari.ivcweb.ivcwebmvc.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CompanyData {
    @NotEmpty
    @Size(min = 3)
    String company;

    @NotEmpty
    @Size(min = 3)
    String exchange;

    @NotEmpty
    @Size(min = 3)
    String companyName;

    @NotEmpty
    @Size(min = 3)
    String historicalData;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHistoricalData() {
        return historicalData;
    }

    public void setHistoricalData(String historicalData) {
        this.historicalData = historicalData;
    }
}