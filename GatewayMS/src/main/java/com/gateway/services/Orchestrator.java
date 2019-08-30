package com.gateway.services;

import com.gateway.domain.Reservation;

public interface Orchestrator{

    Reservation create(Reservation reservation);

    Reservation retrieve(Long id);

    default Reservation consume(Long id){
        throw new UnsupportedOperationException();
    }
}
