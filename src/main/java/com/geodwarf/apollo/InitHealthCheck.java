package com.geodwarf.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class InitHealthCheck {
    Logger logger = LoggerFactory.getLogger(InitHealthCheck.class);

    public  void  healthCheck() throws ResourceAccessException {
        try{
            final String url = "http://localhost:8081/areyoualive";
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            logger.info("is the backend alive: "+response);
        }catch(ResourceAccessException e){
            logger.info("Back end unavailable!!");
    }


    }
}
