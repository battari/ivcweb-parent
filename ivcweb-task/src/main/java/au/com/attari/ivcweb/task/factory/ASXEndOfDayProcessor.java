package au.com.attari.ivcweb.task.factory;

import au.com.attari.ivcweb.task.bean.*;
import au.com.attari.ivcweb.task.business.CompanyDataOtherReader;
import au.com.attari.ivcweb.task.business.CompanyDataReader;
import au.com.attari.ivcweb.task.business.Evaluator;
import au.com.attari.ivcweb.task.utils.FileUtils;
import au.com.attari.ivcweb.task.webclient.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.*;

@Component
public class ASXEndOfDayProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ASXEndOfDayProcessor.class);

	@Value("${exchange}")
	private final String exchange = "ASX";

	@Value("${file.in-endofday.path}")
    private String inPath;

	@Value("${file.archive-endofday.path}")
    private String archivePath = "";

	private static HashMap<String, CompanyValueHiLo> companyValueHiLoMap = new HashMap<>(); // Key is of form year~company~exchange

	SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy",
			Locale.ENGLISH);

	@Autowired
    private CompanyValueWebClient companyValueWebClient;

	@Autowired
	private CompanyValueHiLoWebClient companyValueHiLoWebClient;

	@Autowired
	private CompanyNamesProcessor companyNamesProcessor;

	public void process() {

        logger.info("[process] started ...");

        logger.debug("[process] files from dir " + inPath);

        String files[] = new FileUtils().getFileList(inPath);

        // Any files for processing.
        if(files == null || files.length == 0) {
            logger.error("[process] no files found for processing...");
        }
        else {
            for(String file: files) {
                logger.error("[process] processing file:" + file);
            }
        }

        // do the processing here
        for (String file : files) {
			logger.debug("[process] processing CompanyValue from file: " + file);

			FileReader reader = null;
			CSVParser parser = null;
			try {
				reader = new FileReader(inPath + "\\" + file);
				parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());

				for (CSVRecord csvRecord : parser) {
					String company = csvRecord.get("Security Code");
					if(companyNamesProcessor.getCompanyNames().containsKey(company)) {
						logger.info("End of pay Company: {}, price: {} and Date: {}",
								company, csvRecord.get("Closing Price"), csvRecord.get("Date"));
					}
					else {
						logger.info("Not processing Company: {}, price: {} and Date: {}",
								company, csvRecord.get("Closing Price"), csvRecord.get("Date"));
						continue;
					}
					long d = getDate(csvRecord.get("Date")).getTime();
					int year = prvGetCalendarYear(d);
					if(year == 2021) {
						int i = 0;
						i++;
					}
					long currentD = new Date().getTime();
                    String closingPrice = csvRecord.get("Closing Price");
					if(currentD - d <= 3 * 60 * 60 * 1000 * 24) { // 3 days to cater for weekends
						CompanyValue companyValue = new CompanyValue();
						companyValue.setCompany(company);
                        companyValue.setValue(closingPrice);
						companyValue.setDateTime(new java.sql.Date(d));
						companyValue.setExchange(exchange);
						companyValueWebClient.create(companyValue);
					}

                    String key = prvGetKey(year, company, exchange);
                    if (companyValueHiLoMap.get(key) == null ) {
                        CompanyValueHiLo tmp = new CompanyValueHiLo();
                        tmp.setCompany(company);
                        tmp.setExchange(exchange);
						tmp.setDate(String.valueOf(year));
                        Map<String, CompanyValueHiLo> tmpMap = companyValueHiLoWebClient.getByCompanyAndDate(tmp)
                                .stream()
                                .collect(Collectors.toMap(
                                        CompanyValueHiLo::getKey,
                                        Function.identity()
                                ));
                        if(!tmpMap.isEmpty())
                            companyValueHiLoMap.putAll(tmpMap);
                    }
                    CompanyValueHiLo cVHL = new CompanyValueHiLo();
                    boolean post = false;
                    if(companyValueHiLoMap.get(key) != null) {
                        cVHL= companyValueHiLoMap.get(key);
                        if(new BigDecimal(cVHL.getHiYearValue()).compareTo(new BigDecimal(closingPrice)) < 0) {
                            cVHL.setHiYearValue(closingPrice);
							companyValueHiLoWebClient.update(cVHL,cVHL.getId());
                        }
                        else if(new BigDecimal(cVHL.getLoYearValue()).compareTo(new BigDecimal(closingPrice)) > 0) {
                            cVHL.setLoYearValue(closingPrice);
							companyValueHiLoWebClient.update(cVHL,cVHL.getId());
                        }
                    }
                    else {
                        cVHL.setCompany(company);
                        cVHL.setExchange(exchange);
                        cVHL.setLoYearValue(closingPrice);
                        cVHL.setHiYearValue(closingPrice);
                        cVHL.setDate(String.valueOf(year));
						companyValueHiLoWebClient.create(cVHL);
                    }
				}
			} catch (Exception e){
				logger.error("[process] excption: ", e);
			} finally {
				try {
					reader.close();
					parser.close();
				} catch(Exception e) {}
			}
		}

        // Move files
        for(String file: files) {
            File f = new File(inPath, file);
            boolean movedFile = f.renameTo(new File(archivePath, file));
            if(!movedFile) {
                logger.error("[process] failed to archive file:" + file);
            }
        }

        logger.info("[process] completed ...");

	}

	private Date getDate(String dateInString) {
		try {

			Date date = formatter.parse(dateInString);
			logger.info("date: {}", date);
			logger.info("formatted date: {}", formatter.format(date));
			return date;

		} catch (ParseException e) {
			logger.error("[process] excption: ", e);
		}
		return null;
	}

	private int prvGetCalendarYear(long d) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(d);
		return c.get(Calendar.YEAR);
	}

	private String prvGetKey(int year, String company, String exchange) {
		CompanyValueHiLo tmp = new CompanyValueHiLo();
		tmp.setDate(String.valueOf(year));
		tmp.setCompany(company);
		tmp.setExchange(exchange);
		return tmp.getKey();
	}
}
