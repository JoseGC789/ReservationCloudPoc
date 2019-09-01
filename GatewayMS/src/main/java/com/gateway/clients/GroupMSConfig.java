package com.gateway.clients;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "discovery.ms.group")
@Getter
@Setter
public class GroupMSConfig{
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
