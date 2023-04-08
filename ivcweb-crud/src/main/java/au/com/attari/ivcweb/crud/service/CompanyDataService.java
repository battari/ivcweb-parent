package au.com.attari.ivcweb.crud.service;

import java.util.ArrayList;
import java.util.List;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.com.attari.ivcweb.crud.model.CompanyData;
import au.com.attari.ivcweb.crud.repository.CompanyDataRepository;
//defining the business logic
@Service
public class CompanyDataService
{
    @Autowired
    CompanyDataRepository companyDataRepository;

    //getting all CompanyData record by using the method findaAll() of CrudRepository
    public List<CompanyData> getAllCompanyData() {
        List<CompanyData> companyData = new ArrayList<CompanyData>();
        companyDataRepository.findAll().forEach(companyData1 -> companyData.add(companyData1));
        return companyData;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public CompanyData getCompanyDataById(int id) {
        return companyDataRepository.findById(id).get();
    }

    //getting a specific record by using the method findByCompanyAndExchange() of CrudRepository
    public List<CompanyData> getCompanyDataByCompanyAndExchange(String company, String exchange) {
        return companyDataRepository.findByCompanyAndExchange(company, exchange);
    }

    //getting a specific record by using the method findByCompanyAndExchangeAndsDate() of CrudRepository
    public List<CompanyData> getCompanyDataByCompanyAndExchangeAndDate(String company, String exchange, String date) {
        return companyDataRepository.findByCompanyAndExchangeAndDate(company, exchange, date);
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(CompanyData CompanyData) {
        companyDataRepository.save(CompanyData);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id) {
        companyDataRepository.deleteById(id);
    }

    //updating a record
    public void update(CompanyData companyData, int id) {
        companyDataRepository.save(companyData);
    }

    public List<String> getDistinctCompanyNamesByExchange(String exchange) {
         return companyDataRepository.findDistinctCompanyNamesByExchange(exchange);
    }
}
