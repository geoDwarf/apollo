package com.geodwarf.apollo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RestConfiguration {

    private String URI_PATH = System.getenv("BACKEND_URL");

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public URI uri(@Value("${url.basic}")String basicUrl, @Value("${url.health.actuator}") String actuatorUrl )throws URISyntaxException {
        return new URI(basicUrl+actuatorUrl);
    }

    @Bean
    public URI getPointsUri(@Value("${url.get.points}") String getPoints)throws URISyntaxException {
        return new URI(URI_PATH+getPoints);
    }
}
