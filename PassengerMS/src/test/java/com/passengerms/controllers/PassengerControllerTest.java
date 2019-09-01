package com.passengerms.controllers;

import com.passengerms.domain.dto.Reservation;
import com.passengerms.domain.entities.Passenger;
import com.passengerms.services.PassengerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RunWith(MockitoJUnitRunner.class)
public class PassengerControllerTest{
    private static final Long ID = 1L;
    @InjectMocks
    private PassengerServlet controller;
    @Mock
    private PassengerService service;

    private Reservation reservation;
    private List<Passenger> passengerList;
    private ControllerLinkBuilder link;

    @Before
    public void setUp() {
        Passenger passenger = Passenger.builder()
                .id(ID)
                .ageClass("ADT")
                .firstName("EXAMPLE")
                .lastName("PASSENGER")
                .build();
        passengerList = Arrays.asList(passenger, passenger);
        reservation = new Reservation(ID, passengerList);
        link = linkTo(methodOn(PassengerServlet.class).retrievePassenger(passenger.getId()));
    }

    @Test
    public void testShouldCreateWithResource() {
        Resource<Reservation> passengerResource = new Resource<>(reservation);
        passengerResource.add(link.withSelfRel());
        when(service.create(any())).thenReturn(passengerList);
        ResponseEntity<Resource<Reservation>> expected = ResponseEntity.created(link.toUri()).body(passengerResource);
        ResponseEntity<Resource<Reservation>> actual = controller.postPassenger(reservation);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldOkWithPassengerFound() {
        when(service.read(any())).thenReturn(passengerList);
        ResponseEntity<List<Passenger>> expected = ResponseEntity.ok(passengerList);
        ResponseEntity<List<Passenger>> actual = controller.retrievePassenger(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldNoContent() {
        when(service.read(any())).thenReturn(Collections.emptyList());
        ResponseEntity<List<Passenger>> expected = ResponseEntity.noContent().build();
        ResponseEntity<List<Passenger>> actual = controller.retrievePassenger(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }
}