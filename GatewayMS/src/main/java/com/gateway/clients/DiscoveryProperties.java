package com.gateway.clients;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("discovery")
@Setter
@Getter
public class DiscoveryProperties{
    private static final String COLON = ":";
    private static final String PREFIX = "http://";
    private static final String DASH = "/";
    private String host;
    private String port;
    private String context;

    String buildUri(){
        return PREFIX + host + COLON + port + context;
    }

}
