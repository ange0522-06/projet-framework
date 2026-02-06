package com.framework.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.framework.backend.service")
public class ServiceConfig {
    // Configuration pour scanner les beans de service
}