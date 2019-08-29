package com.passengerms.controllers;

import com.passengerms.domain.dto.Reservation;
import com.passengerms.domain.entities.Passenger;
import com.passengerms.domain.vo.PassengerVO;
import com.passengerms.repositories.PassengerRepository;
import com.passengerms.services.PassengerService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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
    public ResponseEntity<List<Passenger>> retrievePassenger(@PathVariable Long id) throws ExecutionException, InterruptedException{
        Future<List<Passenger>> passenger = service.consume(id);
        return passenger.get().isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(passenger.get());
    }

    @PostMapping
    public ResponseEntity<Resource<Reservation>> postPassenger(@RequestBody Reservation reservation) throws ExecutionException, InterruptedException{
        reservation.getPassengers().forEach(passenger -> passenger.setReservationId(reservation.getId()));
        reservation.setPassengers(repository.saveAll(reservation.getPassengers()));
        Resource<Reservation> dbReservation = new Resource<>(reservation);
        ControllerLinkBuilder link = linkTo(methodOn(PassengerController.class).retrievePassenger(reservation.getId()));
        dbReservation.add(link.withSelfRel());
        return ResponseEntity.created(link.toUri()).body(dbReservation);
    }
}
