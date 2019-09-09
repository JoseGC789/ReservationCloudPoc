package com.discovery.registry.controllers;

import com.discovery.registry.domain.DiscoveryPayload;
import com.discovery.registry.domain.RegistrationPayload;
import com.discovery.registry.persistence.RegistryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
public class DiscoveryServlet{

    private final RegistryRepository repository;

    public DiscoveryServlet(RegistryRepository repository){
        this.repository = repository;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody RegistrationPayload object){
        Arrays.stream(object.getTags())
                .map(tag -> new DiscoveryPayload(null, tag, object.getOthers(), object.getHostname()))
                .forEach(repository::save);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tag}")
    public ResponseEntity<Set<DiscoveryPayload>> asd(@PathVariable String tag){
        return ResponseEntity.ok(repository.findAllByTag(tag));
    }

    @GetMapping
    public ResponseEntity<Set<DiscoveryPayload>> asd2(@RequestParam(name = "tags") List<String> tags){
        log.warn(tags.toString());
        return ResponseEntity.ok(repository.findAllInTags(tags));
    }

}
