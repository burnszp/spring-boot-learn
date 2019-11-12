package com.friends.springbootaop.pointcut;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AspectController {

    @GetMapping
    public String aspectTest(){
        return "aspect";
    }


    @GetMapping("annotation")
    @Validated
    public String aspectAnnotationTest(){
        return "hi annotation";
    }
}
