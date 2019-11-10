import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;

open module GatewayMS {
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.cloud.netflix.eureka.client;
    requires spring.hateoas;
    requires spring.web;
    requires spring.core;
    requires spring.context;
    requires jackson.annotations;
    requires lombok;
    requires slf4j.api;
    requires eureka.client;
}