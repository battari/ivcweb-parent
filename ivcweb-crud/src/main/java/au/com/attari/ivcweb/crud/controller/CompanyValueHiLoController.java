package au.com.attari.ivcweb.crud.controller;

import au.com.attari.ivcweb.crud.model.CompanyValueHiLo;
import au.com.attari.ivcweb.crud.service.CompanyValueHiLoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//mark class as Controller
@RestController
@RequestMapping(value = "/ivcweb/ivcweb-crud")
public class CompanyValueHiLoController
{
    Logger logger = LoggerFactory.getLogger(CompanyValueHiLoController.class);
    //autowire the CompanyValueHiLoService class
    @Autowired
    CompanyValueHiLoService companyValueHiLoService;

    @GetMapping("/company-valuehilos")
    private List<CompanyValueHiLo> getAllCompanyValueHiLo() {
        logger.info("Calling getAllCompanyValueHiLo...");
        return companyValueHiLoService.getAllCompanyValueHiLo();
    }

    //creating a get mapping that retrieves the detail based stock and exchange
    @GetMapping("/company-valuehilo")
    private List<CompanyValueHiLo> getCompanyValueHiLoByCompanyAndExchange(@RequestParam("company") String company, @RequestParam("exchange") String exchange,
                                                                 @RequestParam(name="date", required=false) String date) {
        if(date == null || date.length() ==0) {
            logger.info("Calling getAllCompanyValueHiLoByCompanyAndExchange...");
            return companyValueHiLoService.getCompanyValueHiLoByCompanyAndExchange(company, exchange);
        }
        else {
            logger.info("Calling getAllCompanyValueHiLoByCompanyAndExchange with Date...");
            return companyValueHiLoService.getCompanyValueHiLoByCompanyAndExchangeAndDate(company, exchange, date);
        }
    }

    //creating a get mapping that retrieves the detail of a specific id
    @GetMapping("/company-valuehilo/{id}")
    private CompanyValueHiLo getCompanyValueHiLo(@PathVariable("id") int id) {
        logger.info("Calling getAllCompanyValueHiLo by id {}...", id);
        return companyValueHiLoService.getCompanyValueHiLoById(id);
    }

    //creating a delete mapping that deletes a specified id
    @DeleteMapping("/company-valuehilo/{id}")
    private void deleteCompanyValueHiLo(@PathVariable("id") int id) {
        logger.info("Calling deleteCompanyValueHiLo by id {}...", id);
        companyValueHiLoService.delete(id);
    }

    //creating post mapping that post the Company ValueHiLo detail in the valuehilobase
    @PostMapping("/company-valuehilo")
    private int saveCompanyValueHiLo(@RequestBody CompanyValueHiLo companyValueHiLo) {
        System.out.println("here ....");
        logger.info("Calling saveCompanyValueHiLo...");
        companyValueHiLoService.saveOrUpdate(companyValueHiLo);
        return companyValueHiLo.getId();
    }

    //creating put mapping that updates the Company ValueHiLo detail
    @PutMapping("/company-valuehilo")
    private CompanyValueHiLo update(@RequestBody CompanyValueHiLo companyValueHiLo) {
        logger.info("Calling updateCompanyValueHiLo...");
        companyValueHiLoService.saveOrUpdate(companyValueHiLo);
        return companyValueHiLo;
    }
}
