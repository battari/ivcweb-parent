package au.com.attari.ivcweb.task.webclient;

import au.com.attari.ivcweb.task.bean.CompanyValue;
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
public class CompanyValueWebClient {

    @Value("${ivcweb.crud.baseURL}")
    private String baseURL;

    @Value("${ivcweb.crud.urlPath.companyValue}")
    private String urlPath;

    @Value("${ivcweb.crud.principal.read}")
    String principal;

    @Value("${ivcweb.crud.passwd.read}")
    String passwd;

    private static Logger logger = LoggerFactory.getLogger(CompanyValueWebClient.class);

    public CompanyValueWebClient() {}
    public boolean create(CompanyValue inCompanyValue) {

        logger.info("companyValue: {}", inCompanyValue.toString());

        WebClient client = getWebClient();
        Mono<Integer> companyDataIdMono = client.post()
                .uri(uriBuilder -> uriBuilder.path(urlPath).build())
                .body(Mono.just(inCompanyValue), CompanyValue.class)
                .retrieve()
                .bodyToMono(Integer.class);
        Integer id = companyDataIdMono.block();
        logger.info("Created Company Data for company: {}, exchange: {} and date {} with id: {}"
                , inCompanyValue.getCompany(), inCompanyValue.getExchange()
                , inCompanyValue.getDateTime(), id);

        return true;

    }

     // This will get

     public List<CompanyValue> listByCompanyAndExchange(String company, String exchange) {

         WebClient client = getWebClient();
         Flux<CompanyValue> companyValueFlux = client.get()
                 .uri(uriBuilder -> uriBuilder.path(urlPath)
                         .queryParam("company",company)
                         .queryParam("exchange", exchange)
                         .build())
                 .retrieve()
                 .bodyToFlux(CompanyValue.class);
        List<CompanyValue> companyValueL = companyValueFlux
                 .collect(Collectors.toList())
                 .share().block();
         logger.info("size of data returned for company: {}, exchange: {} is {}",
                 company,
                 exchange,
                 companyValueL.size()
         );
         return companyValueL;
    }

    public ArrayList<CompanyValue> listAllByMaxDate() {

        return new ArrayList<>();
    }

    public boolean existsByCompanyAndDate(CompanyValue inCompanyValue) {

        WebClient client = getWebClient();

        Flux<CompanyValue> companyValueFlux = client.get()
                .uri(uriBuilder -> uriBuilder.path(urlPath)
                        .queryParam("company",inCompanyValue.getCompany())
                        .queryParam("exchange", inCompanyValue.getExchange())
                        .queryParam("date", inCompanyValue.getDateTime())
                        .build())
                .retrieve()
                .bodyToFlux(CompanyValue.class);
        List<CompanyValue> companyValueL = companyValueFlux
                .collect(Collectors.toList())
                .share().block();
        logger.info("size of data returned for company: {}, exchange: {} is {}",
                inCompanyValue.getCompany(),
                inCompanyValue.getExchange(),
                companyValueL.size()
        );

        if( companyValueL.size() == 0)
            return false;

        return true;
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
