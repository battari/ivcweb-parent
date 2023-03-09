package au.com.attari.ivcweb.task.webclient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import au.com.attari.ivcweb.task.bean.CompanyEvaluation;
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

@Component
public class CompanyEvaluationWebClient implements CompanyEvaluationWebClientIface {

    @Value("${ivcweb.crud.baseURL}")
    private String baseURL;

    @Value("${ivcweb.crud.urlPath.companyEvaluation}")
    private String urlPath;

    @Value("${ivcweb.crud.principal.read}")
    String principal;

    @Value("${ivcweb.crud.passwd.read}")
    String passwd;

    private static Logger logger = LoggerFactory.getLogger(CompanyEvaluationWebClient.class);

    public CompanyEvaluationWebClient() {}

    public boolean create(CompanyEvaluation inCompanyEvaluation) {

        WebClient client = getWebClient();
        Mono<Integer> companyEvaluationIdMono = client.post()
                .uri(uriBuilder -> uriBuilder.path(urlPath).build())
                .body(Mono.just(inCompanyEvaluation), CompanyEvaluation.class)
                .retrieve()
                .bodyToMono(Integer.class);
        Integer id = companyEvaluationIdMono.block();
        logger.info("Created Company Evaluation for company: {}, exchange: {} aqnd date {} with id: {}"
                , inCompanyEvaluation.getCompany(), inCompanyEvaluation.getExchange()
                , inCompanyEvaluation.getDate(), id);

        return true;

    }

     public boolean existsByCompanyAndDateAndRequiredRate(CompanyEvaluation inCompanyEvaluation) {

         WebClient client = getWebClient();

         Flux<CompanyEvaluation> companyEvaluationFlux = client.get()
                 .uri(uriBuilder -> uriBuilder.path(urlPath)
                         .queryParam("company",inCompanyEvaluation.getCompany())
                         .queryParam("exchange", inCompanyEvaluation.getExchange())
                         .queryParam("date", inCompanyEvaluation.getDate())
                         .queryParam("rate", inCompanyEvaluation.getRequiredRate())
                         .build())
                 .retrieve()
                 .bodyToFlux(CompanyEvaluation.class);
         List<CompanyEvaluation> companyEvaluationL = companyEvaluationFlux
                 .collect(Collectors.toList())
                 .share().block();
         logger.info("size of data returned for company: {}, exchange: {} is {}",
                 inCompanyEvaluation.getCompany(),
                 inCompanyEvaluation.getExchange(),
                 companyEvaluationL.size()
         );

         if( companyEvaluationL.size() == 0)
             return false;

         return true;
    }

    public ArrayList<CompanyEvaluation> listByCompanyAndDateAndRequiredRate(CompanyEvaluation inCompanyEvaluation) {

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
