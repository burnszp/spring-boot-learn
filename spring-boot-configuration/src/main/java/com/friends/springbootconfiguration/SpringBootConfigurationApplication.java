package com.friends.springbootconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConfigurationApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigurationApplication.class, args);
//        SpringApplication app = new SpringApplication(SpringBootConfigurationApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);
    }

    @Autowired
    private TestValueAnnotation testValueAnnotation;
    @Autowired
    private TestConfigurationPropertiesAnnotation testConfigurationPropertiesAnnotation;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(testValueAnnotation.getBox());
        System.out.println("===================================");
        System.out.println(testConfigurationPropertiesAnnotation.getConfigInt());
        System.out.println(testConfigurationPropertiesAnnotation.getConfigStr());
        System.out.println(testConfigurationPropertiesAnnotation.getRemoteAddress().toString());
        System.out.println("MyPoJo:"+testConfigurationPropertiesAnnotation.getMyPojo().getFirstName());
        System.out.println("MyPoJo:"+testConfigurationPropertiesAnnotation.getMyPojo().getLastName());
        System.out.println(testConfigurationPropertiesAnnotation.isEnabled());
        System.out.println(testConfigurationPropertiesAnnotation.getSecurity().getUsername());
        System.out.println(testConfigurationPropertiesAnnotation.getSecurity().getPassword());
        System.out.println(testConfigurationPropertiesAnnotation.getSecurity().getRoles().toString());
        System.out.println(testConfigurationPropertiesAnnotation.getArrayProps().toString());
        System.out.println(testConfigurationPropertiesAnnotation.getList().size());


    }



}
