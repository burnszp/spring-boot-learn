package com.friends.springbootaop.pointcut.target;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TargetServiceController implements TargetInterface {
    @GetMapping("target")
    @Override
    public String aspectTarget() {
        return "hi target";
    }
}
