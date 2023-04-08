package au.com.attari.ivcweb.crud.controller;

import au.com.attari.ivcweb.crud.model.BuyableCompany;
import au.com.attari.ivcweb.crud.service.BuyableCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//mark class as Controller
@RestController
@RequestMapping(value = "/ivcweb/ivcweb-crud")
public class BuyableCompanyController {
    Logger logger = LoggerFactory.getLogger(BuyableCompanyController.class);
    //autowire the BuyableCompanyService class
    @Autowired
    BuyableCompanyService buyableCompanyService;

    @GetMapping("/buyable-companys")
    private List<BuyableCompany> getDistinctBuyableCompanyByExchange(@RequestParam("exchange") String exchange) {
        logger.info("Calling getByExchange {}...", exchange);
        return buyableCompanyService.getByExchange(exchange);
    }

}
