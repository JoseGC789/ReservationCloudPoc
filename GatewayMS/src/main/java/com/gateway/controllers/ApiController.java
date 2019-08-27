package com.gateway.controllers;

import com.gateway.clients.PassengerMS;
import com.gateway.domain.ApiBundle;
import com.gateway.domain.Passenger;
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
    public ResponseEntity<ApiBundle> aggregateResult(@RequestBody Passenger passenger){
        ApiBundle bundle = new ApiBundle(passengerMS.create(passenger));
        return ResponseEntity.ok(bundle);
    }
}
