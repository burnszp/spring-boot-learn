package com.mybaits.springboot.springbootmybatisbase;

import com.mybaits.springboot.springbootmybatisbase.mapper.MybatisSay;
import com.mybaits.springboot.springbootmybatisbase.mapper.MybatisSayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootMybatisBaseApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisBaseApplication.class, args);
    }
    @Autowired
    private MybatisSayMapper mybatisSayMapper;
     @Override
    public void run(String... args) {
         MybatisSay mybatisSay = mybatisSayMapper.selectByPrimaryKey(1);
         System.out.println(mybatisSay.getSay());
     }
}
