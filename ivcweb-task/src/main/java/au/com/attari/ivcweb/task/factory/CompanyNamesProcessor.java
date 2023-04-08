package au.com.attari.ivcweb.task.factory;

import au.com.attari.ivcweb.task.webclient.CompanyDataWebClient;
import au.com.attari.ivcweb.task.webclient.CompanyValueWebClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CompanyNamesProcessor implements InitializingBean {

    private static final Logger logger
            = LoggerFactory.getLogger(CompanyNamesProcessor.class);

    @Value("${exchange}")
    private final String exchange = "ASX";

    @Autowired
    private CompanyDataWebClient companyDataWebClient;

    // Should be put into redis-cache
    private static Map<String, String> _companyNamesMap = new HashMap<String, String>();

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("[CompanyNamesProcessor] initialising map");
        companyNamesToMap();
        logger.info("[CompanyNamesProcessor] initialised map with size: ", _companyNamesMap.size());
    }

    private void  companyNamesToMap() {
        List<String> companyNamesList = companyDataWebClient.getCompanyNames(exchange);
        _companyNamesMap = companyNamesList.stream()
                .collect(Collectors.toMap(String::toString, String::toString));
    }

    public Map<String, String> getCompanyNames() {
        if(_companyNamesMap == null || _companyNamesMap.isEmpty()) {
            companyNamesToMap();
            logger.info("[CompanyNamesProcessor] initialised map with size: ", _companyNamesMap.size());
        }
        return _companyNamesMap;
    }
}
