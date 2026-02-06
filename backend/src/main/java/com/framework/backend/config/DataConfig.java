package com.framework.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.framework.backend.service")
@PropertySource("classpath:application.properties")
public class DataConfig {
    
    /**
     * Bean RestTemplate pour les appels HTTP vers le Back-Office
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}