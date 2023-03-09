package au.com.attari.ivcweb.crud.repository;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import org.springframework.data.repository.CrudRepository;
import au.com.attari.ivcweb.crud.model.CompanyData;

import java.util.List;

//repository that extends CrudRepository
public interface CompanyDataRepository extends CrudRepository<CompanyData, Integer> {

    public List<CompanyData> findByCompanyAndExchange(String company, String exchange);

    public List<CompanyData> findByCompanyAndExchangeAndDate(String company, String exchange, String date);
}
