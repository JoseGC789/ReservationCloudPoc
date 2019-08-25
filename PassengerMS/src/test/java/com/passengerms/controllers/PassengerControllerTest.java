package com.passengerms.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PassengerControllerTest{
    @InjectMocks
    private PassengerController controller;

    @Test
    public void testShouldReturnOk(){
        ResponseEntity<String> expected = ResponseEntity.ok("OK");
        ResponseEntity<String> actual = controller.getPassenger();

        assertEquals("Should be: " + expected.toString(), expected.toString(), actual.toString());
    }
}