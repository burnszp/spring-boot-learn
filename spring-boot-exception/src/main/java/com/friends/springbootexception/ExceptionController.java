package com.friends.springbootexception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping
    public void   testExceptionMap() throws MyException {
        throw  new MyException("抛出自定义错误");
    }
}
