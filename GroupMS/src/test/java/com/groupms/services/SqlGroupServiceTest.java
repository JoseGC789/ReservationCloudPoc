package com.groupms.services;

import com.groupms.domain.entities.Group;
import com.groupms.domain.entities.Passenger;
import com.groupms.persistence.GroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Collections;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SqlGroupServiceTest{
    private static final Long ID = 5L;
    @InjectMocks
    private SqlGroupService service;
    @Mock
    private GroupRepository repository;
    private Group expected = new Group(ID, "TEST", Collections.singletonList(
            Passenger.builder()
                    .id(ID)
                    .ageClass("adt")
                    .firstName("ex")
                    .lastName("last")
                    .build()
    ));

    @Test
    public void testShouldRetrieve() {
        when(repository.findById(any())).thenReturn(Optional.of(expected));
        Group actual = service.read(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldRetrieveEmpty(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        Group expected = Group.empty();
        Group actual = service.read(ID);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }

    @Test
    public void testShouldCreate(){
        when(repository.save(any())).thenReturn(expected);
        Group actual = service.create(expected);
        assertEquals("Should be " + expected.toString(), expected.toString(), actual.toString());
    }
}