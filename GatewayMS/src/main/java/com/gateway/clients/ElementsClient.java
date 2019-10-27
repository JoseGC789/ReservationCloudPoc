package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.Reservation;
import com.netflix.discovery.EurekaClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Service
public class ElementsClient extends ElementsHandler {
    private final RestOperations operations;

    public ElementsClient(ExecutorService executorService, EurekaClient client, RestOperations operations) {
        super(executorService, client);
        this.operations = operations;
    }

    @Override
    protected Map<String, Object> postToService(Reservation reservation, String location){
        return requestElement(location + reservation.getId(), POST,  buildEntity(reservation.getElements())).getBody();
    }

    @Override
    protected Map<String, Object> getFromService(Long id, String location){
        return requestElement(location + id, GET, buildEntity(id)).getBody();
    }

    private static HttpEntity buildEntity(Object id) {
        return new HttpEntity<>(id, RestConfig.getAcceptHeaders());
    }

    private ResponseEntity<Map<String, Object>> requestElement(String location, HttpMethod verb, HttpEntity entity) {
        return operations.exchange(location, verb, entity, new ParameterizedTypeReference<Map<String, Object>>(){});
    }
}
