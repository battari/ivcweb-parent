package au.com.attari.ivcweb.crud.service;

import au.com.attari.ivcweb.crud.model.CompanyValueHiLo;
import au.com.attari.ivcweb.crud.repository.CompanyValueHiLoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//defining the business logic
@Service
public class CompanyValueHiLoService
{
    @Autowired
    CompanyValueHiLoRepository companyValueHiLoRepository;

    //getting all CompanyValueHiLo record by using the method findaAll() of CrudRepository
    public List<CompanyValueHiLo> getAllCompanyValueHiLo() {
        List<CompanyValueHiLo> companyValueHiLo = new ArrayList<CompanyValueHiLo>();
        companyValueHiLoRepository.findAll().forEach(companyValueHiLo1 -> companyValueHiLo.add(companyValueHiLo1));
        return companyValueHiLo;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public CompanyValueHiLo getCompanyValueHiLoById(int id) {
        return companyValueHiLoRepository.findById(id).get();
    }

    //getting a specific record by using the method findByCompanyAndExchange() of CrudRepository
    public List<CompanyValueHiLo> getCompanyValueHiLoByCompanyAndExchange(String company, String exchange) {
        return companyValueHiLoRepository.findByCompanyAndExchange(company, exchange);
    }

    //getting a specific record by using the method findByCompanyAndExchangeAndsDate() of CrudRepository
    public List<CompanyValueHiLo> getCompanyValueHiLoByCompanyAndExchangeAndDate(String company, String exchange, String date) {
        return companyValueHiLoRepository.findByCompanyAndExchangeAndDate(company, exchange, date);
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(CompanyValueHiLo CompanyValueHiLo) {
        companyValueHiLoRepository.save(CompanyValueHiLo);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id) {
        companyValueHiLoRepository.deleteById(id);
    }

    //updating a record
    public void update(CompanyValueHiLo companyValueHiLo, int id) {
        companyValueHiLoRepository.save(companyValueHiLo);
    }
}
