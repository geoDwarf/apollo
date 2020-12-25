package com.geodwarf.apollo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@RestController
public class FeaturesRestController {

    private RestTemplate restTemplate;
    private ResponseEntity<String> responseEntity;
    private URI getPointsUri;
    private Logger logger  = LoggerFactory.getLogger(FeaturesRestController.class);

    @Autowired
    public FeaturesRestController(RestTemplate restTemplate, URI getPointsUri) {
        this.restTemplate = restTemplate;
        this.getPointsUri = getPointsUri;
    }

    @GetMapping("/getpoints")
    public String getPoints(){
        logger.info("received getpoints request");
        responseEntity = restTemplate.getForEntity(getPointsUri, String.class);
        return responseEntity.getBody();
    }
}