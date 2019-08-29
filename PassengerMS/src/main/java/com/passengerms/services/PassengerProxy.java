package com.passengerms.services;

import com.passengerms.domain.entities.Passenger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Primary
@CacheConfig(cacheNames = "passengers")
public class PassengerProxy implements PassengerService{
    private final PassengerService service;

    public PassengerProxy(@Qualifier("async") PassengerService service){
        this.service = service;
    }

    @Override
    @CachePut(key = "#id")
    public CompletableFuture<List<Passenger>> retrieve(Long id){
        return service.retrieve(id);
    }

    @Override
    @Cacheable(key = "#id")
    public CompletableFuture<List<Passenger>> consume(Long id){
        return service.retrieve(id);
    }
}
