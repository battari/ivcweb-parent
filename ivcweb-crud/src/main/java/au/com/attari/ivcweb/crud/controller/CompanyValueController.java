package au.com.attari.ivcweb.crud.controller;

import au.com.attari.ivcweb.crud.model.CompanyValue;
import au.com.attari.ivcweb.crud.service.CompanyValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//mark class as Controller
@RestController
@RequestMapping(value = "/ivcweb/ivcweb-crud")
public class CompanyValueController
{
    Logger logger = LoggerFactory.getLogger(CompanyValueController.class);
    //autowire the CompanyDataService class
    @Autowired
    CompanyValueService companyValueService;

    @GetMapping("/company-values")
    private List<CompanyValue> getAllCompanyValue() {
        logger.info("Calling getAllCompanyValue...");
        return companyValueService.getAllCompanyValue();
    }

    //creating a get mapping that retrieves the detail based stock and exchange
    @GetMapping("/company-value")
    private List<CompanyValue> getCompanyValueByCompanyAndExchange(@RequestParam("company") String company,
                                                                   @RequestParam("exchange") String exchange
                                                                   ) {
            logger.info("Calling getAllCompanyValueByCompanyAndExchange ...");
            return companyValueService.getCompanyValueByCompanyAndExchange(company, exchange);
    }

    //creating a get mapping that retrieves the detail of a specific Company Data (other)
    @GetMapping("/company-value/{id}")
    private CompanyValue getCompanyValue(@PathVariable("id") int id) {
        logger.info("Calling getAllCompanyValue by id {}...", id);
        return companyValueService.getCompanyValueById(id);
    }

    //creating a delete mapping that deletes a specified Company Data (other)
    @DeleteMapping("/company-value/{id}")
    private void deleteCompanyValue(@PathVariable("id") int id) {
        logger.info("Calling deleteCompanyValue by id {}...", id);
        companyValueService.delete(id);
    }

    //creating post mapping that post the Company Data (other) detail in the database
    @PostMapping("/company-value")
    private int saveCompanyValue(@RequestBody CompanyValue companyValue) {
        logger.info("Calling saveCompanyData...");
        companyValueService.saveOrUpdate(companyValue);
        return companyValue.getId();
    }

    // Creating post mapping that inserts company values in bulk and retrieves processing time
    @PostMapping("/company-values")
    private long saveAllCompanyValue(@RequestBody List<CompanyValue> companyValueAl) {
        logger.info("Calling saveAllCompanyData...");
        long startTime = new Date().getTime();
        companyValueService.saveAll(companyValueAl);
        long endTime = new Date().getTime();
        return (endTime - startTime)/1000;
    }

    //creating put mapping that updates the Company Data (other) detail
    @PutMapping("/company-value")
    private CompanyValue update(@RequestBody CompanyValue companyValue) {
        logger.info("Calling updateCompanyData...");
        companyValueService.saveOrUpdate(companyValue);
        return companyValue;
    }
}
