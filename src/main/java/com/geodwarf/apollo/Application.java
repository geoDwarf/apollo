package com.geodwarf.apollo;

import com.geodwarf.apollo.utils.InitHealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  implements CommandLineRunner {
    //TODO close feature and merge
    @Autowired
    private InitHealthCheck initHealthCheck;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String...args) {
        initHealthCheck.healthCheck();
    }
}
