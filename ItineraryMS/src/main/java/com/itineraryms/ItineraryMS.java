package com.itineraryms;

import com.itineraryms.client.DiscoveryProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DiscoveryProperties.class)
public class ItineraryMS{
    public static void main(String[] args){
        SpringApplication.run(ItineraryMS.class, args);
    }
}