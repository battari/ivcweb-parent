package au.com.attari.ivcweb.task.bean;

public class CompanyValueHiLo {
    int id;
    String company;
    String exchange;
    String hiYearValue;
    String loYearYalue;
    String date;

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

    public String getHiYearValue() {
        return hiYearValue;
    }

    public void setHiYearValue(String hiYearValue) {
        this.hiYearValue = hiYearValue;
    }

    public String getLoYearYalue() {
        return loYearYalue;
    }

    public void setLoYearYalue(String loYearYalue) {
        this.loYearYalue = loYearYalue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return new StringBuffer(date)
                .append("~")
                .append(company)
                .append("~")
                .append(exchange).toString();
    }
}
