package com.gateway;

import com.gateway.clients.ItineraryMSConfig;
import com.gateway.clients.GroupMSConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({GroupMSConfig.class, ItineraryMSConfig.class})
@EnableAsync
public class GatewayMS{
    public static void main(String[] args){
        SpringApplication.run(GatewayMS.class, args);
    }
}
