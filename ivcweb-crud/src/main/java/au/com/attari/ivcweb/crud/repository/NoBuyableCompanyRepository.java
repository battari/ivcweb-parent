package au.com.attari.ivcweb.crud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NoBuyableCompanyRepository<BuyableCompany, Integer> extends CrudRepository<BuyableCompany, Integer> {
}
