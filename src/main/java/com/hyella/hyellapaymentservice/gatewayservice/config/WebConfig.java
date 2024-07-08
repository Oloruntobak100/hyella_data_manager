package com.hyella.hyellapaymentservice.gatewayservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch( @SuppressWarnings( "null" ) PathMatchConfigurer configurer ){
        configurer.addPathPrefix( "/api", c -> true );
    }
}