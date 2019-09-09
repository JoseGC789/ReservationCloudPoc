package com.gateway.clients;

import com.gateway.domain.DiscoveryPayload;
import java.util.Set;

public interface DiscoveryRegistryMS{
    Set<DiscoveryPayload> retrieveServiceData(Set<String> keySet);
    Set<DiscoveryPayload> retrieveServiceData();
}
