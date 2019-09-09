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
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Service
@Slf4j
public class ElementClient extends ElementHandler{
    private final RestOperations operations;

    public ElementClient(RestOperations operations){
        this.operations = operations;
    }

    @Override
    protected Map<String, Object> postToService(Reservation reservation, DiscoveryPayload location){
        log.error("aa");
        HttpEntity entity = new HttpEntity<>(reservation.getElements(), RestConfig.getAcceptHeaders());
        log.error("aa");
        String uriString = UriComponentsBuilder.fromPath(location.getHostname())
                .path(reservation.getId().toString())
                .toString();
        log.error(uriString);
        ResponseEntity<Map<String, Object>> response = operations.exchange(uriString, POST, entity, new ParameterizedTypeReference<Map<String, Object>>(){});
        return response.getBody();
    }

    @Override
    protected Map<String, Object> getFromService(Long id, DiscoveryPayload location){
        log.error("aa");
        HttpEntity entity = new HttpEntity<>(id, RestConfig.getAcceptHeaders());
        log.error("aa");
        String uriString = UriComponentsBuilder.fromHttpUrl(location.getHostname())
                .path(id.toString())
                .toUriString();
        log.error(uriString);
        ResponseEntity<Map<String, Object>> response = operations.exchange(uriString, GET, entity, new ParameterizedTypeReference<Map<String, Object>>(){});
        return response.getBody();
    }
}
