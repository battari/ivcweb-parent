package au.com.attari.ivcweb.task.webclient;

import au.com.attari.ivcweb.task.bean.PortfolioItem;
import au.com.attari.ivcweb.task.bean.PortfolioItem;
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
public class PortfolioItemWebClient {

    @Value("${ivcweb.crud.baseURL}")
    private String baseURL;

    @Value("${ivcweb.crud.urlPath.portfolioItemList}")
    private String urlListPath;

    @Value("${ivcweb.crud.urlPath.portfolioItem}")
    private String urlPath;
    
    @Value("${ivcweb.crud.principal.read}")
    String principal;

    @Value("${ivcweb.crud.passwd.read}")
    String passwd;

    private static Logger logger = LoggerFactory.getLogger(PortfolioItemWebClient.class);

    public PortfolioItemWebClient() {}
    
    public List<PortfolioItem> listAll() {

        WebClient client = getWebClient();

        Flux<PortfolioItem> portfolioItemFlux = client.get()
                .uri(uriBuilder -> uriBuilder.path(urlListPath)
                        .build())
                .retrieve()
                .bodyToFlux(PortfolioItem.class);
        List<PortfolioItem> portfolioItemL = portfolioItemFlux
                .collect(Collectors.toList())
                .share().block();
        logger.info("size of data returned is {}",
                portfolioItemL.size()
        );
        
        return portfolioItemL;
        
    }
    
    public boolean update(PortfolioItem inPortfolioItem, int id) {
        
        logger.info("portfolioItem: {}", inPortfolioItem.toString());

        WebClient client = getWebClient();
        Mono<Integer> portfolioItemIdMono = client.patch()
                .uri(urlPath + "/{id}", id + "")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(inPortfolioItem), PortfolioItem.class)
                .retrieve()
                .bodyToMono(Integer.class);
        Integer retId = portfolioItemIdMono.block();
        logger.info("Retrieved Portfolio Item for company: {}, exchange: {} and date {} with id: {}"
                , inPortfolioItem.getCompany(), inPortfolioItem.getExchange()
                , inPortfolioItem.getRecommendationDate(), id);

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
