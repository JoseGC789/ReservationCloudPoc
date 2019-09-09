package com.discovery.registry.persistence;

import com.discovery.registry.domain.DiscoveryPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface RegistryRepository extends JpaRepository<DiscoveryPayload, String>{
    @Query("SELECT payload FROM DiscoveryPayload payload WHERE payload.tag = :tag")
    Set<DiscoveryPayload> findAllByTag(@Param("tag") String tag);

    @Query("SELECT payload FROM DiscoveryPayload payload WHERE payload.tag IN :tags")
    Set<DiscoveryPayload> findAllInTags(@Param("tags") List<String> tags);
}
