package com.geodwarf.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class InitHealthCheck {
    //TODO check if it can be injected or used in a different way
    Logger logger = LoggerFactory.getLogger(InitHealthCheck.class);
    //TODO Try auto wiring on constructor or setter
    @Autowired
    private RestTemplate restTemplate;

    private ResponseEntity<String> responseEntity;
    @Autowired
    private URI uri;

    //TODO Refactor this method
    public  void  healthCheck() throws ResourceAccessException {
        try{
            responseEntity =  restTemplate.getForEntity(uri,String.class);
            logger.info("is the backend alive calling the uri: "+responseEntity.getBody() + " http code is: " + responseEntity.getStatusCode());
        }catch(ResourceAccessException e){
            logger.info("Back end unavailable!!");
        }
    }
}
