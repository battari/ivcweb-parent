package au.com.attari.ivcweb.ivcwebmvc.util;

import au.com.attari.ivcweb.ivcwebmvc.model.CompanyData;
import au.com.attari.ivcweb.ivcwebmvc.model.PortfolioItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class PortfolioUtil {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioUtil.class);

    private PortfolioUtil() {

    }
    private static PortfolioUtil _instance = new PortfolioUtil();

    public static PortfolioUtil getInstance(){
        return _instance;
    }

    public List<PortfolioItem> scrape(String rawData, String portfolioName, String exchange) {
        System.out.println("............");
        // System.out.println(rawData);
        ArrayList<PortfolioItem> pIL = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(rawData,"\r\n");
        boolean created = false;
        PortfolioItem pI = null;
        while(st.hasMoreElements()) {
            if(created == false) {
                pI = new PortfolioItem();
                pI.setExchange(exchange);
                pI.setRequiredRate(new BigDecimal("0.08")); // use this as default.
                pI.setPortfolioName(portfolioName);
                created = true;
            }
            String tmp = st.nextToken();
            byte b[] = tmp.getBytes(StandardCharsets.UTF_8);
            for(byte c: b) {
                System.out.print(c);
            }
            System.out.println("\n" + tmp + "...." + tmp.length());
            if(tmp.length() == 3 && tmp.matches("^[a-zA-Z0-9]+$")) {
                System.out.println("....comp: " + tmp.length());
                pI.setCompany(tmp);
            }
            else if(tmp.indexOf("View units breakdown") > 0) {
                String[] split = tmp.replaceAll("View units breakdown", "").replaceAll(",", "").split("\\s+");
                System.out.println("....split[0]: " +split[0]);
                pI.setActualPurchaseCount(Integer.valueOf(split[0]));
                pI.setActualPurchaseValue(new BigDecimal(split[1]));
            }
            else if(tmp.indexOf("TradeView more") >= 0) {
                created = false;
                pIL.add(pI);
                pI = null;
            }
        }

        logger.info("Parsed for portfolio {} and obtained {} items", portfolioName, pIL.size());
        pIL.stream().forEach(System.out::println);

        return pIL;
    }
}
