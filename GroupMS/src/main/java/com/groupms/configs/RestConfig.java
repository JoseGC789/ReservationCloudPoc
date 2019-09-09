package com.groupms.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@Configuration
public class RestConfig{

    private static final HttpHeaders ACCEPT_HEADERS = new HttpHeaders();
    static {
        ACCEPT_HEADERS.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Bean
    public RestOperations getTemplate(){
        return new RestTemplate();
    }

    public static HttpHeaders getAcceptHeaders(){
        return ACCEPT_HEADERS;
    }
}

