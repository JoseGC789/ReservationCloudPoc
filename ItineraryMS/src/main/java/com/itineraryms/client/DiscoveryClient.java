package com.itineraryms.client;

import com.itineraryms.configs.RestConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class DiscoveryClient{

    private final DiscoveryProperties properties;
    private final RestOperations template;

    public DiscoveryClient(DiscoveryProperties properties, RestOperations template){
        this.properties = properties;
        this.template = template;
    }

    public HttpStatus subscribe(String serviceHostName){
        HttpEntity<DiscoveryProperties.RegistrationPayload> entity = new HttpEntity<>(properties.buildRegistration(serviceHostName), RestConfig.getAcceptHeaders());
        ResponseEntity<String> response = template.exchange(properties.buildUri(), HttpMethod.POST, entity, String.class);
        return response.getStatusCode();
    }
}
