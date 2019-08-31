package com.gateway.clients;

import com.gateway.domain.Reservation;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractMs<T> implements ReservationServerMS{
    @Override
    public final Reservation.ReservationBuilder create(Reservation.ReservationBuilder builder, Reservation reservation){
        return callService(builder, () -> postToService(reservation));
    }

    @Override
    public final Reservation.ReservationBuilder read(Reservation.ReservationBuilder builder, Long id){
        return callService(builder, () -> readFromService(id));
    }

    private Reservation.ReservationBuilder callService(Reservation.ReservationBuilder builder, Supplier<T> method){
        return handleField(method.get()).apply(builder);
    }

    protected abstract T postToService(Reservation reservation);

    protected abstract T readFromService(Long id);

    protected abstract Function<Reservation.ReservationBuilder, Reservation.ReservationBuilder> handleField(T data);
}
