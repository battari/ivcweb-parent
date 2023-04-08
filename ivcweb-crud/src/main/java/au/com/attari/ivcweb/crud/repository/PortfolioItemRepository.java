package au.com.attari.ivcweb.crud.repository;


import au.com.attari.ivcweb.crud.model.PortfolioItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//repository that extends CrudRepository
public interface PortfolioItemRepository extends CrudRepository<PortfolioItem, Integer> {


}
