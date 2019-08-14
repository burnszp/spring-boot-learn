package com.friends.springbootsecuritybase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringBootSecurityBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityBaseApplication.class, args);
    }

}
