package com.groupms.controllers;

import com.groupms.domain.dtos.Reservation;
import com.groupms.domain.entities.Group;
import com.groupms.services.GroupService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GroupServlet{

    private final GroupService service;

    public GroupServlet(GroupService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> retrieveGroup(@PathVariable Long id) {
        Reservation reservation = new Reservation(service.read(id));
        Group group = service.read(id);
        reservation.setGroup(group);
        return group.getPassengers().isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reservation);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Resource<Reservation>> postGroup(@PathVariable Long id, @RequestBody Reservation reservation) {
        reservation.getGroup().setId(id);
        reservation.setGroup(service.create(reservation.getGroup()));
        Resource<Reservation> dbReservation = new Resource<>(reservation);
        ControllerLinkBuilder link = linkTo(methodOn(GroupServlet.class).retrieveGroup(id));
        dbReservation.add(link.withSelfRel());
        return ResponseEntity.created(link.toUri()).body(dbReservation);
    }
}
