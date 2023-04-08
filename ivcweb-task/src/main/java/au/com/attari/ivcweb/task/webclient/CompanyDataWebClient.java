package au.com.attari.ivcweb.task.webclient;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.attari.ivcweb.task.bean.CompanyData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.HttpHeaders;

@Component
public class CompanyDataWebClient {

    @Value("${ivcweb.crud.baseURL}")
    private String baseURL;

    @Value("${ivcweb.crud.urlPath.companyData}")
    private String urlPath;

    @Value("${ivcweb.crud.urlPath.companyNames}")
    private String companyNamesUrlPath;

    @Value("${ivcweb.crud.principal.read}")
    String principal;

    @Value("${ivcweb.crud.passwd.read}")
    String passwd;

    private static Logger logger = LoggerFactory.getLogger(CompanyDataWebClient.class);

    public CompanyDataWebClient() {}

    public boolean create(CompanyData inCompanyData) {
        WebClient client = getWebClient();
        Mono<Integer> companyDataIdMono = client.post()
                .uri(uriBuilder -> uriBuilder.path(urlPath).build())
                .body(Mono.just(inCompanyData), CompanyData.class)
                .retrieve()
                .bodyToMono(Integer.class);
        Integer id = companyDataIdMono.block();
        logger.info("Created Company Data for company: {}, exchange: {} aqnd date {} with id: {}"
                , inCompanyData.getCompany(), inCompanyData.getExchange()
                , inCompanyData.getDate(), id);

       return true;
    }

     public List<CompanyData> listAll() {

        return new ArrayList<>();
    }

    public List<CompanyData> listAllByMaxDate(String exchange) {

        return new ArrayList<>();
    }

    public boolean existsByCompanyAndDate(CompanyData inCompanyData) {
        WebClient client = getWebClient();

        Flux<CompanyData> companyDataFlux = client.get()
                .uri(uriBuilder -> uriBuilder.path(urlPath)
                .queryParam("company",inCompanyData.getCompany())
                .queryParam("exchange", inCompanyData.getExchange())
                .queryParam("date", inCompanyData.getDate())
                .build())
                .retrieve()
                .bodyToFlux(CompanyData.class);
        List<CompanyData> companyDataL = companyDataFlux
                .collect(Collectors.toList())
                .share().block();
        logger.info("size of data returned for company: {}, exchange: {} is {}",
            inCompanyData.getCompany(),
            inCompanyData.getExchange(),
            companyDataL.size()
        );

        if( companyDataL.size() == 0)
            return false;

        return true;
    }

    public List<CompanyData> listByCompanyAndDate(CompanyData inCompanyData) {

        return new ArrayList<>();
    }
    
	public List<String> getCompanyNames(String exchange) {

        WebClient client = getWebClient();

        // Flux<String> returns as one String so use Flux<Object>
        Flux<Object> strFlux = client.get()
                .uri(uriBuilder -> uriBuilder.path(companyNamesUrlPath)
                        .queryParam("exchange", exchange)
                        .build())
                .retrieve()
                .bodyToFlux(Object.class);
        List<Object> companyNamesOL = strFlux.collectList().block();
        List<String> companyNamesL = companyNamesOL.stream().map(Object::toString).collect(Collectors.toList());
        // companyNamesL.forEach(System.out::println);

        logger.info("size of data returned for company names from exchange: {} is {}",
                exchange,
                companyNamesL.size()
        );
        return companyNamesL;
	}

    public List<String> listUniqueExchanges() {

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
