package au.com.attari.ivcweb.task.webclient;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.attari.ivcweb.task.bean.CompanyDataOther;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.HttpHeaders;

@Component
public class CompanyDataOtherWebClient {

    @Value("${ivcweb.crud.baseURL}")
    private String baseURL;

    @Value("${ivcweb.crud.urlPath.companyDataOther}")
    private String urlPath;

    @Value("${ivcweb.crud.principal.read}")
    String principal;

    @Value("${ivcweb.crud.passwd.read}")
    String passwd;

    private static Logger logger = LoggerFactory.getLogger(CompanyDataOtherWebClient.class);

    public CompanyDataOtherWebClient() {}
    public boolean create(CompanyDataOther inCompanyDataOther) {

        logger.info("companyDataOther: {}", inCompanyDataOther.toString());

        WebClient client = getWebClient();
        Mono<Integer> companyDataIdMono = client.post()
                .uri(uriBuilder -> uriBuilder.path(urlPath).build())
                .body(Mono.just(inCompanyDataOther), CompanyDataOther.class)
                .retrieve()
                .bodyToMono(Integer.class);
        Integer id = companyDataIdMono.block();
        logger.info("Created Company Data for company: {}, exchange: {} aqnd date {} with id: {}"
                , inCompanyDataOther.getCompany(), inCompanyDataOther.getExchange()
                , inCompanyDataOther.getDate(), id);

        return true;

    }

     public ArrayList<CompanyDataOther> listAll() {

        return new ArrayList<>();
    }

    public ArrayList<CompanyDataOther> listAllByMaxDate() {

        return new ArrayList<>();
    }

    public boolean existsByCompanyAndDate(CompanyDataOther inCompanyDataOther) {

        WebClient client = getWebClient();

        Flux<CompanyDataOther> companyDataOtherFlux = client.get()
                .uri(uriBuilder -> uriBuilder.path(urlPath)
                        .queryParam("company",inCompanyDataOther.getCompany())
                        .queryParam("exchange", inCompanyDataOther.getExchange())
                        .queryParam("date", inCompanyDataOther.getDate())
                        .build())
                .retrieve()
                .bodyToFlux(CompanyDataOther.class);
        List<CompanyDataOther> companyDataOtherL = companyDataOtherFlux
                .collect(Collectors.toList())
                .share().block();
        logger.info("size of data returned for company: {}, exchange: {} is {}",
                inCompanyDataOther.getCompany(),
                inCompanyDataOther.getExchange(),
                companyDataOtherL.size()
        );

        if( companyDataOtherL.size() == 0)
            return false;

        return true;
    }

    public ArrayList<CompanyDataOther> listByCompanyAndDate(CompanyDataOther inCompanyDataOther) {

        return new ArrayList<>();
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
