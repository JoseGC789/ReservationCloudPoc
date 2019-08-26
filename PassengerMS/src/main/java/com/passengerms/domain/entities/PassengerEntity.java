package com.passengerms.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerEntity{
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String ageClass;
}
