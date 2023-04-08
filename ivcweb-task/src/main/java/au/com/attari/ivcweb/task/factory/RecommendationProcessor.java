package au.com.attari.ivcweb.task.factory;

import au.com.attari.ivcweb.task.bean.CompanyEvaluation;
import au.com.attari.ivcweb.task.bean.CompanyEvaluationSortByDate;
import au.com.attari.ivcweb.task.bean.CompanyValue;
import au.com.attari.ivcweb.task.bean.PortfolioItem;
import au.com.attari.ivcweb.task.webclient.CompanyEvaluationWebClient;
import au.com.attari.ivcweb.task.webclient.CompanyValueWebClient;
import au.com.attari.ivcweb.task.webclient.PortfolioItemWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecommendationProcessor {
    private static final Logger logger
            = LoggerFactory.getLogger(RecommendationProcessor.class);

    @Autowired
    private CompanyValueWebClient companyValueWebClient;

    @Autowired
    private PortfolioItemWebClient portfolioItemWebClient;

    @Autowired
    private CompanyEvaluationWebClient companyEvaluationWebClient;

    public void process() {

        List<PortfolioItem> portfolioItemL = portfolioItemWebClient.listAll();

        // If already run for today, do not run it.
        if(portfolioItemL != null && portfolioItemL.size() > 0) {
            Long time = new Date().getTime();
            if(portfolioItemL.get(0).getRecommendationDate().getTime() > getStartOfDay().getTime()) {
                logger.info("[process] Recommendation already processed @ {}",
                        portfolioItemL.get(0).getRecommendationDate());
                return;
            }
        }

        portfolioItemL.forEach(pI -> processForPortfolioItem(pI));
    }

    private void processForPortfolioItem(PortfolioItem portfolioItem) {
        List<CompanyValue> companyValueL =
                companyValueWebClient.listByCompanyAndExchange(
                        portfolioItem.getCompany(), portfolioItem.getExchange());
        List<CompanyValue> sortedCVL = companyValueL.stream().sorted(
                Comparator.comparing(CompanyValue::getDateTime))
                .collect(Collectors.toList());
        if(!sortedCVL.isEmpty()) {
            sortedCVL.forEach(cV -> processForCompanyValue(portfolioItem, cV));
        }
    }

    private void processForCompanyValue(PortfolioItem portfolioItem, CompanyValue companyValue) {
        CompanyEvaluation tmp = new CompanyEvaluation();
        tmp.setCompany(companyValue.getCompany());
        tmp.setExchange(companyValue.getExchange());
        List<CompanyEvaluation> companyEvaluationL = companyEvaluationWebClient.listByCompanyAndDateAndRequiredRate(tmp);
        List<CompanyEvaluation> sortedCEL = companyEvaluationL.stream().sorted(
                Comparator.comparing(CompanyEvaluation::getDate).reversed())
                .collect(Collectors.toList());
        if(!sortedCEL.isEmpty()) {
            // 3 values not first one for each required rate -
            String date = sortedCEL.get(0).getDate();
            List<CompanyEvaluation> latestSortedCEL = sortedCEL.stream().filter(cE -> cE.getDate().equalsIgnoreCase(date)).collect(Collectors.toList())
                    .stream().sorted(Comparator.comparing(CompanyEvaluation::getIntrinsicValue)).collect(Collectors.toList());
            processRecommendation(portfolioItem, companyValue, latestSortedCEL);
        }
    }

    private void processRecommendation(PortfolioItem portfolioItem, CompanyValue companyValue, List<CompanyEvaluation> companyEvaluationList) {

        // iV0 should be smallest as it was sorted.
        BigDecimal iV0 = companyEvaluationList.get(0).getIntrinsicValuePerShare();
        BigDecimal iV1 = companyEvaluationList.get(1).getIntrinsicValuePerShare();
        BigDecimal iV2 = companyEvaluationList.get(2).getIntrinsicValuePerShare();
        BigDecimal cV = new BigDecimal(companyValue.getValue());
        logger.info("[processRecommendation] Processing recommendation for company: {}, exchange: {}, iv0: {}, iV1: {}, iV2: {} and cV: {}",
                companyValue.getCompany(), companyValue.getExchange(),
                iV0, iV1, iV2, cV);


        // All 3 evaluation > companyValue. This is a STRONG_BUY
        // current value is buy value
        if(iV0.compareTo(cV) > 0 && iV1.compareTo(cV) > 0 && iV2.compareTo(cV) > 0) {
            portfolioItem.setRecommendation("STRONG_BUY");
            portfolioItem.setRecommendedSellValue(cV);
            portfolioItem.setRecommendationDate(new Date());
        }
        else if(iV0.compareTo(cV) <= 0 && iV1.compareTo(cV) > 0 && iV2.compareTo(cV) > 0) {
            portfolioItem.setRecommendation("WEAK_BUY_OR_HOLD");
            portfolioItem.setRecommendedSellValue(cV);
            portfolioItem.setRecommendationDate(new Date());
        }
        else if(iV0.compareTo(cV) <= 0 && iV1.compareTo(cV) <= 0 && iV2.compareTo(cV) >= 0) {
            portfolioItem.setRecommendation("HOLD");
            portfolioItem.setRecommendedSellValue(cV);
            portfolioItem.setRecommendationDate(new Date());
        }
        else {
            portfolioItem.setRecommendation("SELL");
            portfolioItem.setRecommendedSellValue(cV);
            portfolioItem.setRecommendationDate(new Date());
        }
        portfolioItemWebClient.update(portfolioItem, portfolioItem.getId());
    }

    public Date getStartOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
