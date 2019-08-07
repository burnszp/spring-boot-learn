package com.friends.springbootjson;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootJsonControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testJson() throws Exception {
        User user = new User();
        user.setAge(12);
        user.setBirthday(new Date());
        user.setUserAccount("Joey");
        user.setPassword("Joey's password");
        String s = objectMapper.writeValueAsString(user);
        this.mvc.perform(post("/testJson").content(s.getBytes()).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(content().string(containsString("Joey's password"))).andDo(print());
    }
}