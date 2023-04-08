package au.com.attari.ivcweb.crud.repository;

import au.com.attari.ivcweb.crud.model.CompanyValue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//repository that extends CrudRepository
public interface CompanyValueRepository extends CrudRepository<CompanyValue, Integer> {

    public List<CompanyValue> findByCompanyAndExchange(String company, String exchange);

}
