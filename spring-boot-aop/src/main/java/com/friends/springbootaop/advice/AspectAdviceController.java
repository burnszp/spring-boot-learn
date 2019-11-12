package com.friends.springbootaop.advice;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AspectAdviceController {


    @GetMapping("AfterAndAfterAll")
    public String aspectBeforeExample(String say,String something,Integer day){
        return say+something+day;
    }


    @GetMapping("afterThrowing")
    public String afterThrowing(){
        throw new RuntimeException("runtime error");
    }

    @GetMapping("around")
    public String around(){
        return "hi around";
    }


    @GetMapping("parameterNormal")
    public Guy parameterNormal(Guy guy){
        return guy;
    }


    @GetMapping("instanceParameter")
    public Guy instanceParameter(Guy guy){
        return guy;
    }


    @GetMapping("annotationParameter")
    @Auditable("hi guys!! I'm annotationParameter")
    public String annotationParameter(){
        return "hi annotationParameter!!";
    }
}
