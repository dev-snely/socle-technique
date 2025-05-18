package com.log430.socle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocleApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SocleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SocleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("Hello World depuis la console !");
    }
}