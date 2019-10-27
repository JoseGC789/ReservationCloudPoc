package com.gateway.clients;

import com.gateway.domain.Reservation;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

public abstract class ElementsHandler implements ElementsMS{
    private final ExecutorService executor;
    private final EurekaClient client;

    public ElementsHandler(ExecutorService executor, EurekaClient client) {
        this.executor = executor;
        this.client = client;
    }

    @Override
    public final void create(Reservation reservation){
        handleElements(location -> postToService(reservation, location));
    }

    @Override
    public final List<Future<Map<String, Object>>> read(Long id){
        return handleElements(location -> getFromService(id, location));
    }

    private List<Future<Map<String, Object>>> handleElements(Function<String,Map<String, Object>> method){
        List<Future<Map<String, Object>>> futures = new ArrayList<>();
        for(Application app : client.getApplications().getRegisteredApplications()){
            futures.add(executor.submit(() -> method.apply(app.getInstances().get(0).getHomePageUrl())));
        }
        return futures;
    }

    protected abstract Map<String, Object> postToService(Reservation reservation, String location);

    protected abstract Map<String, Object> getFromService(Long id, String location);

}
