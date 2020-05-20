package com.geodwarf.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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

    //TODO use a configuration file
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
