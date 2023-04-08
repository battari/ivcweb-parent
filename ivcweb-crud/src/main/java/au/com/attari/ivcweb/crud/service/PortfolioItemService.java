package au.com.attari.ivcweb.crud.service;

import au.com.attari.ivcweb.crud.model.CompanyData;
import au.com.attari.ivcweb.crud.model.CompanyValue;
import au.com.attari.ivcweb.crud.model.PortfolioItem;
import au.com.attari.ivcweb.crud.repository.PortfolioItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//defining the business logic
@Service
public class PortfolioItemService {
    @Autowired
    PortfolioItemRepository portfolioItemRepository;

    public List<PortfolioItem> getAllPortfolioItem() {
        List<PortfolioItem> portfolioItems = new ArrayList();
        portfolioItemRepository.findAll().forEach(portfolioItems::add);
        return portfolioItems;
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(PortfolioItem portfolioItem) {
        portfolioItemRepository.save(portfolioItem);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id) {
        portfolioItemRepository.deleteById(id);
    }

    public void update(PortfolioItem portfolioItem, int id) {
        portfolioItemRepository.save(portfolioItem);
    }

}
