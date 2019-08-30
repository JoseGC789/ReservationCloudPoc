package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.Passenger;
import com.gateway.domain.Reservation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import java.util.List;
import java.util.function.Function;

@Service
public class PassengerMS extends AbstractMs<List<Passenger>>{
    private final PassengerMSConfig config;
    private final RestOperations template;

    public PassengerMS(PassengerMSConfig config, RestOperations template){
        this.config = config;
        this.template = template;
    }

    @Override
    protected List<Passenger> postToService(Reservation reservation){
        HttpEntity<Reservation> entity = new HttpEntity<>(reservation, RestConfig.getAcceptHeaders());
        return template.exchange(config.buildUri(), HttpMethod.POST, entity, new ParameterizedTypeReference<List<Passenger>>(){}).getBody();
    }

    @Override
    protected List<Passenger> readFromService(Long id){
        HttpEntity<Long> entity = new HttpEntity<>(id, RestConfig.getAcceptHeaders());
        return template.exchange(config.buildUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Passenger>>(){}).getBody();
    }

    @Override
    protected Function<Reservation.ReservationBuilder, Reservation.ReservationBuilder> handleField(List<Passenger> data){
        return reservation -> reservation.passengers(data);
    }
}
