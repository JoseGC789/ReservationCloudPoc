package com.gateway.controllers;

import com.gateway.domain.Reservation;
import com.gateway.services.Orchestrator;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GatewayServlet{

    private final Orchestrator orchestrator;

    public GatewayServlet(Orchestrator orchestrator){
        this.orchestrator = orchestrator;
    }

    @PostMapping
    public ResponseEntity<Resource<String>> create(@RequestBody Reservation reservation) throws ExecutionException, InterruptedException{
        orchestrator.create(reservation);
        Resource<String> resource = new Resource<>("SCHEDULED CREATE");
        ControllerLinkBuilder link = linkTo(methodOn(GatewayServlet.class).retrieveResult(reservation.getId()));
        resource.add(link.withSelfRel());
        return ResponseEntity.created(link.toUri()).body(resource);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Resource<String>> aggregateResult(@PathVariable Long id) throws ExecutionException, InterruptedException{
        orchestrator.retrieve(id);
        Resource<String> resource = new Resource<>("SCHEDULED REFRESH");
        ControllerLinkBuilder link = linkTo(methodOn(GatewayServlet.class).retrieveResult(id));
        resource.add(link.withSelfRel());
        return ResponseEntity.accepted().body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> retrieveResult(@PathVariable Long id) throws ExecutionException, InterruptedException{
        return ResponseEntity.ok(orchestrator.consume(id).get());
    }

}
