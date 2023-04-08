package au.com.attari.ivcweb.ivcwebmvc.webclient;

import au.com.attari.ivcweb.ivcwebmvc.model.BuyableCompany;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class GenericWebClient {

    Logger logger = LoggerFactory.getLogger(GenericWebClient.class);

    @Value("${ivcweb.crud.base-url}")
    String baseURL;

    @Value("${ivcweb.crud.principal.read}")
    String principal;

    @Value("${ivcweb.crud.passwd.read}")
    String passwd;

    HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + getBasicAuthHeader());
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    String getBasicAuthHeader() {
        String credentials = principal +":" + passwd;
        return new String(Base64.encodeBase64(credentials.getBytes()));
    }
}


