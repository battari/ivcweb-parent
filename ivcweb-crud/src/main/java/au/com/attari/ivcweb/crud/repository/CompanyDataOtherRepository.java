package au.com.attari.ivcweb.crud.repository;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//repository that extends CrudRepository
public interface CompanyDataOtherRepository extends CrudRepository<CompanyDataOther, Integer> {

    public List<CompanyDataOther> findByCompanyAndExchange(String company, String exchange);

    public List<CompanyDataOther> findByCompanyAndExchangeAndDate(String company, String exchange, String date);
}
