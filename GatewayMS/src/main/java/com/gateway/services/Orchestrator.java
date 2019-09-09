package com.gateway.services;

import com.gateway.domain.DiscoveryPayload;
import com.gateway.domain.Reservation;
import java.util.Set;
import java.util.concurrent.Future;

public interface Orchestrator{

    void create(Reservation reservation, Set<DiscoveryPayload> locations);

    Future<Reservation> retrieve(Long id, Set<DiscoveryPayload> locations);

    default Future<Reservation> consume(Long id, Set<DiscoveryPayload> locations){
        throw new UnsupportedOperationException();
    }
}
