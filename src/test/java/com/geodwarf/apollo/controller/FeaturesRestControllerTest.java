package com.geodwarf.apollo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FeaturesRestControllerTest {

    @InjectMocks
    FeaturesRestController featuresRestController;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity responseEntity;
    @Mock
    private URI getPointsUri;

    @Test
    public void whenEverythingIsCorrectReturnBodyOfResponse() throws Exception{
        //given
        when(restTemplate.getForEntity(getPointsUri, String.class)).thenReturn(responseEntity);
        //when
        featuresRestController.getPoints();
        //then
        verify(responseEntity).getBody();
    }
}

