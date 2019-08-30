package com.passengerms.services;

import com.passengerms.domain.entities.Passenger;
import com.passengerms.repositories.PassengerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SqlPassengerService implements PassengerService{
    private final PassengerRepository repository;

    public SqlPassengerService(PassengerRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Passenger> read(Long id){
        return repository.findPassengerById(id);
    }

    @Override
    public List<Passenger> create(List<Passenger> passengers){
        return repository.saveAll(passengers);
    }
}
