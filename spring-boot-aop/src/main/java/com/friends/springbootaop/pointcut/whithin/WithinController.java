package com.friends.springbootaop.pointcut.whithin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithinController {
    @GetMapping("/within")
    public String aspectTest(){
        return "hi within";
    }
}
