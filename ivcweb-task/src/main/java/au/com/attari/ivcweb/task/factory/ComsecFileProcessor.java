package au.com.attari.ivcweb.task.factory;

import au.com.attari.ivcweb.task.bean.CompanyData;
import au.com.attari.ivcweb.task.bean.CompanyDataOther;
import au.com.attari.ivcweb.task.bean.CompanyEvaluation;
import au.com.attari.ivcweb.task.bean.CompanyIntrinsicValue;
import au.com.attari.ivcweb.task.business.CompanyDataOtherReader;
import au.com.attari.ivcweb.task.business.CompanyDataReader;
import au.com.attari.ivcweb.task.business.Evaluator;
import au.com.attari.ivcweb.task.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;

import au.com.attari.ivcweb.task.webclient.CompanyDataWebClient;
import au.com.attari.ivcweb.task.webclient.CompanyDataOtherWebClient;
import au.com.attari.ivcweb.task.webclient.CompanyEvaluationWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComsecFileProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ComsecFileProcessor.class);
	
	@Value("${exchange}")
	private final String exchange = "ASX";

	@Value("${file.in.path}")
    private String inPath;

	@Value("${file.archive.path}")
    private String archivePath = "";

	@Value("${requiredRateArr}")
    private String[] requiredRateArr = new String[]{};

	@Autowired
    private CompanyDataWebClient companyDataDao;
	@Autowired
    private CompanyEvaluationWebClient companyEvaluationDao;
	@Autowired
    private CompanyDataOtherWebClient companyDataOtherDao;
	
	public String getExchange() {
		return exchange;
	}
	
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
        	logger.debug("[process] processing CompanyData from file: " + file);
        	for(String requiredRateStr : requiredRateArr) {
        		
				BigDecimal requiredRate = new BigDecimal(requiredRateStr);
				CompanyDataReader companyDataReader = new CompanyDataReader();
				ArrayList<CompanyData> companyDataAl = companyDataReader.read(inPath + "\\"
						+ file, exchange);
				
				logger.debug("[process] companyDataAl.size() = " + companyDataAl.size());
				Iterator<CompanyData> it = companyDataAl.iterator();
				while (it.hasNext()) {
					CompanyData companyData = it.next();
					logger.info("[process] processing company: {} and requiredRate: {}", companyData.getCompany(), requiredRate);
					CompanyIntrinsicValue companyIntrinsicValue = new Evaluator()
							.evaluate(companyData, requiredRate);
					logger.debug("[process] processed company: {}", companyData.getCompany());
					logger.debug("" + companyData);
					if (companyData.getSharesIssued() == 0) {
						// There are no shares for this company @ that year
						// ignore
						logger.error("[process] no shares for company: "
								+ companyData.getCompany() + ", "
								+ companyData.getDate());
						continue;
					}
					//TODO: make rest template calls
					if (!getCompanyDataDao()
							.existsByCompanyAndDate(companyData)) {
						getCompanyDataDao().create(companyData);
					}
					logger.debug("[process] @@@@ intrinsic: " + companyIntrinsicValue);
					logger.debug("[process] @@@@: issued shares: "
							+ new BigDecimal(companyData.getSharesIssued()));

					BigDecimal perShareValue = companyIntrinsicValue
							.getIntrinsicValue().divide(
									new BigDecimal(
											companyData.getSharesIssued()), 2,
									RoundingMode.CEILING);
					logger.info("[process] Company: {}, exchange: {} and intrinsicValue per share: "
							, companyData.getCompany()
							, companyData.getExchange()
							, perShareValue);
					CompanyEvaluation companyEvaluation = new CompanyEvaluation(
							companyData.getCompany(), exchange, companyData.getDate(),
							companyIntrinsicValue.getIntrinsicValue(),
							perShareValue, requiredRate);
					//TODO: Make rest template call
					if (!getCompanyEvaluationDao()
							.existsByCompanyAndDateAndRequiredRate(
									companyEvaluation)) {
						getCompanyEvaluationDao().create(companyEvaluation);
					}
				}
            }
        	
        	logger.debug("[process] processing CompanyDataOther from file: " + file);
			CompanyDataOtherReader companyDataOtherReader = new CompanyDataOtherReader();
			ArrayList<CompanyDataOther> companyDataOtherAl = companyDataOtherReader.read(inPath + "\\"
	+ file, exchange);
			logger.debug("[process] companyDataOtherAl.size() = " + companyDataOtherAl.size());
			Iterator<CompanyDataOther> it = companyDataOtherAl.iterator();
			while (it.hasNext()) {
				CompanyDataOther companyDataOther = it.next();
				logger.debug("[process] processing company: "
						+ companyDataOther.getCompany());
				if (!getCompanyDataOtherDao()
						.existsByCompanyAndDate(companyDataOther)) {
					getCompanyDataOtherDao().create(companyDataOther);
				}
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
	
    public CompanyDataWebClient getCompanyDataDao() {
        return companyDataDao;
    }

    public void setCompanyDataDao(CompanyDataWebClient companyDataDao) {
        this.companyDataDao = companyDataDao;
    }

    public CompanyEvaluationWebClient getCompanyEvaluationDao() {
        return companyEvaluationDao;
    }

    public void setCompanyEvaluationDao(CompanyEvaluationWebClient companyEvaluationDao) {
        this.companyEvaluationDao = companyEvaluationDao;
    }

    public String[] getRequiredRateArr() {
        return requiredRateArr;
    }

    public void setRequiredRateArr(String requiredRateArr[]) {
        this.requiredRateArr = requiredRateArr;
    }

	public CompanyDataOtherWebClient getCompanyDataOtherDao() {
		return companyDataOtherDao;
	}

	public void setCompanyDataOtherDao(CompanyDataOtherWebClient companyDataOtherDao) {
		this.companyDataOtherDao = companyDataOtherDao;
	}
	
	public String getInPath() {
		return inPath;
	}

	public void setInPath(String inPath) {
		this.inPath = inPath;
	}

	public String getArchivePath() {
		return archivePath;
	}

	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}
	
}
