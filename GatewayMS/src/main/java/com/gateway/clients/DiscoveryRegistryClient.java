package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.DiscoveryPayload;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import java.util.Set;

@Service
public class DiscoveryRegistryClient implements DiscoveryRegistryMS{

    private final DiscoveryProperties properties;
    private final RestOperations template;

    public DiscoveryRegistryClient(DiscoveryProperties properties, RestOperations template){
        this.properties = properties;
        this.template = template;
    }

    @Override
    public Set<DiscoveryPayload> retrieveServiceData(){
        HttpEntity entity = new HttpEntity<>(null, RestConfig.getAcceptHeaders());
        ResponseEntity<Set<DiscoveryPayload>> response = template.exchange(properties.buildUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<Set<DiscoveryPayload>>(){});
        return response.getBody();
    }
}
