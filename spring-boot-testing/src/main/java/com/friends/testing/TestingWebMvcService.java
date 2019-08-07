package com.friends.testing;

import org.springframework.stereotype.Service;

@Service
public class TestingWebMvcService {
    public String sayHey(Integer userId) {
        return userId + "    I'm Joys";
    }
}
