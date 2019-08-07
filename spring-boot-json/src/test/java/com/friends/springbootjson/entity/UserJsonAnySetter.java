package com.friends.springbootjson.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class UserJsonAnySetter {
    public String name;
    private Map<String, String> properties;

    public UserJsonAnySetter() {
        properties = new HashMap<String, String>();
    }

    public UserJsonAnySetter(final String name) {
        this.name = name;
        properties = new HashMap<String, String>();
    }

    @JsonAnySetter
    public void add(final String key, final String value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }
}
