package com.gateway.clients;

import com.gateway.domain.DiscoveryPayload;
import com.gateway.domain.Reservation;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

public interface ElementsMS{
    void create(Reservation reservation, Set<DiscoveryPayload> locations);
    List<Future<Map<String, Object>>> read(Long id, Set<DiscoveryPayload> locations);
}
