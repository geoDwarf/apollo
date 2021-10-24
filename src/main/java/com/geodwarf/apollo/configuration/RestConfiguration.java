package com.geodwarf.apollo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "actuator-url")
    public URI actuatorUri(@Value("${url.basic}")String basicUrl, @Value("${url.health.actuator}") String actuatorUrl )throws URISyntaxException {
        return new URI(basicUrl+actuatorUrl);
    }

    @Bean(name = "get-points-url")
    public URI getPointsUri(@Value("${url.basic}")String basicUrl, @Value("${url.features.points}") String actuatorUrl )throws URISyntaxException {
        return new URI(basicUrl+actuatorUrl);
    }
}
