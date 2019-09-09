package com.itineraryms.controllers;

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
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class ItineraryServlet{

    private final ItineraryService service;

    public ItineraryServlet(ItineraryService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itinerary> retrieveItinerary(@PathVariable Long id) {
        Itinerary itinerary = service.read(id);
        return itinerary.getSegments().isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(itinerary);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Resource<Itinerary>> postItinerary(@PathVariable Long id, @RequestBody Itinerary itinerary) {
        itinerary.setItineraryId(id);
        itinerary = service.create(itinerary);
        Resource<Itinerary> dbReservation = new Resource<>(itinerary);
        ControllerLinkBuilder link = linkTo(ControllerLinkBuilder.methodOn(ItineraryServlet.class).retrieveItinerary(id));
        dbReservation.add(link.withSelfRel());
        return ResponseEntity.created(link.toUri()).body(dbReservation);
    }
}