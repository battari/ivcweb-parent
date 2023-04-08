package au.com.attari.ivcweb.ivcwebmvc.webclient;

import au.com.attari.ivcweb.ivcwebmvc.model.BuyableCompany;
import au.com.attari.ivcweb.ivcwebmvc.util.HeadersUtils;
import au.com.attari.ivcweb.ivcwebmvc.util.RestTemplateConfiguration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Max;
import java.net.URI;

import java.util.*;

@Component
public class BuyableCompanyWebClient extends GenericWebClient {

    Logger logger = LoggerFactory.getLogger(BuyableCompanyWebClient.class);

    @Value("${ivcweb.crud.url-path.get.buyable-companys}")
    private String urlPath;

    public List<BuyableCompany> getByExchange(String exchange) {
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.add("Authorization", HeadersUtils.getAuthorization(principal, passwd));

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseURL + urlPath)
                .queryParam("exchange", exchange);
        String url = builder.build().encode().toUriString();
        logger.info("URL: " + url);

        ResponseEntity<List> responseEntity =
                new RestTemplate().exchange(url,
                        HttpMethod.GET, new HttpEntity<>(httpHeaders()), List.class);
        if (responseEntity.hasBody()) {
            List<BuyableCompany> l = responseEntity.getBody();
            logger.info("[getByExchange] size of buyable list returned: " + l.size());
            return l;

        } else {
            return new ArrayList();
        }
    }


}


