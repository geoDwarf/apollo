package com.geodwarf.apollo.utils;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public interface HealthCheck {

    /**
     * This check the healthy of a service we it depends on
     */
    void check();

    @Component
    class HealthCheckImpl implements HealthCheck{

        @Autowired
        private LoggerProxy loggerProxy;
        //TODO Try auto wiring on constructor or setter+
        // TODO check if it can be injected or used in a different way
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private URI uri;

        private ResponseEntity<String> responseEntity;

        private Logger logger;

         @Override
         public void  check(){
             logger = loggerProxy.getLogger(HealthCheckImpl.class);
             try{
                 logger.info("calling the back end at "+uri.getHost() +" " + uri.getPath());
                 responseEntity =  restTemplate.getForEntity(uri,String.class);
             if (responseEntity != null){
                logResponseDetails();
             }
             }catch(ResourceAccessException e){
                 logger.error("Back end unavailable!!");
             }
             ContinuousHealthCheck continuousHealthCheck = new ContinuousHealthCheck();
             Thread th = new Thread(continuousHealthCheck);
             th.start();
         }

         private void logResponseDetails(){
            if(responseEntity.getStatusCode()==HttpStatus.OK){
                logger.info("Backend status : "+ responseEntity.getBody() + " http code is: " + responseEntity.getStatusCode());
            }else{
                 logger.error("Backend returned a non OK status : "+ responseEntity.getBody() + " http code is: " + responseEntity.getStatusCode());
         }

     }
 }

}
