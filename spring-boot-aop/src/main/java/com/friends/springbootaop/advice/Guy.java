package com.friends.springbootaop.advice;

import lombok.Data;

@Data
public class Guy {
    private String saySomething;
    private  String  firstName;
    private  String  lastName;
    private  Integer  age;

    @Override
    public String toString() {
        return "Guy{" +
                "saySomething='" + saySomething + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
