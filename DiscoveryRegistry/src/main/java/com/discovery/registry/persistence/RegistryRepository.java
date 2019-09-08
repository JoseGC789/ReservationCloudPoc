package com.discovery.registry.persistence;

import com.discovery.registry.domain.DiscoveryPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface RegistryRepository extends JpaRepository<DiscoveryPayload, String>{
    @Query("SELECT payload FROM DiscoveryPayload payload WHERE payload.tag = ?1")
    Set<DiscoveryPayload> findAllByTag(String tag);
}
