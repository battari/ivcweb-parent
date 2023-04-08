package au.com.attari.ivcweb.crud.repository;

import au.com.attari.ivcweb.crud.model.BuyableCompany;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//repository that extends CrudRepository

public interface BuyableCompanyRepository extends NoBuyableCompanyRepository<BuyableCompany, Integer> {

    @Query(value = "select ce.id, ce.company, ce.exchange, ce.required_rate, ce.intrinsic_value_per_share, cv.day_value as value, \n" +
            "            ce.s_date as date, DATE_FORMAT(cv.datetime, '%Y/%m/%e') as date_value   from company_evaluation ce \n" +
            "inner join company_value cv \n" +
            "on ce.company = cv.company \n" +
            "and ce.exchange = cv.exchange\n" +
            "where ce.required_rate in ('0.08', '0.10', '0.12') \n" +
            "and substring(ce.s_date, 1,4) >= '2022'\n" +
            "and ce.exchange= :exchange\n" +
            "and cv.exchange=ce.exchange\n" +
            "having cast(ce.intrinsic_value_per_share as decimal) > cast(cv.day_value as decimal)"
            , nativeQuery = true)
    public List<BuyableCompany> findByExchange(String exchange);

}