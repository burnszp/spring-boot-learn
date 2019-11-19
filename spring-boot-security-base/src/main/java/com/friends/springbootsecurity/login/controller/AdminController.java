package com.friends.springbootsecurity.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/admin")
public class AdminController {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ResponseBody
    public String hi() {
        return "hey guys!";
    }
}
