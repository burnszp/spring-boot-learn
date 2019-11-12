package com.mybaits.springboot.springbootmybatisbase.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.mybaits.springboot.springbootmybatisbase.mapper")
public class MybatisMapperScanConfig {}
