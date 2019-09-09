package com.gateway.services;

import com.gateway.clients.ElementsMS;
import com.gateway.domain.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

@Service("async")
@Slf4j
public class AsyncOrchestrator implements Orchestrator{
    private final List<ElementsMS> reservationServerMS;
    private final ExecutorService executorService;

    public AsyncOrchestrator(List<ElementsMS> reservationServerMS, ExecutorService executorService){
        this.reservationServerMS = reservationServerMS;
        this.executorService = executorService;
    }

    @Override
    @Async
    public Future<Reservation> create(Reservation reservation){
        Reservation.ReservationBuilder builder = Reservation.builder().id(reservation.getId());
        orchestrateExecution(ms -> ms.create(builder, reservation));
        return CompletableFuture.completedFuture(builder.build());
    }

    @Override
    @Async
    public Future<Reservation> retrieve(Long id) throws ExecutionException, InterruptedException{
        Reservation.ReservationBuilder builder = Reservation.builder().id(id);
        List<Future> futures = orchestrateExecution(ms -> ms.read(builder, id));
        waitCompletion(futures);
        return CompletableFuture.completedFuture(builder.build());
    }

    private <T> List<Future> orchestrateExecution(Function<ElementsMS, T> action){
        log.warn("new AsyncOrchestrator call thread " + UUID.randomUUID());
        List<Future> futures = new ArrayList<>();
        for(ElementsMS ms : reservationServerMS){
            log.warn("new ReservationMS call thread " + UUID.randomUUID());
            futures.add(executorService.submit(() -> action.apply(ms)));
        }
        return futures;
    }

    private void waitCompletion(List<Future> futures) throws InterruptedException, ExecutionException{
        for(Future future : futures){
            future.get();
        }
    }

}
