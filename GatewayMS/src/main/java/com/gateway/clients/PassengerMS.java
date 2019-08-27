package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.Passenger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class PassengerMS{
    private final PassengerMSConfig config;
    private final RestOperations template;

    public PassengerMS(PassengerMSConfig config, RestOperations template){
        this.config = config;
        this.template = template;
    }

    public Passenger create(Passenger passenger){
        HttpEntity<Passenger> entity = new HttpEntity<>(passenger, RestConfig.getAcceptHeaders());
        return template.exchange(config.buildUri(), HttpMethod.POST, entity, Passenger.class).getBody();
    }
}
