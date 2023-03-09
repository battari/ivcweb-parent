package au.com.attari.ivcweb.crud.service;

import au.com.attari.ivcweb.crud.model.CompanyEvaluation;
import au.com.attari.ivcweb.crud.model.CompanyEvaluation;
import au.com.attari.ivcweb.crud.repository.CompanyEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//defining the business logic
@Service
public class CompanyEvaluationService
{
    @Autowired
    CompanyEvaluationRepository companyEvaluationRepository;

    //getting all CompanyEvaluation record by using the method findaAll() of CrudRepository
    public List<CompanyEvaluation> getAllCompanyEvaluation() {
        List<CompanyEvaluation> companyEvaluation = new ArrayList<CompanyEvaluation>();
        companyEvaluationRepository.findAll().forEach(companyEvaluation1 -> companyEvaluation.add(companyEvaluation1));
        return companyEvaluation;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public CompanyEvaluation getCompanyEvaluationById(int id) {
        return companyEvaluationRepository.findById(id).get();
    }

    //getting a specific record by using the method findByCompanyAndExchange() of CrudRepository
    public List<CompanyEvaluation> getCompanyEvaluationByCompanyAndExchange(String company, String exchange) {
        return companyEvaluationRepository.findByCompanyAndExchange(company, exchange);
    }

    //getting a specific record by using the method findByCompanyAndExchangeAndDate() of CrudRepository
    public List<CompanyEvaluation> getCompanyEvaluationByCompanyAndExchangeAndDate(String company, String exchange, String date) {
        return companyEvaluationRepository.findByCompanyAndExchangeAndDate(company, exchange, date);
    }

    //getting a specific record by using the method findByCompanyAndExchangeAndsDateAndRequiredRate() of CrudRepository
    public List<CompanyEvaluation> getCompanyEvaluationByCompanyAndExchangeAndDateAndRequiredRate(String company, String exchange, String date, String requiredRate) {
        return companyEvaluationRepository.findByCompanyAndExchangeAndDateAndRequiredRate(company, exchange, date, requiredRate);
    }


    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(CompanyEvaluation CompanyEvaluation) {
        companyEvaluationRepository.save(CompanyEvaluation);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id) {
        companyEvaluationRepository.deleteById(id);
    }

    //updating a record
    public void update(CompanyEvaluation companyEvaluation, int id) {
        companyEvaluationRepository.save(companyEvaluation);
    }
}
