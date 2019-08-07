package com.friends.springbootjson.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class BeanWithGetter {
    public int id;
    private String name;

    public BeanWithGetter() {

    }

    public BeanWithGetter(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    @JsonSetter("name")
    public void setTheName(final String name) {
        this.name = name;
    }

    @JsonGetter("name")
    public String getTheName() {
        return name;
    }
}
