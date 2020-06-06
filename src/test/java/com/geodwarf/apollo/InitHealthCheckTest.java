package com.geodwarf.apollo;

import com.geodwarf.apollo.utils.InitHealthCheck;
import com.geodwarf.apollo.utils.LoggerProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InitHealthCheckTest {

    @Mock
    private URI uri;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<String> responseEntity;
    @Mock
    private LoggerProxy loggerProxy;
    @Mock
    private Logger logger;

    @InjectMocks
    private InitHealthCheck initHealthCheck;

    @Test
    public void initHealthCheckIsCalledWithExpectedParameters(){

        //Given
        givenUrlSetUp();
        when(restTemplate.getForEntity(uri,String.class)).thenReturn(responseEntity);
        when(loggerProxy.getLogger(InitHealthCheck.class)).thenReturn(logger);

        //When
        initHealthCheck.healthCheck();

        //Then
        verify(restTemplate,times(1)).getForEntity(uri,String.class);
        verify(logger,times(1)).info(anyString());


    }

    @Test
    public void whenResourcesAreUnavailableExceptionIsCaughtAndErrorLogged(){
        //given
        givenUrlSetUp();
        given(restTemplate.getForEntity(uri,String.class)).willThrow(new ResourceAccessException(""));
        when(loggerProxy.getLogger(InitHealthCheck.class)).thenReturn(logger);

        //when
        initHealthCheck.healthCheck();

        //then
        verify(logger,times(1)).error("Back end unavailable!!");
    }
    private void givenUrlSetUp(){
        when(uri.getHost()).thenReturn("localhost");
        when(uri.getPath()).thenReturn("actuator/health");
    }
}
