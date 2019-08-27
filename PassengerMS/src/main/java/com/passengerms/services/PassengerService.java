package com.passengerms.services;

import com.passengerms.domain.entities.PassengerEntity;
import java.util.concurrent.CompletableFuture;

public interface PassengerService{
    CompletableFuture<PassengerEntity> retrieve(Long id);
    default CompletableFuture<PassengerEntity> consume(Long id){
        throw new UnsupportedOperationException();
    }
}
