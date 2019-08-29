package com.passengerms.controllers;

import com.passengerms.domain.dto.Reservation;
import com.passengerms.domain.entities.Passenger;
import com.passengerms.domain.vo.PassengerVO;
import com.passengerms.repositories.PassengerRepository;
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
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RunWith(MockitoJUnitRunner.class)
public class PassengerControllerTest{
    private static final Long ID = 1L;
    @InjectMocks
    private PassengerController controller;
    @Mock
    private PassengerRepository repository;
    @Mock
    private PassengerService service;

    private Reservation reservation;
    private List<Passenger> passengerList;
    private ControllerLinkBuilder link;

    @Before
    public void setUp() throws ExecutionException, InterruptedException{
        Passenger passenger = Passenger.builder()
                .id(ID)
                .ageClass("ADT")
                .firstName("EXAMPLE")
                .lastName("PASSENGER")
                .build();
        passengerList = Arrays.asList(passenger, passenger);
        reservation = new Reservation(ID, passengerList);
        link = linkTo(methodOn(PassengerController.class).retrievePassenger(passenger.getId()));
    }

    @Test
    public void testShouldCreateWithResource() throws ExecutionException, InterruptedException{
        Resource<Reservation> passengerResource = new Resource<>(reservation);
        passengerResource.add(link.withSelfRel());
        when(repository.saveAll(any())).thenReturn(passengerList);
        ResponseEntity<Resource<Reservation>> expected = ResponseEntity.created(link.toUri()).body(passengerResource);
        ResponseEntity<Resource<Reservation>> actual = controller.postPassenger(reservation);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldOkWithPassengerFound() throws ExecutionException, InterruptedException{
        when(service.consume(any())).thenReturn(CompletableFuture.completedFuture(passengerList));
        ResponseEntity<List<Passenger>> expected = ResponseEntity.ok(passengerList);
        ResponseEntity<List<Passenger>> actual = controller.retrievePassenger(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldAcceptWithResource() throws ExecutionException, InterruptedException{
        CompletableFuture<List<Passenger>> promise = CompletableFuture.completedFuture(passengerList);
        Resource<PassengerVO> passengerResource = new Resource<>(PassengerVO.of(promise));
        passengerResource.add(link.withSelfRel());
        when(service.retrieve(any())).thenReturn(promise);
        ResponseEntity<Resource<PassengerVO>> expected = ResponseEntity.accepted().body(passengerResource);
        ResponseEntity<Resource<PassengerVO>> actual = controller.acceptPassenger(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }
}