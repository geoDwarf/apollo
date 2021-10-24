package com.geodwarf.apollo;

import ch.qos.logback.classic.Logger;
import com.geodwarf.apollo.utils.HealthCheck;
import com.geodwarf.apollo.utils.LoggerProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HealthCheckTest {

    @InjectMocks
    private HealthCheck.HealthCheckImpl healthCheckImpl;

    @Mock
    private Logger logger;
    @Mock
    private LoggerProxy loggerProxy;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<String> responseEntity;
    @Mock
    private HttpStatus responseCode;
    @Mock
    private URI uri;

    @Test
    public void testWhenResponseIs200ApplicationKeepRunning(){

        //given  back end is available and it return 200
        givenUrlSetUp();
        when(restTemplate.getForEntity(uri,String.class)).thenReturn(responseEntity);
        when(responseEntity.getStatusCode()).thenReturn(responseCode.OK);
        when(loggerProxy.getLogger(HealthCheck.HealthCheckImpl.class)).thenReturn(logger);

       //when we call the method to check
        healthCheckImpl.check();

        //it log the result and carries on
        verify(logger,times(2)).info(anyString());
        verify(restTemplate,times(1)).getForEntity(uri,String.class);
        verify(responseEntity,times(1)).getBody();
        verify(responseEntity,times(2)).getStatusCode();

    }

    @Test
    public void testWhenResponseIsNot200ApplicationLogsAnError(){

        //given  back end is available but  it does not return 200
        givenUrlSetUp();
        when(restTemplate.getForEntity(uri,String.class)).thenReturn(responseEntity);
        when(responseEntity.getStatusCode()).thenReturn(responseCode.INTERNAL_SERVER_ERROR);
        when(loggerProxy.getLogger(HealthCheck.HealthCheckImpl.class)).thenReturn(logger);

        //when we call the method to check
        healthCheckImpl.check();

        //it log the error and carries on
        verify(logger,times(1)).error(anyString());
        verify(restTemplate,times(1)).getForEntity(uri,String.class);
        verify(responseEntity,times(1)).getBody();
        verify(responseEntity,times(2)).getStatusCode();
    }

    private void givenUrlSetUp(){
        when(uri.getHost()).thenReturn("localhost");
        when(uri.getPath()).thenReturn("actuator/health");
    }
}
