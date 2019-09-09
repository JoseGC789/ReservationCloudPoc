package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.DiscoveryPayload;
import com.gateway.domain.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Service
@Slf4j
public class ElementClient extends ElementHandler{
    private final RestOperations operations;

    public ElementClient(ExecutorService executorService, RestOperations operations){
        super(executorService);
        this.operations = operations;
    }

    @Override
    protected Map<String, Object> postToService(Reservation reservation, DiscoveryPayload location){
        HttpEntity entity = new HttpEntity<>(reservation.getElements(), RestConfig.getAcceptHeaders());
        String uriString = UriComponentsBuilder.fromUriString("http://" + location.getHostname())
                .path("/")
                .path(reservation.getId().toString())
                .toUriString();
        ResponseEntity<Map<String, Object>> response = operations.exchange(uriString, POST, entity, new ParameterizedTypeReference<Map<String, Object>>(){});
        return response.getBody();
    }

    @Override
    protected Map<String, Object> getFromService(Long id, DiscoveryPayload location){
        HttpEntity entity = new HttpEntity<>(id, RestConfig.getAcceptHeaders());
        String uriString = UriComponentsBuilder.fromUriString("http://" + location.getHostname())
                .path("/")
                .path(id.toString())
                .toUriString();
        ResponseEntity<Map<String, Object>> response = operations.exchange(uriString, GET, entity, new ParameterizedTypeReference<Map<String, Object>>(){});
        log.error(response.getBody().toString());
        return response.getBody();
    }
}
