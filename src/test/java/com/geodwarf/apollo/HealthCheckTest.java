package com.geodwarf.apollo;

import com.geodwarf.apollo.utils.HealthCheck;
import com.geodwarf.apollo.utils.InitHealthCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class HealthCheckTest {

    @InjectMocks
    private HealthCheck.HealthCheckImpl healthCheckImpl;

    @Mock
    private InitHealthCheck initHealthCheck;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<String> responseEntity;
    @Mock
    private HttpStatus responseCode;

    @Test
    public void testWhenResponseIs200ApplicationKeepRunning(){

        //given  back end is available and it return 200
       when(initHealthCheck.getResponse()).thenReturn(responseEntity);
       when(responseEntity.getStatusCode()).thenReturn(responseCode.OK);

       //when we call the method to check
        healthCheckImpl.check();

        //it log the result and carries on
        verify(responseEntity,times(1)).getBody();
        verify(responseEntity,times(2)).getStatusCode();

    }

    @Test
    public void testWhenResponseIsNot200ApplicationLogsAnError(){

        //given  back end is available but  it does not return 200
        when(initHealthCheck.getResponse()).thenReturn(responseEntity);
        when(responseEntity.getStatusCode()).thenReturn(responseCode.INTERNAL_SERVER_ERROR);

        //when we call the method to check
        healthCheckImpl.check();

        //it log the result and carries on
        verify(responseEntity,times(1)).getBody();
        verify(responseEntity,times(2)).getStatusCode();
    }
}
