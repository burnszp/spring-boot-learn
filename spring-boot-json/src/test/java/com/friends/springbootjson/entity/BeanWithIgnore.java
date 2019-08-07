package com.friends.springbootjson.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "id" })
public class BeanWithIgnore {
    public int id;
    public String name;
    @JsonIgnore
    public String password;

    public BeanWithIgnore() {

    }
    public BeanWithIgnore(final int id, final String name,String password) {
        this.id = id;
        this.name = name;
        this.password=password;
    }
}
