package com.itineraryms.services;

import com.itineraryms.client.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.net.InetAddress;

@Service
@Slf4j
public class SubscriberService implements CommandLineRunner{
    private final Environment environment;
    private final DiscoveryClient client;

    public SubscriberService(Environment environment, DiscoveryClient client){
        this.environment = environment;
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception{
        HttpStatus status = null;
        String localHost = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String context = environment.getProperty("server.servlet.contextPath");
        String uri = String.join("", localHost, ":", port, context);
        try{
           status = client.subscribe(uri);
        }finally{
            if(HttpStatus.NO_CONTENT != status){
                log.error("FAILED TO ESTABLISH CONNECTION WITH SERVICE DISCOVERY WITH: "
                        + uri
                        + " RESPONDS WITH STATUS: "
                        + status);
            }
        }
    }
}
