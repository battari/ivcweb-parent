package au.com.attari.ivcweb.crud.controller;

import au.com.attari.ivcweb.crud.model.CompanyValue;
import au.com.attari.ivcweb.crud.model.PortfolioItem;
import au.com.attari.ivcweb.crud.service.PortfolioItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//mark class as Controller
@RestController
@RequestMapping(value = "/ivcweb/ivcweb-crud")
public class PortfolioItemController {
    Logger logger = LoggerFactory.getLogger(PortfolioItemController.class);
    //autowire the PortfolioItemService class
    @Autowired
    PortfolioItemService portfolioItemService;

    @GetMapping("/portfolio-items")
    private List<PortfolioItem> getAllPortfolioItem() {
        logger.info("Calling getAllPortfolioItem {}...");
        return portfolioItemService.getAllPortfolioItem();
    }

    @DeleteMapping("/portfolio-item/{id}")
    private void deletePortfolioItem(@PathVariable("id") int id) {
        logger.info("Calling delete PortfolioItem by id {}...", id);
        portfolioItemService.delete(id);
    }

    //creating post mapping that post the Company Data (other) detail in the database
    @PostMapping("/portfolio-item")
    private int save(@RequestBody PortfolioItem portfolioItem) {
        System.out.println("here ....");
        logger.info("Calling save PortfolioItem...");
        portfolioItemService.saveOrUpdate(portfolioItem);
        return portfolioItem.getId();
    }

    //creating put mapping that updates the Company Data (other) detail
    @PutMapping("/portfolio-item")
    private PortfolioItem update(@RequestBody PortfolioItem portfolioItem) {
        logger.info("Calling saveOrUpdate PortfolioItem...");
        portfolioItemService.saveOrUpdate(portfolioItem);
        return portfolioItem;
    }

    @PatchMapping("/portfolio-item/{id}")
    private int update(@RequestBody PortfolioItem portfolioItem, @PathVariable("id") int id) {
        logger.info("Calling update PortfolioItem...");
        portfolioItem.setId(id);
        portfolioItemService.update(portfolioItem, id);
        return portfolioItem.getId();
    }
}
