package au.com.attari.ivcweb.crud.controller;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import au.com.attari.ivcweb.crud.model.CompanyEvaluation;
import au.com.attari.ivcweb.crud.service.CompanyEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

//mark class as Controller
@RestController
@RequestMapping(value = "/ivcweb/ivcweb-crud")
public class CompanyEvaluationController
{
    Logger logger = LoggerFactory.getLogger(CompanyEvaluationController.class);
    //autowire the CompanyEvaluationService class
    @Autowired
    CompanyEvaluationService companyEvaluationService;

    @GetMapping("/company-evaluations")
    private List<CompanyEvaluation> getAllCompanyEvaluation() {
        logger.info("Calling getAllCompanyEvaluation...");
        return companyEvaluationService.getAllCompanyEvaluation();
    }

    //creating a get mapping that retrieves the detail based stock and exchange
    @GetMapping("/company-evaluation")
    private List<CompanyEvaluation> getCompanyEvaluationByCompanyAndExchange(@RequestParam("company") String company, @RequestParam("exchange") String exchange,
        @RequestParam(name="date", required=false) String date, @RequestParam(name="rate", required=false) String requiredRate) {
        logger.info("Calling getAllCompanyEvaluationByCompanyAndExchange...");
        if((date == null || date.length() ==0) && (requiredRate == null || requiredRate.length() ==0)) {
            return companyEvaluationService.getCompanyEvaluationByCompanyAndExchange(company, exchange);
        } else if(date != null && date.length() > 0 && requiredRate != null && requiredRate.length() > 0) {
            logger.info("Calling getAllCompanyEvaluationByCompanyAndExchange with Date and requiredRate...");
            return companyEvaluationService.getCompanyEvaluationByCompanyAndExchangeAndDateAndRequiredRate(company, exchange, date, requiredRate);
        } else  if(date != null && date.length() > 0) {
            logger.info("Calling getAllCompanyEvaluationByCompanyAndExchangeAndsDate...");
            return companyEvaluationService.getCompanyEvaluationByCompanyAndExchangeAndDate(company, exchange, date);
        }
        else {
            // TODO: process not supported
            return companyEvaluationService.getCompanyEvaluationByCompanyAndExchange(company, exchange);
        }
    }

    //creating a get mapping that retrieves the detail of a specific id
    @GetMapping("/company-evaluation/{id}")
    private CompanyEvaluation getCompanyEvaluation(@PathVariable("id") int id) {
        return companyEvaluationService.getCompanyEvaluationById(id);
    }

    //creating a delete mapping that deletes a specified
    @DeleteMapping("/company-evaluation/{id}")
    private void deleteCompanyEvaluation(@PathVariable("id") int id) {
        logger.info("Calling getAllCompanyEvaluation by id {}...", id);
        companyEvaluationService.delete(id);
    }

    //creating post mapping that post the evaluation detail in the database
    @PostMapping("/company-evaluation")
    private int saveCompanyEvaluation(@RequestBody CompanyEvaluation companyEvaluation) {
        System.out.println("here ....");
        logger.info("Calling saveCompanyEvaluation...");
        companyEvaluationService.saveOrUpdate(companyEvaluation);
        return companyEvaluation.getId();
    }

    //creating put mapping that updates the evaluation detail
    @PutMapping("/company-evaluation")
    private CompanyEvaluation update(@RequestBody CompanyEvaluation companyEvaluation) {
        logger.info("Calling updateCompanyEvaluation...");
        companyEvaluationService.saveOrUpdate(companyEvaluation);
        return companyEvaluation;
    }
}
