package au.com.attari.ivcweb.ivcwebmvc.util;

import au.com.attari.ivcweb.ivcwebmvc.model.CompanyData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyDataWriterUtil {

    private static final Logger logger = LoggerFactory.getLogger(CompanyDataWriterUtil.class);

    private CompanyDataWriterUtil() {

    }
    private static CompanyDataWriterUtil _instance = new CompanyDataWriterUtil();

    public static CompanyDataWriterUtil getInstance(){
        return _instance;
    }

    public void writeFile(String path, CompanyData inCompanyData) {

        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path + "\\" + inCompanyData.getCompany() + "-" + Calendar.getInstance().get(Calendar.YEAR) + ".csv"));
            StringBuffer companyStr = new StringBuffer("STOCKCODE=" + inCompanyData.getCompany() +"\n");
            os.write(companyStr.toString().getBytes(), 0, companyStr.length());
            StringBuffer companyNameStr = new StringBuffer("COMPANYNAME=" + inCompanyData.getCompanyName() +"\n");
            os.write(companyNameStr.toString().getBytes(), 0, companyNameStr.length());
            StringBuffer exchangeStr = new StringBuffer("EXCHANGE=" + inCompanyData.getExchange() +"\n");
            os.write(exchangeStr.toString().getBytes(), 0, exchangeStr.length());

            os.write(inCompanyData.getHistoricalData().getBytes(), 0, inCompanyData.getHistoricalData().length());

        } catch (IOException e) {
            logger.error("exception", e);
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                logger.error("exception", e);
            }
        }

    }
}
