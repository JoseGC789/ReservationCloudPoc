package com.passengerms.services;

import com.passengerms.domain.entities.Passenger;
import java.util.List;

public interface PassengerService{
    List<Passenger> read(Long id);
    List<Passenger> create(List<Passenger> passengers);
}
