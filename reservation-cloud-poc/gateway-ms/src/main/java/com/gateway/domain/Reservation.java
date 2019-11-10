package com.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reservation{
    @JsonIgnore
    private Long id;
    private Map<String, Object> elements;
}
