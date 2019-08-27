package com.passengerms.services;

import com.passengerms.domain.entities.PassengerEntity;
import com.passengerms.repositories.PassengerRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
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
        PassengerEntity passenger = PassengerEntity.builder()
                .id(5L)
                .ageClass("adt")
                .firstName("ex")
                .lastName("last")
                .build();
        when(repository.findById(any())).thenReturn(Optional.of(passenger));
        Future<PassengerEntity> expected = CompletableFuture.completedFuture(passenger);
        Future<PassengerEntity> actual = async.retrieve(5L);
        assertEquals("Should be " + passenger.toString(), expected.get().toString(), actual.get().toString());
    }

    @Test
    public void testShouldThrowNotFound(){
        thrown.expect(ResponseStatusException.class);
        thrown.expectMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        when(repository.findById(any())).thenReturn(Optional.empty());
        async.retrieve(5L);
    }

    @Test
    public void testShouldThrowUnsupported(){
        thrown.expect(UnsupportedOperationException.class);
        async.consume(5L);
    }
}