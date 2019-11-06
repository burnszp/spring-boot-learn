package com.friends.springbootjpa.web.controller;

import com.friends.springbootjpa.persistence.model.UserEntity;
import com.friends.springbootjpa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/viewByPageableAndLastname")
    public Page<UserEntity> viewByPageableAndLastname(String lastname,Pageable pageable) {
        return   userService.viewByPageableAndLastname(lastname,pageable);
    }

    @GetMapping("/viewByPageableAndUserEntity")
    public Page<UserEntity> viewByPageableAndUserEntity(UserEntity userEntity,Pageable pageable) {
        return   userService.viewByPageableAndUserEntity(userEntity,pageable);
    }
    //https://docs.spring.io/spring-data/jpa/docs/2.1.6.RELEASE/reference/html/#core.web.basic
    @GetMapping("/viewByPageable")
    public Page<UserEntity> viewByPageable(Pageable pageable) {
      return   userService.viewByPageable(pageable);
    }

    @GetMapping("/view")
    public Page<UserEntity> getUsers(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        return   userService.getUsers(pageNum,pageSize);
    }

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable Integer id){
        return   userService.getUser(id);
    }

    @PostMapping
    public UserEntity addUser(@ModelAttribute UserEntity user){
        return   userService.addUser(user);
    }

    //https://stackoverflow.com/questions/52162452/how-to-use-hibernate-dynamicupdate-with-spring-data-jpa
    @PostMapping("/dynamicUpdate")
    public UserEntity dynamicUpdateUser(@ModelAttribute UserEntity user){
        return   userService.dynamicUpdate(user);
    }

    @PutMapping
    public UserEntity updateUser(@ModelAttribute UserEntity user){
        return   userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public Integer deleteUser(@PathVariable Integer id){
        return   userService.deleteUser(id);
    }


}
