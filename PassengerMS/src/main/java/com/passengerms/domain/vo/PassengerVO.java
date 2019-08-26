package com.passengerms.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.passengerms.domain.entities.PassengerEntity;
import lombok.Value;
import java.util.concurrent.Future;

@Value
public class PassengerVO{
    @JsonIgnore
    private Future<PassengerEntity> entityPromise;
    private TaskStatus status;

    public static PassengerVO of(Future<PassengerEntity> promise){
        return new PassengerVO(promise, TaskStatus.SCHEDULED);
    }

    private enum TaskStatus{
        SCHEDULED,
        FINISHED;
    }
}
