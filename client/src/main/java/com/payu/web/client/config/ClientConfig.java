package com.payu.web.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Configuration
public class ClientConfig {

    @Bean
    public Client client() {
        return ClientBuilder.newClient(new org.glassfish.jersey.client.ClientConfig());
    }
}
