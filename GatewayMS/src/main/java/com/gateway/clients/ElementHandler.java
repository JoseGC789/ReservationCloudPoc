package com.gateway.clients;

import com.gateway.domain.DiscoveryPayload;
import com.gateway.domain.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

@Slf4j
public abstract class ElementHandler implements ElementsMS{
    @Autowired
    private ExecutorService executorService;

    @Override
    public final void create(Reservation reservation, Set<DiscoveryPayload> locations){
        handleElements(location -> postToService(reservation, location), locations);
    }

    @Override
    public final List<Future<Map<String, Object>>> read(Long id, Set<DiscoveryPayload> locations){
        return handleElements(location -> getFromService(id, location), locations);
    }

    private List<Future<Map<String, Object>>> handleElements(Function<DiscoveryPayload,Map<String, Object>> method, Set<DiscoveryPayload> locations){
        log.error("IN HANDLE");
        List<Future<Map<String, Object>>> futures = new ArrayList<>();
        locations.forEach(location -> futures.add(executorService.submit(() -> method.apply(location))));
        return futures;
    }

    protected abstract Map<String, Object> postToService(Reservation reservation, DiscoveryPayload location);

    protected abstract Map<String, Object> getFromService(Long id, DiscoveryPayload location);

}
