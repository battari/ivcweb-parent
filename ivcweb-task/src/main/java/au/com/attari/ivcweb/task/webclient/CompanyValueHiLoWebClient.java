package au.com.attari.ivcweb.task.webclient;

import au.com.attari.ivcweb.task.bean.CompanyValueHiLo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyValueHiLoWebClient {

    @Value("${ivcweb.crud.baseURL}")
    private String baseURL;

    @Value("${ivcweb.crud.urlPath.companyValueHiLo}")
    private String urlPath;

    @Value("${ivcweb.crud.principal.read}")
    String principal;

    @Value("${ivcweb.crud.passwd.read}")
    String passwd;

    private static Logger logger = LoggerFactory.getLogger(CompanyValueHiLoWebClient.class);

    public CompanyValueHiLoWebClient() {}

    public boolean create(CompanyValueHiLo inCompanyValueHiLo) {
        WebClient client = getWebClient();
        Mono<Integer> companyValueHiLoIdMono = client.post()
                .uri(uriBuilder -> uriBuilder.path(urlPath).build())
                .body(Mono.just(inCompanyValueHiLo), CompanyValueHiLo.class)
                .retrieve()
                .bodyToMono(Integer.class);
        Integer id = companyValueHiLoIdMono.block();
        logger.info("Created Company ValueHiLo for company: {}, exchange: {} aqnd date {} with id: {}"
                , inCompanyValueHiLo.getCompany(), inCompanyValueHiLo.getExchange()
                , inCompanyValueHiLo.getDate(), id);

       return true;
    }

    public List<CompanyValueHiLo> getByCompanyAndDate(CompanyValueHiLo inCompanyValueHiLo) {
        WebClient client = getWebClient();

        Flux<CompanyValueHiLo> companyValueHiLoFlux = client.get()
                .uri(uriBuilder -> uriBuilder.path(urlPath)
                .queryParam("company",inCompanyValueHiLo.getCompany())
                .queryParam("exchange", inCompanyValueHiLo.getExchange())
                .build())
                .retrieve()
                .bodyToFlux(CompanyValueHiLo.class);
        List<CompanyValueHiLo> companyValueHiLoL = companyValueHiLoFlux
                .collect(Collectors.toList())
                .share().block();
        logger.info("size of data returned for company: {}, exchange: {} is {}",
            inCompanyValueHiLo.getCompany(),
            inCompanyValueHiLo.getExchange(),
            companyValueHiLoL.size()
        );

        if( companyValueHiLoL.size() == 0)
            return new ArrayList<CompanyValueHiLo>();

        return companyValueHiLoL;
    }

    private WebClient getWebClient() {
        WebClient client = WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(ExchangeFilterFunctions
                        .basicAuthentication(principal, passwd))
                .build();
        return client;
    }


}
