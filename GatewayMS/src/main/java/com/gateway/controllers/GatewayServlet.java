package com.gateway.controllers;

import com.gateway.clients.DiscoveryRegistryMS;
import com.gateway.domain.DiscoveryPayload;
import com.gateway.domain.Reservation;
import com.gateway.services.Orchestrator;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
public class GatewayServlet{

    private final Orchestrator orchestrator;
    private final DiscoveryRegistryMS discoveryRegistryMS;

    public GatewayServlet(Orchestrator orchestrator, DiscoveryRegistryMS discoveryRegistryMS){
        this.orchestrator = orchestrator;
        this.discoveryRegistryMS = discoveryRegistryMS;
    }

    @GetMapping("/test/test")
    public ResponseEntity<Set<DiscoveryPayload>> asdasa(@RequestBody Map<String, Object> request){
        return ResponseEntity.ok(discoveryRegistryMS.retrieveServiceData(request));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Resource<String>> create(@PathVariable Long id, @RequestBody Reservation reservation) throws ExecutionException, InterruptedException{
        reservation.setId(id);
        orchestrator.create(reservation);
        Resource<String> resource = new Resource<>("SCHEDULED CREATE");
        ControllerLinkBuilder link = linkTo(methodOn(GatewayServlet.class).retrieveResult(id));
        resource.add(link.withSelfRel());
        return ResponseEntity.created(link.toUri()).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<String>> aggregateResult(@PathVariable Long id) throws ExecutionException, InterruptedException{
        orchestrator.retrieve(id);
        Resource<String> resource = new Resource<>("SCHEDULED REFRESH");
        ControllerLinkBuilder link = linkTo(methodOn(GatewayServlet.class).retrieveResult(id));
        resource.add(link.withSelfRel());
        return ResponseEntity.accepted().body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveResult(@PathVariable Long id) throws ExecutionException, InterruptedException{
        return ResponseEntity.ok(orchestrator.consume(id).get());
    }

}
