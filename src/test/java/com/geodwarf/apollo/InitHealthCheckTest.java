package com.geodwarf.apollo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ComponentScan("com.geodwarf")
public class InitHealthCheckTest {

    @Autowired
    private URI uri;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<String> responseEntity;

    @InjectMocks
    private  InitHealthCheck initHealthCheck;

    @Test
    public void initHealthCheckIsCalledWithExpectedParameters(){

        //Given
        when(restTemplate.getForEntity(uri,String.class)).thenReturn(responseEntity);

        //When
        initHealthCheck.healthCheck();

        //Then
        verify(responseEntity, times(1)).getBody();
        verify(responseEntity, times(1)).getStatusCode();

    }
}
