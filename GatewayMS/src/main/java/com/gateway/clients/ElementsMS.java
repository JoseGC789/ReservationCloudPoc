package com.gateway.clients;

import com.gateway.domain.Reservation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public interface ElementsMS{
    void create(Reservation reservation);
    List<Future<Map<String, Object>>> read(Long id);
}
