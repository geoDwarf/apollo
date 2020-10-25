package com.geodwarf.apollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class FeatureController {

    private ResponseEntity<String> responseEntity;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    @Qualifier("get-points-url")
    private URI uri;

    @RequestMapping("/getpoints")
    public String getPoints(){
        responseEntity =  restTemplate.getForEntity(uri,String.class);
        String points = responseEntity.getBody();
        return points;
    }

}
