package com.gateway.services;

import com.gateway.clients.ElementsMS;
import com.gateway.domain.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@Primary
@CacheConfig(cacheNames = "reservation")
@Slf4j
public class InMemoryOrchestrator implements Orchestrator{
    private final ElementsMS ms;
    private final ExecutorService executorService;

    public InMemoryOrchestrator(ElementsMS ms, ExecutorService executorService){
        this.ms = ms;
        this.executorService = executorService;
    }

    @Override
    public void create(Reservation reservation){
        executorService.submit(() -> ms.create(reservation));
    }

    @CachePut(key = "#id")
    @Override
    public Future<Reservation> retrieve(Long id){
        return executorService.submit(() -> getReservationFuture(id));
    }

    private Reservation getReservationFuture(Long id) {
        Map<String, Object> elements = new HashMap<>();
        List<Future<Map<String, Object>>> read = ms.read(id);
        for(Future<Map<String, Object>> future : read){
            try{
                elements.putAll(future.get());
            }catch(Exception e){
                log.error(e.getMessage());
            }
        }
        return Reservation.builder().elements(elements).id(id).build();
    }

    @Cacheable(key = "#id")
    @Override
    public Future<Reservation> consume(Long id){
        return retrieve(id);
    }

}
