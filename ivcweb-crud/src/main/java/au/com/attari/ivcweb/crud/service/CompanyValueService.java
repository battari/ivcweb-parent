package au.com.attari.ivcweb.crud.service;

import au.com.attari.ivcweb.crud.model.CompanyValue;
import au.com.attari.ivcweb.crud.repository.CompanyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

//defining the business logic
@Service
public class CompanyValueService
{
    @Autowired
    CompanyValueRepository companyValueRepository;

    //getting all companyValue record by using the method findaAll() of CrudRepository
    public List<CompanyValue> getAllCompanyValue() {
        List<CompanyValue> companyValue = new ArrayList<CompanyValue>();
        companyValueRepository.findAll().forEach(companyValue1 -> companyValue.add(companyValue1));
        return companyValue;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public CompanyValue getCompanyValueById(int id) {
        return companyValueRepository.findById(id).get();
    }

    //getting a specific record by using the method findByCompanyAndExchange() of CrudRepository
    public List<CompanyValue> getCompanyValueByCompanyAndExchange(String company, String exchange) {
        return companyValueRepository.findByCompanyAndExchange(company, exchange);
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(CompanyValue companyValue) {
        companyValueRepository.save(companyValue);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id) {
        companyValueRepository.deleteById(id);
    }

    //updating a record
    public void update(CompanyValue companyValue, int id) {
        companyValueRepository.save(companyValue);
    }

    public void saveAll(List<CompanyValue> companyValueAl) {
        companyValueRepository.saveAll(companyValueAl);
    }

}
