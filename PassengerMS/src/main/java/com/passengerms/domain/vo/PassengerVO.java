package com.passengerms.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.passengerms.domain.entities.Passenger;
import lombok.Value;
import java.util.List;
import java.util.concurrent.Future;

@Value
public class PassengerVO{
    @JsonIgnore
    private Future<List<Passenger>> entityPromise;
    private TaskStatus status;

    public static PassengerVO of(Future<List<Passenger>> promise){
        return new PassengerVO(promise, TaskStatus.SCHEDULED);
    }

    private enum TaskStatus{
        SCHEDULED,
        FINISHED;
    }
}
