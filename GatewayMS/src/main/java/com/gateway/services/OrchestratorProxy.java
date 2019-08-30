package com.gateway.services;

import com.gateway.domain.Reservation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@CacheConfig(cacheNames = "reservation")
public class OrchestratorProxy implements Orchestrator{
    private final Orchestrator orchestrator;

    public OrchestratorProxy(@Qualifier("async") Orchestrator orchestrator){
        this.orchestrator = orchestrator;
    }

    @Override
    public Reservation create(Reservation reservation){
        return orchestrator.create(reservation);
    }

    @CachePut(key = "#id")
    @Override
    public Reservation retrieve(Long id){
        return orchestrator.retrieve(id);
    }

    @Cacheable(key = "#id")
    @Override
    public Reservation consume(Long id){
        return orchestrator.retrieve(id);
    }

}
