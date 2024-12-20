package de.frankherling.spielwiese.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class TheApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheApplication.class, args);
    }

}
