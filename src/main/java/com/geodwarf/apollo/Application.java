package com.geodwarf.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        InitHealthCheck intialHealthCheck = new InitHealthCheck();
        Logger logger = LoggerFactory.getLogger(Application.class);
        SpringApplication.run(Application.class, args);
        logger.info("Happy days");
        intialHealthCheck.healthCheck();
    }
}
