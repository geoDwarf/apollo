package com.geodwarf.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class InitHealthCheck {
    //TODO check if it can be injected or used in a different way
    Logger logger = LoggerFactory.getLogger(InitHealthCheck.class);

    @Autowired
    private RestTemplate restTemplate;

    public  void  healthCheck() throws ResourceAccessException {
        try{
            //TODO put in a properties file
            final String url = "http://localhost:8081/areyoualive";
            final String actuatorUrl = "http://localhost:8081/actuator/health";
            String response = restTemplate.getForObject(url, String.class);
            String responseActuator = restTemplate.getForObject(actuatorUrl, String.class);
            logger.info("is the backend alive: "+response);
            logger.info("is the backend alive calling the actuator: "+responseActuator);
        }catch(ResourceAccessException e){
            logger.info("Back end unavailable!!");
        }
    }

}
