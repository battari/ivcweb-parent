package au.com.attari.ivcweb.crud.repository;

import au.com.attari.ivcweb.crud.model.CompanyValueHiLo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//repository that extends CrudRepository
public interface CompanyValueHiLoRepository extends CrudRepository<CompanyValueHiLo, Integer> {

    public List<CompanyValueHiLo> findByCompanyAndExchange(String company, String exchange);

    public List<CompanyValueHiLo> findByCompanyAndExchangeAndDate(String company, String exchange, String date);
    
}
