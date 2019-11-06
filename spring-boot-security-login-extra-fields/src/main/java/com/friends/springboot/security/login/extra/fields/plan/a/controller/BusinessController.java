package com.friends.springboot.security.login.extra.fields.plan.a.controller;

import com.friends.springboot.security.login.extra.fields.plan.a.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


@RestController
@RequestMapping("/api")
public class BusinessController {
    @Autowired
    HttpSession httpSession;

    @GetMapping("/authentication/verificationCode")
    public String getUser(){
        String verificationCode = randomAlphabetic(6);
        httpSession.setAttribute("verificationCode",verificationCode);
        String id = httpSession.getId();
        System.out.println(id);
        return verificationCode;
    }


    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public String deleteUser(@PathVariable Long id){
        return "delete user success by user id :"+id;
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('GET_USER')")
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        return user;
    }
}
