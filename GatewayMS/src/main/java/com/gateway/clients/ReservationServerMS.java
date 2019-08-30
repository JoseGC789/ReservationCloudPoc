package com.gateway.clients;

import com.gateway.domain.Reservation;

public interface ReservationServerMS{
    Reservation.ReservationBuilder create(Reservation.ReservationBuilder builder, Reservation reservation);
    Reservation.ReservationBuilder read(Reservation.ReservationBuilder builder, Long id);
}
