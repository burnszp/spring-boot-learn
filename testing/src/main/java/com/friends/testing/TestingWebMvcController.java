package com.friends.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingWebMvcController {
    @Autowired
    private TestingWebMvcService testingWebMvcService;

    @GetMapping("/sayHey")
    public String sayHey(@RequestParam Integer userId){
        return testingWebMvcService.sayHey(userId);
    }

}
