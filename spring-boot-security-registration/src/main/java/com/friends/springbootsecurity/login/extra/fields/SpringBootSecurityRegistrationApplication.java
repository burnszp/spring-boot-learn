package com.friends.springbootsecurity.login.extra.fields;

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
