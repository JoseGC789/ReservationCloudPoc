package com.gateway.configs;

import com.gateway.controllers.DiscoveryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DiscoveryConfig implements WebMvcConfigurer{

    @Autowired
    private DiscoveryHandler discoveryHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(discoveryHandler).addPathPatterns("/{id}");
    }
}
