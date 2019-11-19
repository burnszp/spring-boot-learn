package com.friends.springbootsecurity.login.controller;

import com.friends.springbootsecurity.login.extra.fields.dto.UserDto;
import com.friends.springbootsecurity.login.model.GenericResponse;
import com.friends.springbootsecurity.login.extra.fields.service.IUserService;
import com.friends.springbootsecurity.login.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    // Registration
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public GenericResponse registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", accountDto);
        final User registered = userService.registerNewUserAccount(accountDto);
        return new GenericResponse("success");
    }


}
