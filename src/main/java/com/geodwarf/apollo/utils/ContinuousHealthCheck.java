package com.geodwarf.apollo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;


@Component
public class ContinuousHealthCheck implements   Runnable   {

    //TODO put the URI in a configuration file and see if it is possible to inject Object in multithreading, also check how it can be testes
    private URI uri;
    private ResponseEntity<String> responseEntity;
    private Logger logger  = LoggerFactory.getLogger(ContinuousHealthCheck.class);;
    private RestTemplate restTemplate =  new RestTemplate();
    //private String URI_PATH = "http://localhost:8081/actuator/health";
    //private String URI_PATH = System.getenv("BACKEND_URL");

    public ContinuousHealthCheck(@Autowired @Qualifier("healthcheckUri")URI uri)  {
        System.out.println(uri);
        this.uri = uri;
//        try{
//            uri = new URI(uri + "/actuator/health"); }
//        catch(URISyntaxException e){
//            logger.info("Unknown  URL or URL malformed");
//        }
    }

    @Override
    public void run() {
        while(true)
            try
            {
                Thread.sleep(80000);
                try{
                    logger.info("calling the back end at: "+  uri);
                    responseEntity =  restTemplate.getForEntity(uri,String.class);
                    if (responseEntity != null){
                        logResponseDetails();
                    }
                }catch(ResourceAccessException e){
                    logger.error("Back end unavailable!!");
                }
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    }

    private void logResponseDetails(){
        if(responseEntity.getStatusCode()== HttpStatus.OK){
            logger.info("Backend status : "+ responseEntity.getBody() + " http code is: " + responseEntity.getStatusCode());
        }else{
            logger.error("Backend returned a non OK status : "+ responseEntity.getBody() + " http code is: " + responseEntity.getStatusCode());
        }
    }

}

