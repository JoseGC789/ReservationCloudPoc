package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.DiscoveryPayload;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;
import java.util.Set;
import static org.springframework.http.HttpMethod.GET;

@Service
public class DiscoveryRegistryClient implements DiscoveryRegistryMS{

    private final DiscoveryProperties properties;
    private final RestOperations template;

    public DiscoveryRegistryClient(DiscoveryProperties properties, RestOperations template){
        this.properties = properties;
        this.template = template;
    }

    @Override
    public Set<DiscoveryPayload> retrieveServiceData(Set<String> keySet){
        HttpEntity entity = new HttpEntity<>(null, RestConfig.getAcceptHeaders());
        String uriString = UriComponentsBuilder.fromHttpUrl(properties.buildUri())
                .queryParam("tags", String.join(",", keySet))
                .toUriString();
        ResponseEntity<Set<DiscoveryPayload>> response = template.exchange(uriString, GET, entity, new ParameterizedTypeReference<Set<DiscoveryPayload>>(){});
        return response.getBody();
    }

    @Override
    public Set<DiscoveryPayload> retrieveServiceData(){
        return retrieveServiceData(Collections.emptySet());
    }
}
