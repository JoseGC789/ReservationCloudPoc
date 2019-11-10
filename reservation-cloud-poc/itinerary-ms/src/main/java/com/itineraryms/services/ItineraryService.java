package com.itineraryms.services;

import com.itineraryms.domain.entities.Itinerary;
import com.itineraryms.persistence.ItineraryRepository;
import org.springframework.stereotype.Service;

@Service
public class ItineraryService{
    private final ItineraryRepository itineraryRepository;

    public ItineraryService(ItineraryRepository itineraryRepository){
        this.itineraryRepository = itineraryRepository;
    }

    public Itinerary create(Itinerary itinerary){
        return itineraryRepository.save(itinerary);
    }
    public Itinerary read(Long id){
        return itineraryRepository.findById(id).orElseGet(Itinerary::empty);
    }
}
