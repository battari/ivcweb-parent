package au.com.attari.ivcweb.crud.controller;

import java.util.List;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import au.com.attari.ivcweb.crud.model.CompanyData;
import au.com.attari.ivcweb.crud.service.CompanyDataService;
//mark class as Controller
@RestController
@RequestMapping(value = "/ivcweb/ivcweb-crud")
public class CompanyDataController
{
    Logger logger = LoggerFactory.getLogger(CompanyDataController.class);
    //autowire the CompanyDataService class
    @Autowired
    CompanyDataService companyDataService;

    //creating a get mapping that retrieves all the CompanyData detail from the database
    @GetMapping("/welcome")
    public String welcome() {
        logger.info("Calling welcome...");
        return "Welcome from CompanyData...";
    }

    @GetMapping("/company-datas")
    private List<CompanyData> getAllCompanyData() {
        logger.info("Calling getAllCompanyData...");
        return companyDataService.getAllCompanyData();
    }

    //creating a get mapping that retrieves the detail based stock and exchange
    @GetMapping("/company-data")
    private List<CompanyData> getCompanyDataByCompanyAndExchange(@RequestParam("company") String company, @RequestParam("exchange") String exchange,
                                                                 @RequestParam(name="date", required=false) String date) {
        if(date == null || date.length() ==0) {
            logger.info("Calling getAllCompanyDataByCompanyAndExchange...");
            return companyDataService.getCompanyDataByCompanyAndExchange(company, exchange);
        }
        else {
            logger.info("Calling getAllCompanyDataByCompanyAndExchange with Date...");
            return companyDataService.getCompanyDataByCompanyAndExchangeAndDate(company, exchange, date);
        }
    }

    @GetMapping("/company-names")
    private List<String> getDistinctCompanyDataByExchange(@RequestParam("exchange") String exchange) {
        logger.info("Calling getDistinctCompanyNamesByExchange {}...", exchange);
        return companyDataService.getDistinctCompanyNamesByExchange(exchange);
    }

    //creating a get mapping that retrieves the detail of a specific id
    @GetMapping("/company-data/{id}")
    private CompanyData getCompanyData(@PathVariable("id") int id) {
        logger.info("Calling getAllCompanyData by id {}...", id);
        return companyDataService.getCompanyDataById(id);
    }

    //creating a delete mapping that deletes a specified id
    @DeleteMapping("/company-data/{id}")
    private void deleteCompanyData(@PathVariable("id") int id) {
        logger.info("Calling deleteCompanyData by id {}...", id);
        companyDataService.delete(id);
    }

    //creating post mapping that post the Company Data detail in the database
    @PostMapping("/company-data")
    private int saveCompanyData(@RequestBody CompanyData companyData) {
        System.out.println("here ....");
        logger.info("Calling saveCompanyData...");
        companyDataService.saveOrUpdate(companyData);
        return companyData.getId();
    }

    //creating put mapping that updates the Company Data detail
    @PutMapping("/company-data")
    private CompanyData update(@RequestBody CompanyData companyData) {
        logger.info("Calling updateCompanyData...");
        companyDataService.saveOrUpdate(companyData);
        return companyData;
    }
}
