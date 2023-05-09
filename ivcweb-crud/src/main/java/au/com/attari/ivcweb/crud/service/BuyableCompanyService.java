package au.com.attari.ivcweb.crud.service;

import au.com.attari.ivcweb.crud.model.BuyableCompany;
import au.com.attari.ivcweb.crud.repository.BuyableCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

//defining the business logic
@Service
public class BuyableCompanyService  {
    @Autowired
    BuyableCompanyRepository buyableCompanyRepository;

    public List<BuyableCompany> getByExchange(String exchange) {
        return buyableCompanyRepository.findByExchange(exchange, "" + (Calendar.getInstance().get(Calendar.YEAR)-1));
    }
}
