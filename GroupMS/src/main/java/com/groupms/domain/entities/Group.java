package com.groupms.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="GroupOfPassengers")
public class Group{
    @Id
    @JsonIgnore
    private Long id;
    private String type;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    public static Group empty(){
        return new Group(null, "", Collections.emptyList());
    }
}
