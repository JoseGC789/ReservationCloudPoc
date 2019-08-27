package com.passengerms.controllers;

import com.passengerms.domain.entities.PassengerEntity;
import com.passengerms.domain.vo.PassengerVO;
import com.passengerms.repositories.PassengerRepository;
import com.passengerms.services.PassengerService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PassengerController{

    private final PassengerRepository repository;
    private final PassengerService service;

    public PassengerController(PassengerRepository repository, PassengerService service){
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Resource<PassengerVO>> acceptPassenger(@PathVariable Long id) throws ExecutionException, InterruptedException{
        Resource<PassengerVO> promise = new Resource<>(PassengerVO.of(service.retrieve(id)));
        promise.add(linkTo(methodOn(PassengerController.class).retrievePassenger(id)).withSelfRel());
        return ResponseEntity.accepted().body(promise);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerEntity> retrievePassenger(@PathVariable Long id) throws ExecutionException, InterruptedException{
        Future<PassengerEntity> passenger = service.consume(id);
        return ResponseEntity.ok(passenger.get());
    }

    @PostMapping
    public ResponseEntity<Resource<PassengerEntity>> postPassenger(@RequestBody PassengerEntity passengerEntity) throws ExecutionException, InterruptedException{
        Resource<PassengerEntity> passenger = new Resource<>(repository.save(passengerEntity));
        ControllerLinkBuilder link = linkTo(methodOn(PassengerController.class).retrievePassenger(passengerEntity.getId()));
        passenger.add(link.withSelfRel());
        return ResponseEntity.created(link.toUri()).body(passenger);
    }
}
