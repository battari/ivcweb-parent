package au.com.attari.ivcweb.crud.controller;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import au.com.attari.ivcweb.crud.service.CompanyDataOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//mark class as Controller
@RestController
@RequestMapping(value = "/ivcweb/ivcweb-crud")
public class CompanyDataOtherController
{
    Logger logger = LoggerFactory.getLogger(CompanyDataOtherController.class);
    //autowire the CompanyDataService class
    @Autowired
    CompanyDataOtherService companyDataOtherService;

    @GetMapping("/company-data-others")
    private List<CompanyDataOther> getAllCompanyDataOther() {
        logger.info("Calling getAllCompanyDataOther...");
        return companyDataOtherService.getAllCompanyDataOther();
    }

    //creating a get mapping that retrieves the detail based stock and exchange
    @GetMapping("/company-data-other")
    private List<CompanyDataOther> getCompanyDataOtherByCompanyAndExchange(@RequestParam("company") String company, @RequestParam("exchange") String exchange,
                                                                           @RequestParam(name="date", required=false) String date) {
        if(date == null || date.length() ==0) {
            logger.info("Calling getAllCompanyDataOtherByCompanyAndExchange...");
            return companyDataOtherService.getCompanyDataOtherByCompanyAndExchange(company, exchange);
        }
        else {
            logger.info("Calling getAllCompanyDataOtherByCompanyAndExchange with Date...");
            return companyDataOtherService.getCompanyDataOtherByCompanyAndExchangeAndDate(company, exchange, date);
        }
    }

    //creating a get mapping that retrieves the detail of a specific Company Data (other)
    @GetMapping("/company-data-other/{id}")
    private CompanyDataOther getCompanyDataOther(@PathVariable("id") int id) {
        logger.info("Calling getAllCompanyDataOther by id {}...", id);
        return companyDataOtherService.getCompanyDataOtherById(id);
    }

    //creating a delete mapping that deletes a specified Company Data (other)
    @DeleteMapping("/company-data-other/{id}")
    private void deleteCompanyDataOther(@PathVariable("id") int id) {
        logger.info("Calling deleteCompanyDataOther by id {}...", id);
        companyDataOtherService.delete(id);
    }

    //creating post mapping that post the Company Data (other) detail in the database
    @PostMapping("/company-data-other")
    private int saveCompanyDataOther(@RequestBody CompanyDataOther companyDataOther) {
        System.out.println("here ....");
        logger.info("Calling saveCompanyData...");
        companyDataOtherService.saveOrUpdate(companyDataOther);
        return companyDataOther.getId();
    }

    //creating put mapping that updates the Company Data (other) detail
    @PutMapping("/company-data-other")
    private CompanyDataOther update(@RequestBody CompanyDataOther companyDataOther) {
        logger.info("Calling updateCompanyData...");
        companyDataOtherService.saveOrUpdate(companyDataOther);
        return companyDataOther;
    }
}
