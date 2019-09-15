package com.groupms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GroupMS{
    public static void main(String[] args){
        SpringApplication.run(GroupMS.class, args);
    }
}
