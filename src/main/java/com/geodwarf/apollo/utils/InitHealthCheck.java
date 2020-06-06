package com.geodwarf.apollo.utils;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class InitHealthCheck {
    //TODO check if it can be injected or used in a different way
    private Logger logger;
    @Autowired
    LoggerProxy loggerProxy;
    //TODO Try auto wiring on constructor or setter
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private URI uri;
    @Autowired
    private HealthCheck healthCheck;

    private ResponseEntity<String> response;

    public  void  healthCheck()  {
        logger  = loggerProxy.getLogger(InitHealthCheck.class);
        try{
            logger.info("calling the back end at "+uri.getHost() +" " + uri.getPath());
            response =  restTemplate.getForEntity(uri,String.class);
        }catch(ResourceAccessException e){
            logger.error("Back end unavailable!!");
        }
    }

    public ResponseEntity<String> getResponse() {
        return response;
    }
}
