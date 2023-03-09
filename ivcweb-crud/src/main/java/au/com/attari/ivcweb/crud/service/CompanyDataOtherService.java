package au.com.attari.ivcweb.crud.service;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import au.com.attari.ivcweb.crud.repository.CompanyDataOtherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//defining the business logic
@Service
public class CompanyDataOtherService
{
    @Autowired
    CompanyDataOtherRepository companyDataOtherRepository;

    //getting all companyDataOther record by using the method findaAll() of CrudRepository
    public List<CompanyDataOther> getAllCompanyDataOther() {
        List<CompanyDataOther> companyDataOther = new ArrayList<CompanyDataOther>();
        companyDataOtherRepository.findAll().forEach(companyDataOther1 -> companyDataOther.add(companyDataOther1));
        return companyDataOther;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public CompanyDataOther getCompanyDataOtherById(int id) {
        return companyDataOtherRepository.findById(id).get();
    }

    //getting a specific record by using the method findByCompanyAndExchange() of CrudRepository
    public List<CompanyDataOther> getCompanyDataOtherByCompanyAndExchange(String company, String exchange) {
        return companyDataOtherRepository.findByCompanyAndExchange(company, exchange);
    }

    //getting a specific record by using the method findByCompanyAndExchangeAndSDate() of CrudRepository
    public List<CompanyDataOther> getCompanyDataOtherByCompanyAndExchangeAndDate(String company, String exchange, String date) {
        return companyDataOtherRepository.findByCompanyAndExchangeAndDate(company, exchange, date);
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(CompanyDataOther companyDataOther) {
        companyDataOtherRepository.save(companyDataOther);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id) {
        companyDataOtherRepository.deleteById(id);
    }

    //updating a record
    public void update(CompanyDataOther companyDataOther, int id) {
        companyDataOtherRepository.save(companyDataOther);
    }

}
