package com.groupms.services;

import com.groupms.clients.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.net.InetAddress;

@Service
@Slf4j
public class SubscriptionService implements CommandLineRunner{
    private final Environment environment;
    private final DiscoveryClient client;

    public SubscriptionService(Environment environment, DiscoveryClient client){
        this.environment = environment;
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception{
        HttpStatus status = null;
        String localHost = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String context = environment.getProperty("server.servlet.contextPath");
        String uri = localHost + ":" + port + context;
        try{
            status = client.subscribe(uri);
        }finally{
            if(HttpStatus.NO_CONTENT != status){
                log.error("Failed to connect with Service Registry with uri: "
                        + uri
                        + " - Registry Response: "
                        + status);
            }else {
                log.warn("Connected to Service Registry!");
            }
        }
    }
}