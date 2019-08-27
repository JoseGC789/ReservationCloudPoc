package com.passengerms.services;

import com.passengerms.domain.entities.PassengerEntity;
import com.passengerms.repositories.PassengerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public CompletableFuture<PassengerEntity> retrieve(Long id){
        log.warn("new thread " + UUID.randomUUID().toString());
        return CompletableFuture.completedFuture(repository.findById(id)
                .orElseThrow(this::newNotFound));
    }

    private ResponseStatusException newNotFound(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
