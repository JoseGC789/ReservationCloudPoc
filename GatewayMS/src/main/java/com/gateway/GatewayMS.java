package com.gateway;

import com.gateway.clients.DiscoveryProperties;
import com.gateway.configs.DiscoveryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(DiscoveryProperties.class)
@EnableAsync
public class GatewayMS{
    public static void main(String[] args){
        SpringApplication.run(GatewayMS.class, args);
    }
}
