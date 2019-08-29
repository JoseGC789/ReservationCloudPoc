package com.passengerms.services;

import com.passengerms.domain.entities.Passenger;
import com.passengerms.repositories.PassengerRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AsyncPassengerServiceTest{
    @InjectMocks
    private AsyncPassengerService async;
    @Mock
    private PassengerRepository repository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testShouldRetrieve() throws ExecutionException, InterruptedException{
        List<Passenger> passenger = Collections.singletonList(
                Passenger.builder()
                        .id(5L)
                        .ageClass("adt")
                        .firstName("ex")
                        .lastName("last")
                        .build()
        );
        when(repository.findPassengerById(any())).thenReturn(passenger);
        Future<List<Passenger>> expected = CompletableFuture.completedFuture(passenger);
        Future<List<Passenger>> actual = async.retrieve(5L);
        assertEquals("Should be " + passenger.toString(), expected.get().toString(), actual.get().toString());
    }

    @Test
    public void testShouldThrowUnsupported(){
        thrown.expect(UnsupportedOperationException.class);
        async.consume(5L);
    }
}