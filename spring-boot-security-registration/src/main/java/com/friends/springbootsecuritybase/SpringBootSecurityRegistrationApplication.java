package com.friends.springbootsecuritybase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringBootSecurityRegistrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityRegistrationApplication.class, args);
    }

}
