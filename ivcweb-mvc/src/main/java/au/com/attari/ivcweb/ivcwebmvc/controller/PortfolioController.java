package au.com.attari.ivcweb.ivcwebmvc.controller;

import au.com.attari.ivcweb.ivcwebmvc.model.PortfolioData;
import au.com.attari.ivcweb.ivcwebmvc.model.PortfolioItem;
import au.com.attari.ivcweb.ivcwebmvc.util.HistoricalDataUtil;
import au.com.attari.ivcweb.ivcwebmvc.util.PortfolioUtil;
import au.com.attari.ivcweb.ivcwebmvc.webclient.PortfolioItemWebClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PortfolioController {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    @Value("${file.in.path}")
    String path;

    @Autowired
    private PortfolioItemWebClient portfolioItemWebClient;

    @RequestMapping("/portfolio-data")
    public String index(Model model) {
        return "portfolio-data";
    }

    @RequestMapping(value = "/portfolio-post", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute PortfolioData portfolioData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("portfolio-items");
        List<PortfolioItem> suppliedL = PortfolioUtil.getInstance().scrape(
                portfolioData.getRawPortfolioItems(),
                portfolioData.getPortfolioName(),
                portfolioData.getExchange());
        portfolioData.setPorfolioItems(suppliedL);
        logger.info("suppliedL.size(): {}", suppliedL.size());
        List<PortfolioItem> tmpL = new ObjectMapper().convertValue(portfolioItemWebClient.getAll(), new TypeReference<List<PortfolioItem>>(){});
        List<PortfolioItem> currentL = tmpL.stream().filter(item -> item.getPortfolioName().equalsIgnoreCase(portfolioData.getPortfolioName())).collect(Collectors.toList());
        logger.info("currentL.size(): {}", currentL.size());

        // 1 - delete those that have been removed.
        List<PortfolioItem>  deletableL = getDeletableList(suppliedL, currentL);
        deletableL.stream().forEach(item -> portfolioItemWebClient.delete(item.getId()));

        // 2 - Post those that are new
        List<PortfolioItem>  addableL = getAddableList(suppliedL, currentL);
        addableL.stream().filter(item -> item.getCompany() != null).forEach(item -> portfolioItemWebClient.save(item));

        // todo call l.forEach(PortfolioItemWebclient)
        modelAndView.addObject("portfolioData", portfolioData);
        return modelAndView;
    }

    private  List<PortfolioItem> getDeletableList(List<PortfolioItem> suppliedL, List<PortfolioItem> currentL) {
        List<PortfolioItem> deletableL = new ArrayList<>();
        logger.info("........... suppliedL.size(): {}  .......... currentL.size(): {} ", suppliedL.size(), currentL.size() );
        for(PortfolioItem c: currentL) {
            boolean found = false;
            for(PortfolioItem s: suppliedL) {
                if(c.getCompany().equalsIgnoreCase(s.getCompany()) &&
                c.getExchange().equalsIgnoreCase(s.getExchange())) {
                    found = true;
                    break;
                }
            }
            if(found) {
                found = false;
            }
            else {
                deletableL.add(c);
            }
        }
        return deletableL;
    }

    private  List<PortfolioItem> getAddableList(List<PortfolioItem> suppliedL, List<PortfolioItem> currentL) {
        List<PortfolioItem> addableL = new ArrayList<>();
        for(PortfolioItem s: suppliedL) {
            boolean found = false;
            for(PortfolioItem c: currentL) {
                if(c.getCompany().equalsIgnoreCase(s.getCompany()) &&
                        c.getExchange().equalsIgnoreCase(s.getExchange())) {
                    found = true;
                    break;
                }
            }
            if(found) {
                found = false;
            }
            else {
                if(s.getCompany() != null) {
                    // A null getting parsed don't add it.
                    addableL.add(s);
                }
            }
        }
        return addableL;
    }
}
