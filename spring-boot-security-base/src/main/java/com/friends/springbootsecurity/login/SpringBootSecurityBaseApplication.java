package com.friends.springbootsecurity.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication

public class SpringBootSecurityBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityBaseApplication.class, args);
    }
}
