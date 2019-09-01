package com.gateway.clients;

import com.gateway.configs.RestConfig;
import com.gateway.domain.Group;
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
public class GroupMS extends ReservationServlet<Group>{
    private final GroupMSConfig config;
    private final RestOperations template;

    public GroupMS(GroupMSConfig config, RestOperations template){
        this.config = config;
        this.template = template;
    }

    @Override
    protected Group postToService(Reservation reservation){
        HttpEntity<Reservation> entity = new HttpEntity<>(reservation, RestConfig.getAcceptHeaders());
        ResponseEntity<Reservation> response = template.exchange(config.buildUri(), HttpMethod.POST, entity, Reservation.class);
        return extractAnswer(response, () -> response.getBody().getGroup());
    }

    @Override
    protected Group getFromService(Long id){
        HttpEntity<Long> entity = new HttpEntity<>(id, RestConfig.getAcceptHeaders());
        ResponseEntity<Group> response = template.exchange(config.buildUri() + id, HttpMethod.GET, entity, Group.class);
        return extractAnswer(response, response::getBody);
    }

    private <T> Group extractAnswer(ResponseEntity<T> response, Supplier<Group> action){
        return response.getStatusCode() != HttpStatus.NO_CONTENT ? action.get() : Group.empty();
    }

    @Override
    protected Function<Reservation.ReservationBuilder, Reservation.ReservationBuilder> handleField(Group data){
        return reservation -> reservation.group(data);
    }
}
