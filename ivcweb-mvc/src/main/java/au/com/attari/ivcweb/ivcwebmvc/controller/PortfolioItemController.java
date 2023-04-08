package au.com.attari.ivcweb.ivcwebmvc.controller;

import au.com.attari.ivcweb.ivcwebmvc.model.CompanyData;
import au.com.attari.ivcweb.ivcwebmvc.util.CompanyDataWriterUtil;
import au.com.attari.ivcweb.ivcwebmvc.util.HistoricalDataUtil;
import au.com.attari.ivcweb.ivcwebmvc.webclient.PortfolioItemWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PortfolioItemController {

    @Autowired
    private PortfolioItemWebClient portfolioItemWebClient;

    @RequestMapping(value="/portfolio-items", method = RequestMethod.GET)
    private String listPortfolioItems(Model model){
        model.addAttribute("portfolioItems", portfolioItemWebClient.getAll());
        return "portfolio-items";
    }

}
