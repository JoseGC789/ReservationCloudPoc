package com.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Passenger{
    private String firstName;
    private String lastName;
    private String ageClass;
}