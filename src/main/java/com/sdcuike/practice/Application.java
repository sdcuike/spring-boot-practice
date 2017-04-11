package com.sdcuike.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author sdcuike
 *         <p>
 *         Created on 2016.12
 *         <p>
 *         We generally recommend that you locate your main application class in a root package above other classes. <br>
 *         <p>
 *         We recommend that you follow Java’s recommended package naming conventions and use a reversed domain name (for example, com.example.project)
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
    }
}
