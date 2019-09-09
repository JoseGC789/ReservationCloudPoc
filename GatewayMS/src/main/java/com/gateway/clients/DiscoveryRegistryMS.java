package com.gateway.clients;

import com.gateway.domain.DiscoveryPayload;
import java.util.Set;

public interface DiscoveryRegistryMS{
    public Set<DiscoveryPayload> retrieveServiceData();
}
