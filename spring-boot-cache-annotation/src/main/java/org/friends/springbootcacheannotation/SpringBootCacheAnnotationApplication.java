package org.friends.springbootcacheannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class SpringBootCacheAnnotationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheAnnotationApplication.class, args);
    }
}
