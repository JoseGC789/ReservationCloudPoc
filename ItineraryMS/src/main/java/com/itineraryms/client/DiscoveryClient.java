package com.itineraryms.client;

import com.itineraryms.configs.RestConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import java.util.Map;

@Service
public class DiscoveryClient{

    private final DiscoveryProperties properties;
    private final RestOperations template;

    public DiscoveryClient(DiscoveryProperties properties, RestOperations template){
        this.properties = properties;
        this.template = template;
    }

    public HttpStatus subscribe(String serviceHostName){
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(properties.buildRegistration(serviceHostName).getPayload(), RestConfig.getAcceptHeaders());
        ResponseEntity response = template.exchange(properties.buildUri(), HttpMethod.POST, entity, String.class);
        return response.getStatusCode();
    }
}
