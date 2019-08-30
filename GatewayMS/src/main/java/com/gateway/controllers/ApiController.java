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
public class ApiController{

    private final Orchestrator orchestrator;

    public ApiController(Orchestrator orchestrator){
        this.orchestrator = orchestrator;
    }

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation){
        return ResponseEntity.ok(orchestrator.create(reservation));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Resource<String>> aggregateResult(@PathVariable Long id){
        orchestrator.retrieve(id);
        Resource<String> resource = new Resource<>("SCHEDULED");
        ControllerLinkBuilder link = linkTo(methodOn(ApiController.class).retrieveResult(id));
        resource.add(link.withSelfRel());
        return ResponseEntity.accepted().body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> retrieveResult(@PathVariable Long id){
        return ResponseEntity.ok(orchestrator.consume(id));
    }

}
