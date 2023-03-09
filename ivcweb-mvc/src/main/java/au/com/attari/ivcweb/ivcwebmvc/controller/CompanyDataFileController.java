package au.com.attari.ivcweb.ivcwebmvc.controller;

import au.com.attari.ivcweb.ivcwebmvc.model.CompanyData;
import au.com.attari.ivcweb.ivcwebmvc.util.CompanyDataWriterUtil;
import au.com.attari.ivcweb.ivcwebmvc.util.HistoricalDataUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompanyDataFileController {

    @Value("${file.in.path}")
    String path;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute CompanyData companyData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("company-data");
        String tmp = HistoricalDataUtil.getInstance().parseFormat(companyData.getHistoricalData());
        companyData.setHistoricalData(tmp);
        CompanyDataWriterUtil.getInstance().writeFile(path, companyData);
        modelAndView.addObject("companyData", companyData);
        return modelAndView;
    }
}
