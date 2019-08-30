package com.gateway.services;

import com.gateway.clients.ReservationServerMS;
import com.gateway.domain.Reservation;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.Future;

@Service("async")
public class AsyncOrchestrator implements Orchestrator{
    private final List<ReservationServerMS> reservationServerMS;

    public AsyncOrchestrator(List<ReservationServerMS> reservationServerMS){
        this.reservationServerMS = reservationServerMS;
    }

    @Override
    public Reservation create(Reservation reservation){
        Reservation.ReservationBuilder builder = Reservation.builder();
        reservationServerMS.forEach(ms -> ms.create(builder, reservation));
        return builder.build();
    }

    @Override
    public Future<Reservation> retrieve(Long id){
        return null;
    }

}
