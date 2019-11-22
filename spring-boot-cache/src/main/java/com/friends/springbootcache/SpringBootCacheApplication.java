package com.friends.springbootcache;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@EnableCaching
@RestController
public class SpringBootCacheApplication  extends CacheInfo{
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        //获取实例化应用时加载的所有类
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }




     // org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration
//    org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
    // org.springframework.cache.annotation.ProxyCachingConfiguration;
   // org.springframework.cache.config.internalCacheAdvisor
   //spring.cache-org.springframework.boot.autoconfigure.cache.CacheProperties






}
