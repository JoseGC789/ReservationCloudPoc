package com.groupms.domain.dtos;

import com.groupms.domain.entities.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation{
    private Group group;
}
