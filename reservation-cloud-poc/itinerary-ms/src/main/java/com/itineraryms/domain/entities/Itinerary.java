package com.itineraryms.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary{
    @Id
    @JsonIgnore
    private Long itineraryId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Segment> segments;

    public static Itinerary empty(){
        return new Itinerary(null, Collections.emptyList());
    }
}
