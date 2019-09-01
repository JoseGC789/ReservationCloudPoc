package com.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Portion{
    private String departureAirport;
    private String arrivalAirport;
    private String duration;
}
