package com.friends.springbootsecurity.login.extra.fields.plan.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringBootSecurityLoginExtraFieldsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityLoginExtraFieldsApplication.class, args);
    }

}
