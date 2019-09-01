package com.passengerms.persistence;

import com.passengerms.domain.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{
    @Query("SELECT p FROM Passenger p WHERE p.reservationId = ?1 AND p.flown = false")
    List<Passenger> findPassengerById (Long reservationId);
}
