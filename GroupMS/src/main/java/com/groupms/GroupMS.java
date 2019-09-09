package com.groupms;

import com.groupms.clients.DiscoveryProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DiscoveryProperties.class)
public class GroupMS{
    public static void main(String[] args){
        SpringApplication.run(GroupMS.class, args);
    }
}
