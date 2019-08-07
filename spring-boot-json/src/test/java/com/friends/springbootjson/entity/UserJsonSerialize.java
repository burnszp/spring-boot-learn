package com.friends.springbootjson.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.friends.springbootjson.SpringBootJsonTest;

import java.util.Date;

public class UserJsonSerialize {
    @JsonSerialize(using = CustomDateSerializer.class )
    private Date birthday;
    private Integer id;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserJsonSerialize() {
    }
    public UserJsonSerialize(Date birthday, int id) {
        this.birthday=birthday;
        this.id=id;
    }
}
