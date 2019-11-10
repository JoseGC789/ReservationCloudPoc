package com.itineraryms.controllers;

import com.itineraryms.domain.dtos.Reservation;
import com.itineraryms.domain.entities.Itinerary;
import com.itineraryms.services.ItineraryService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class ItineraryServlet{

    private final ItineraryService service;

    public ItineraryServlet(ItineraryService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> retrieveItinerary(@PathVariable Long id) {
        Reservation reservation = new Reservation();
        Itinerary itinerary = service.read(id);
        reservation.setItinerary(itinerary);
        return itinerary.getSegments().isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reservation);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Resource<Reservation>> postItinerary(@PathVariable Long id,@Valid @RequestBody Reservation reservation) {
        reservation.getItinerary().setItineraryId(id);
        reservation.setItinerary(service.create(reservation.getItinerary()));
        Resource<Reservation> dbReservation = new Resource<>(reservation);
        ControllerLinkBuilder link = linkTo(ControllerLinkBuilder.methodOn(ItineraryServlet.class).retrieveItinerary(id));
        dbReservation.add(link.withSelfRel());
        return ResponseEntity.created(link.toUri()).body(dbReservation);
    }
}