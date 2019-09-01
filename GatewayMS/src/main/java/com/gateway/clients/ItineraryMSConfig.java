package com.gateway.clients;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "discovery.ms.itinerary")
@Getter
@Setter
public class ItineraryMSConfig{
    private static final String COLON = ":";
    private static final String PREFIX = "http://";
    private static final String DASH = "/";
    private String dir;
    private String port;
    private String context;

    String buildUri(){
        return PREFIX + dir + COLON + port + context + DASH;
    }
}
