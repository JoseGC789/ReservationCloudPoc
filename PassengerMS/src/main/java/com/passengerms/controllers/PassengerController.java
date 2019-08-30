package com.passengerms.controllers;

import com.passengerms.domain.dto.Reservation;
import com.passengerms.domain.entities.Passenger;
import com.passengerms.services.PassengerService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PassengerController{

    private final PassengerService service;

    public PassengerController(PassengerService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Passenger>> retrievePassenger(@PathVariable Long id) {
        List<Passenger> passenger = service.read(id);
        return passenger.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(passenger);
    }

    @PostMapping
    public ResponseEntity<Resource<Reservation>> postPassenger(@RequestBody Reservation reservation) {
        reservation.getPassengers().forEach(passenger -> passenger.setReservationId(reservation.getId()));
        reservation.setPassengers(service.create(reservation.getPassengers()));
        Resource<Reservation> dbReservation = new Resource<>(reservation);
        ControllerLinkBuilder link = linkTo(methodOn(PassengerController.class).retrievePassenger(reservation.getId()));
        dbReservation.add(link.withSelfRel());
        return ResponseEntity.created(link.toUri()).body(dbReservation);
    }
}
