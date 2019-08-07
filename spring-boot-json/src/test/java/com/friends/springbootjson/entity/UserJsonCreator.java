package com.friends.springbootjson.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserJsonCreator {
    public int id;
    public String name;

    @JsonCreator
    public UserJsonCreator(@JsonProperty("id") final int id, @JsonProperty("username") final String name) {
        this.id = id;
        this.name = name;
    }
}
