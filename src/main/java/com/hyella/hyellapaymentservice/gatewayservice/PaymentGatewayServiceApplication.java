package com.hyella.hyellapaymentservice.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
// @ComponentScan(basePackages = { "com.hyella.hyellapaymentservice.gatewayservice" })
@EntityScan( basePackages = { "com.hyella.hyellapaymentservice.gatewayservice" } )  // force scan JPA entities

public class PaymentGatewayServiceApplication {
    public static void main( String[] args ){
        SpringApplication.run( PaymentGatewayServiceApplication.class, args );
    }

}
