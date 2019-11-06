package com.friends.springboot.security.login.extra.fields.plan.a.controller;

import com.friends.springboot.security.login.extra.fields.plan.a.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class BusinessController {

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public String deleteUser(@PathVariable Long id){
        return "delete user success by user id :"+id;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_USER')")
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        return user;
    }
}
