package com.friends.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebMvcControllerByAutoConfigureMockMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testingWebMvc() throws Exception {
        mockMvc.perform(get("/sayHey").param("userId","1").accept(MediaType.TEXT_PLAIN)).andExpect(content().string("1    I'm Joys")).andExpect(status().isOk()).andDo(print());
    }
}