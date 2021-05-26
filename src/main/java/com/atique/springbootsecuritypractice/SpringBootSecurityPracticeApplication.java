package com.atique.springbootsecuritypractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootSecurityPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityPracticeApplication.class, args);
    }

}
