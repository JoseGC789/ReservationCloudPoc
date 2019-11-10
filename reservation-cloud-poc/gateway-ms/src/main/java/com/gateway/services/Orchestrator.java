package com.gateway.services;

import com.gateway.domain.Reservation;
import java.util.concurrent.Future;

public interface Orchestrator{

    void create(Reservation reservation);

    Future<Reservation> retrieve(Long id);

    default Future<Reservation> consume(Long id){
        throw new UnsupportedOperationException();
    }
}
