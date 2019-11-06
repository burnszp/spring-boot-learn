package com.friends.springbootsecurity.login.extra.fields.plan.a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/admin")
public class AdminController {

    // API

    // read - single
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ResponseBody
    public String hi() {
        return "hey guys!";
    }
}
