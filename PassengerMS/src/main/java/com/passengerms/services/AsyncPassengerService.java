package com.passengerms.services;

import com.passengerms.domain.entities.Passenger;
import com.passengerms.repositories.PassengerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service("async")
@Slf4j
public class AsyncPassengerService implements PassengerService{
    private final PassengerRepository repository;

    public AsyncPassengerService(PassengerRepository repository){
        this.repository = repository;
    }

    @Override
    @Async
    public CompletableFuture<List<Passenger>> retrieve(Long id){
        log.warn("new thread " + UUID.randomUUID().toString());
        return CompletableFuture.completedFuture(repository.findPassengerById(id));
    }
}
