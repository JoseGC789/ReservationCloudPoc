package com.passengerms.services;

import com.passengerms.domain.entities.Passenger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PassengerService{
    CompletableFuture<List<Passenger>> retrieve(Long id);
    default CompletableFuture<List<Passenger>> consume(Long id){
        throw new UnsupportedOperationException();
    }
}
