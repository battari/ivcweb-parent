package au.com.attari.ivcweb.crud.repository;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import au.com.attari.ivcweb.crud.model.CompanyData;

import java.util.List;

//repository that extends CrudRepository
public interface CompanyDataRepository extends CrudRepository<CompanyData, Integer> {

    public List<CompanyData> findByCompanyAndExchange(String company, String exchange);

    public List<CompanyData> findByCompanyAndExchangeAndDate(String company, String exchange, String date);

    @Query(value = "SELECT distinct company FROM company_data cd WHERE cd.exchange = ?1 ORDER BY company ASC", nativeQuery = true)
    public  List<String> findDistinctCompanyNamesByExchange(String exchange);
}
