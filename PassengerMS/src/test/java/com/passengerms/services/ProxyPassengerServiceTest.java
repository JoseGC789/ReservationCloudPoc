package com.passengerms.services;

import com.passengerms.domain.entities.Passenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProxyPassengerServiceTest{
    @InjectMocks
    private PassengerProxy proxy;
    @Mock
    private PassengerService service;

    @Test
    public void testShouldRetrieve(){
        List<Passenger> passenger = Collections.singletonList(
                Passenger.builder()
                        .id(5L)
                        .ageClass("adt")
                        .firstName("ex")
                        .lastName("last")
                        .build()
        );
        CompletableFuture<List<Passenger>> expected = CompletableFuture.completedFuture(passenger);
        when(proxy.retrieve(any())).thenReturn(expected);
        Future<List<Passenger>> actual = service.retrieve(5L);
        assertEquals("Should be " + passenger.toString(), expected.toString(), actual.toString());
    }

}