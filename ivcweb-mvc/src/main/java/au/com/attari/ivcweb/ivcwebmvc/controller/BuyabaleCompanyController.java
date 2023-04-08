package au.com.attari.ivcweb.ivcwebmvc.controller;

import au.com.attari.ivcweb.ivcwebmvc.model.CompanyData;
import au.com.attari.ivcweb.ivcwebmvc.util.CompanyDataWriterUtil;
import au.com.attari.ivcweb.ivcwebmvc.util.HistoricalDataUtil;
import au.com.attari.ivcweb.ivcwebmvc.webclient.BuyableCompanyWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuyabaleCompanyController {

    @Autowired
    private BuyableCompanyWebClient buyableCompanyWebClient;

    @RequestMapping(value="/buyable-companys", method = RequestMethod.GET)
    private String listBuyableCompanys(Model model){
        model.addAttribute("buyableCompanys", buyableCompanyWebClient.getByExchange("ASX"));
        return "buyable-companys";
    }
}
