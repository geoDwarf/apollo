package com.geodwarf.apollo.utils;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface HealthCheck {

    /**
     * This is is a one time health check
     */
    void check();

    @Component
    class HealthCheckImpl implements HealthCheck{

        @Autowired
        private LoggerProxy loggerProxy;
        @Autowired
        private InitHealthCheck initHealthCheck;

        private ResponseEntity<String> responseEntity;

        private Logger logger;

         @Override
         public void  check(){
             logger = loggerProxy.getLogger(HealthCheckImpl.class);
             initHealthCheck.healthCheck();
             responseEntity = initHealthCheck.getResponse();
             if (responseEntity != null){
                logResponseDetails();
             }
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
