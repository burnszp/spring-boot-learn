package com.mybaits.springboot.springbootmybatisbase.mapper;

import lombok.Data;

import java.io.Serializable;
@Data
public class MybatisSay implements Serializable {
    private Integer id;

    private String say;

    private static final long serialVersionUID = 1L;

}