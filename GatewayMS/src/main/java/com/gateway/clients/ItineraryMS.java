package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.Itinerary;
import com.gateway.domain.Reservation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class ItineraryMS extends ReservationServlet<Itinerary>{
    private final ItineraryMSConfig config;
    private final RestOperations template;

    public ItineraryMS(ItineraryMSConfig config, RestOperations template){
        this.config = config;
        this.template = template;
    }

    @Override
    protected Itinerary postToService(Reservation reservation){
        HttpEntity<Reservation> entity = new HttpEntity<>(reservation, RestConfig.getAcceptHeaders());
        ResponseEntity<Reservation> response = template.exchange(config.buildUri(), HttpMethod.POST, entity, Reservation.class);
        return extractAnswer(response, () -> response.getBody().getItinerary());
    }

    @Override
    protected Itinerary getFromService(Long id){
        HttpEntity<Long> entity = new HttpEntity<>(id, RestConfig.getAcceptHeaders());
        ResponseEntity<Itinerary> response = template.exchange(config.buildUri() + id, HttpMethod.GET, entity, Itinerary.class);
        return extractAnswer(response, response::getBody);
    }

    private <T> Itinerary extractAnswer(ResponseEntity<T> response, Supplier<Itinerary> action){
        return response.getStatusCode() != HttpStatus.NO_CONTENT ? action.get() : Itinerary.empty();
    }

    @Override
    protected Function<Reservation.ReservationBuilder, Reservation.ReservationBuilder> handleField(Itinerary data){
        return reservation -> reservation.itinerary(data);
    }
}
