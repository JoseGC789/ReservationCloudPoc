package com.gateway.services;

import com.gateway.clients.ReservationServerMS;
import com.gateway.domain.Reservation;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("async")
public class AsyncOrchestrator implements Orchestrator{
    private final List<ReservationServerMS> reservationServerMS;

    public AsyncOrchestrator(List<ReservationServerMS> reservationServerMS){
        this.reservationServerMS = reservationServerMS;
    }

    @Override
    public Reservation create(Reservation reservation){
        Reservation.ReservationBuilder builder = Reservation.builder().id(reservation.getId());
        for(ReservationServerMS ms : reservationServerMS){
            ms.create(builder, reservation);
        }
        return builder.build();
    }

    @Override
    public Reservation retrieve(Long id){
        Reservation.ReservationBuilder builder = Reservation.builder().id(id);
        for(ReservationServerMS ms : reservationServerMS){
            ms.read(builder, id);
        }
        return builder.build();
    }

}