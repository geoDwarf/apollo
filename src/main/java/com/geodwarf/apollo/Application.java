package com.geodwarf.apollo;

import com.geodwarf.apollo.utils.ContinuousHealthCheck;
import com.geodwarf.apollo.utils.HealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  implements CommandLineRunner {
    //TODO write java doc
    //TODO isolate js in its own file
    //TODO import leaflet library

    @Autowired
    private HealthCheck healthCheck;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String...args) {

        healthCheck.check();

    }
}
