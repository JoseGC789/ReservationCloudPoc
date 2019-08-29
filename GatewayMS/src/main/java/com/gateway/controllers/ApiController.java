package com.gateway.controllers;

import com.gateway.clients.PassengerMS;
import com.gateway.domain.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController{

    private final PassengerMS passengerMS;

    public ApiController(PassengerMS passengerMS){
        this.passengerMS = passengerMS;
    }

    @PostMapping
    public ResponseEntity<Reservation> aggregateResult(@RequestBody Reservation reservation){
        Reservation bundle = passengerMS.create(reservation);
        return ResponseEntity.ok(bundle);
    }
}
