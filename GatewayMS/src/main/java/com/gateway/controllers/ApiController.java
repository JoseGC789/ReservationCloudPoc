package com.gateway.controllers;

import com.gateway.domain.Reservation;
import com.gateway.services.Orchestrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController{

    private final Orchestrator orchestrator;

    public ApiController(Orchestrator orchestrator){
        this.orchestrator = orchestrator;
    }

    @PostMapping
    public ResponseEntity<Reservation> aggregateResult(@RequestBody Reservation reservation){
        return ResponseEntity.ok(orchestrator.create(reservation));
    }
}
