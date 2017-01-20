package com.sdcuike.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        //        SpringApplication.run(Application.class, new String[] { "--debug" });
        SpringApplication.run(Application.class, args);
    }
}
