package com.geodwarf.apollo;

import com.geodwarf.apollo.utils.InitHealthCheck;
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
public class InitHealthCheckTest {
    //TODO add Exception test for not 200 http status code
    @Mock
    private URI uri;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<String> responseEntity;

    @InjectMocks
    private InitHealthCheck initHealthCheck;

    @Test
    public void initHealthCheckIsCalledWithExpectedParameters(){

        //Given
        when(uri.getHost()).thenReturn("localhost");
        when(uri.getPath()).thenReturn("actuator/health");
        when(restTemplate.getForEntity(uri,String.class)).thenReturn(responseEntity);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(responseEntity.getBody()).thenReturn("{status:UP}");

        //When
        initHealthCheck.healthCheck();

        //Then
        verify(restTemplate,times(1)).getForEntity(uri,String.class);
        verify(responseEntity, times(1)).getBody();
        verify(responseEntity, times(1)).getStatusCode();

    }
}
