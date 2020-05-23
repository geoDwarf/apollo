package com.geodwarf.apollo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class Application  implements CommandLineRunner
{

    @Autowired
    private  InitHealthCheck initHealthCheck;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String...args) throws Exception {
        initHealthCheck.healthCheck();
    }
    //TODO use a properties file
    //TODO use a configuration file
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public URI uri() throws URISyntaxException {
        return new URI("http://localhost:8081/actuator/health");
    }

}
