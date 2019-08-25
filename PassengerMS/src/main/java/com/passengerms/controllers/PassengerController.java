package com.passengerms.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerController{

    @GetMapping("/getPassenger")
    public ResponseEntity<String> getPassenger(){
        return ResponseEntity.ok("OK");
    }
}
