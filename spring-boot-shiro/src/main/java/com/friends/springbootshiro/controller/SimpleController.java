package com.friends.springbootshiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    private static Logger log = LoggerFactory.getLogger(SimpleController.class);
    @RequiresPermissions("write")
    @GetMapping("/writeRestrictedCall")
    public void writeRestrictedCall() {
        log.info("executing method that requires the 'write' permission");
    }

    @RequiresPermissions("read")
    @GetMapping("/readRestrictedCall")
    public void readRestrictedCall() {
        log.info("executing method that requires the 'read' permission");
    }
}
