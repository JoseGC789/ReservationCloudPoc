package com.itineraryms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItineraryMS{
    public static void main(String[] args){
        SpringApplication.run(ItineraryMS.class, args);
    }
}