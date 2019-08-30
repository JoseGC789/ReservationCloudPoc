package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.Passenger;
import com.gateway.domain.Reservation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

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
        ResponseEntity<Reservation> response = template.exchange(config.buildUri(), HttpMethod.POST, entity, Reservation.class);
        return extractAnswer(response, () -> response.getBody().getPassengers());
    }

    @Override
    protected List<Passenger> readFromService(Long id){
        HttpEntity<Long> entity = new HttpEntity<>(id, RestConfig.getAcceptHeaders());
        ResponseEntity<List<Passenger>> response = template.exchange(config.buildUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Passenger>>(){});
        return extractAnswer(response, response::getBody);
    }

    private <T> List<Passenger> extractAnswer(ResponseEntity<T> response, Supplier<List<Passenger>> action){
        return response.getStatusCode() == HttpStatus.OK ? action.get() : Collections.emptyList();
    }

    @Override
    protected Function<Reservation.ReservationBuilder, Reservation.ReservationBuilder> handleField(List<Passenger> data){
        return reservation -> reservation.passengers(data);
    }
}
