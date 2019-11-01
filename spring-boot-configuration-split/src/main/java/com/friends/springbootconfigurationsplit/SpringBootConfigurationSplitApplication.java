package com.friends.springbootconfigurationsplit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConfigurationSplitApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigurationSplitApplication.class, args);
    }

    @Value("${user-defined.common-configuration}")
    private String userDefinedCommConf;
    @Value("${user-defined.dev-configuration}")
    private String devConf;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(userDefinedCommConf);
        System.out.println(devConf);
    }
}
