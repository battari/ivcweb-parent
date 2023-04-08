package au.com.attari.ivcweb.ivcwebmvc.util;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@ComponentScan("au.com.attari.ivcweb.ivcwebmvc")
public class RestTemplateConfiguration {

    Logger logger = LoggerFactory.getLogger(RestTemplateConfiguration.class);

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        logger.info("Building rest template");
        return restTemplateBuilder
                .requestFactory(SimpleClientHttpRequestFactory.class)
                .errorHandler(new DefaultResponseErrorHandler()).build();
    }
}
