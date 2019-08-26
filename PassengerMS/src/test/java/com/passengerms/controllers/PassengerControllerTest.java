package com.passengerms.controllers;

import com.passengerms.domain.entities.PassengerEntity;
import com.passengerms.domain.vo.PassengerVO;
import com.passengerms.repositories.PassengerRepository;
import com.passengerms.services.PassengerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
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

    private PassengerEntity passenger;
    private ControllerLinkBuilder link;

    @Before
    public void setUp() throws ExecutionException, InterruptedException{
        passenger = PassengerEntity.builder()
                .id(ID)
                .ageClass("ADT")
                .firstName("EXAMPLE")
                .lastName("PASSENGER")
                .build();
        link = linkTo(methodOn(PassengerController.class).retrievePassenger(passenger.getId()));
    }

    @Test
    public void testShouldCreateWithResource() throws ExecutionException, InterruptedException{
        Resource<PassengerEntity> passengerResource = new Resource<>(passenger);
        passengerResource.add(link.withSelfRel());
        when(repository.save(any())).thenReturn(passenger);
        ResponseEntity<Resource<PassengerEntity>> expected = ResponseEntity.created(link.toUri()).body(passengerResource);
        ResponseEntity<Resource<PassengerEntity>> actual = controller.postPassenger(passenger);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldOkWithPassengerFound() throws ExecutionException, InterruptedException{
        when(service.consume(any())).thenReturn(CompletableFuture.completedFuture(passenger));
        ResponseEntity<PassengerEntity> expected = ResponseEntity.ok(passenger);
        ResponseEntity<PassengerEntity> actual = controller.retrievePassenger(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldAcceptWithResource() throws ExecutionException, InterruptedException{
        CompletableFuture<PassengerEntity> promise = CompletableFuture.completedFuture(passenger);
        Resource<PassengerVO> passengerResource = new Resource<>(PassengerVO.of(promise));
        passengerResource.add(link.withSelfRel());
        when(service.retrieve(any())).thenReturn(promise);
        ResponseEntity<Resource<PassengerVO>> expected = ResponseEntity.accepted().body(passengerResource);
        ResponseEntity<Resource<PassengerVO>> actual = controller.acceptPassenger(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }
}