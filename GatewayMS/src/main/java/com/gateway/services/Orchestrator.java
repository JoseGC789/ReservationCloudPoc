package com.gateway.services;

import com.gateway.domain.Reservation;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface Orchestrator{

    Future<Reservation> create(Reservation reservation);

    Future<Reservation> retrieve(Long id) throws ExecutionException, InterruptedException;

    default Future<Reservation> consume(Long id) throws ExecutionException, InterruptedException{
        throw new UnsupportedOperationException();
    }
}
