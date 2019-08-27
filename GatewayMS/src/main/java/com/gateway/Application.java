package com.gateway;

import com.gateway.clients.PassengerMSConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(PassengerMSConfig.class)
public class Application{
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
