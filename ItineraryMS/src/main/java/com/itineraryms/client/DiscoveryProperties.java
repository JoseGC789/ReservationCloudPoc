package com.itineraryms.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("discovery")
@Getter
@Setter
@ToString
public class DiscoveryProperties{

    private SubscriptionHostName hostName;
    private ItineraryRegistration payload;

    public String buildUri(){
        return hostName.buildUri();
    }

    public RegistrationPayload buildRegistration(String hostname){
        return RegistrationPayload.from(payload.getTags(), hostname);
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public static class RegistrationPayload{
        private static final String KEY_TAG = "tags";
        private static final String KEY_HOST = "hostname";
        private final Map<String, Object> payload;

        private static RegistrationPayload from(String[] tags, String hostname){
            Map<String, Object> payload = new HashMap<>();
            payload.put(KEY_TAG, tags);
            payload.put(KEY_HOST, hostname);
            return new RegistrationPayload(Collections.unmodifiableMap(payload));
        }
    }

    @Getter
    @Setter
    @ToString
    public static class SubscriptionHostName{
        private static final String COLON = ":";
        private static final String PREFIX = "http://";
        private String host;
        private String port;
        private String context;
        private String endpoint;

        private String buildUri(){
            return PREFIX + host + COLON + port + context + endpoint;
        }

    }

    @Getter
    @Setter
    @ToString
    public static class ItineraryRegistration{
        private String[] tags;
    }
}
