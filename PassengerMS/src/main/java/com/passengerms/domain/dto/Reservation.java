package com.passengerms.domain.dto;

import com.passengerms.domain.entities.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation{
    private Long id;
    private List<Passenger> passengers;
}
