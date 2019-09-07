package com.discovery.registry.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryServlet{

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody Object object){
        return ResponseEntity.noContent().build();
    }

}
