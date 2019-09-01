package com.passengerms.services;

import com.passengerms.domain.entities.Passenger;
import com.passengerms.persistence.PassengerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SqlPassengerServiceTest{
    @InjectMocks
    private SqlPassengerService service;
    @Mock
    private PassengerRepository repository;
    private List<Passenger> expected = Collections.singletonList(
            Passenger.builder()
                    .id(5L)
                    .ageClass("adt")
                    .firstName("ex")
                    .lastName("last")
                    .build()
    );

    @Test
    public void testShouldRetrieve() {
        when(repository.findPassengerById(any())).thenReturn(expected);
        List<Passenger> actual = service.read(5L);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldThrowUnsupported(){
        when(repository.saveAll(any())).thenReturn(expected);
        List<Passenger> actual = service.create(expected);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }
}