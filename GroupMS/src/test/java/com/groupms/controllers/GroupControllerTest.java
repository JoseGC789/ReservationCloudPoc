package com.groupms.controllers;

import com.groupms.domain.dtos.Reservation;
import com.groupms.domain.entities.Group;
import com.groupms.domain.entities.Passenger;
import com.groupms.services.GroupService;
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
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RunWith(MockitoJUnitRunner.class)
public class GroupControllerTest{
    private static final Long ID = 1L;
    @InjectMocks
    private GroupServlet controller;
    @Mock
    private GroupService service;

    private Reservation reservation;
    private Group group;
    private ControllerLinkBuilder link;

    @Before
    public void setUp() {
        Passenger passenger = Passenger.builder()
                .id(ID)
                .ageClass("ADT")
                .firstName("EXAMPLE")
                .lastName("PASSENGER")
                .build();
        String groupType = "Example";
        group = new Group(ID, groupType, Arrays.asList(passenger, passenger));
        reservation = new Reservation();
        reservation.setGroup(group);
        link = linkTo(methodOn(GroupServlet.class).retrieveGroup(group.getId()));
    }

    @Test
    public void testShouldCreateWithResource() {
        Resource<Reservation> reservationResource = new Resource<>(reservation);
        reservationResource.add(link.withSelfRel());
        when(service.create(any())).thenReturn(group);
        ResponseEntity<Resource<Reservation>> expected = ResponseEntity.created(link.toUri()).body(reservationResource);
        ResponseEntity<Resource<Reservation>> actual = controller.postGroup(ID, reservation);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldOkWithGroupFound() {
        when(service.read(any())).thenReturn(group);
        ResponseEntity<Group> expected = ResponseEntity.ok(group);
        ResponseEntity<Group> actual = controller.retrieveGroup(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldNoContent() {
        when(service.read(any())).thenReturn(Group.empty());
        ResponseEntity<Group> expected = ResponseEntity.noContent().build();
        ResponseEntity<Group> actual = controller.retrieveGroup(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }
}