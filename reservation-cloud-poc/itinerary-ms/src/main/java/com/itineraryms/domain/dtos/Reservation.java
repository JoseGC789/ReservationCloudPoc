package com.itineraryms.domain.dtos;

import com.itineraryms.domain.entities.Itinerary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation{
    @NotNull
    private Itinerary itinerary;
}
