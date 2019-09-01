package com.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Itinerary{
    private List<Segment> segments;

    public static Itinerary empty(){
        return new Itinerary(Collections.emptyList());
    }
}
