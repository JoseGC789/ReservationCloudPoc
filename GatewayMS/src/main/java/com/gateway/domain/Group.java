package com.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group{
    private String type;
    private List<Passenger> passengers;

    public static Group empty(){
        return new Group("", Collections.emptyList());
    }

}
