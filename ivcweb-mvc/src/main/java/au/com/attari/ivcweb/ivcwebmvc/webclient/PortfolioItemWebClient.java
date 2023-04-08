package au.com.attari.ivcweb.ivcwebmvc.webclient;

import au.com.attari.ivcweb.ivcwebmvc.model.PortfolioItem;
import au.com.attari.ivcweb.ivcwebmvc.util.HeadersUtils;
import au.com.attari.ivcweb.ivcwebmvc.util.RestTemplateConfiguration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
public class PortfolioItemWebClient extends GenericWebClient{

    Logger logger = LoggerFactory.getLogger(PortfolioItemWebClient.class);

    @Value("${ivcweb.crud.url-path.get.portfolio-items}")
    private String urlPath;

    @Value("${ivcweb.crud.url-path.delete-post-put.portfolio-items}")
    private String changeUrlPath;

    public List<PortfolioItem> getAll() {
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.add("Authorization", HeadersUtils.getAuthorization(principal, passwd));

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseURL + urlPath);
        String url = builder.build().encode().toUriString();
        logger.info("URL: " + url);

        ResponseEntity<List> responseEntity =
                new RestTemplate().exchange(url,
                        HttpMethod.GET, new HttpEntity<>(httpHeaders()), List.class);
        if (responseEntity.hasBody()) {
            List<PortfolioItem> l = responseEntity.getBody();
            logger.info("[getByExchange] size of portfolio item list returned: " + l.size());
            return l;

        } else {
            return new ArrayList();
        }
    }

    public void delete(int id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseURL + changeUrlPath  + "/" + id);
        String url = builder.build().encode().toUriString();
        logger.info("[delete] URL: " + url);
        int statusCode = new RestTemplate().exchange(url,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaders()), String.class).getStatusCode().value();
        logger.info("[delete] status code: " + statusCode); // Ignore failures
    }

    public int save(PortfolioItem portfolioItem) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseURL + changeUrlPath);
        String url = builder.build().encode().toUriString();
        logger.info("[save] URL: " + url);
        ResponseEntity<Integer> responseEntity = new RestTemplate().exchange(url,
                HttpMethod.POST, new HttpEntity<>(portfolioItem, httpHeaders()), Integer.class);
        int statusCode = responseEntity.getStatusCode().value();
        logger.info("[save] status code: " + statusCode);
        if (responseEntity.hasBody()) {
            int id = responseEntity.getBody().intValue();
            return 1;
        }
        return 0;
    }

    public int update(PortfolioItem portfolioItem) {
        return 0;
    }

}


