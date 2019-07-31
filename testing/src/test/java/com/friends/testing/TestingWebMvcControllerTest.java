package com.friends.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingWebMvcControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    public void testingWebMvc() throws Exception {
        mockMvc.perform(get("/sayHey").param("userId","1").accept(MediaType.TEXT_PLAIN)).andExpect(content().string("1    I'm Joys")).andExpect(status().isOk()).andDo(print());
    }



    @Autowired
    private TestingWebMvcController testingWebMvcController;
    @Autowired
    private TestingWebMvcService testingWebMvcService;

    @Test
    public void testingWebMvcByAutowired()  {
        Assert.assertEquals("1    I'm Joys",testingWebMvcController.sayHey(1));
        Assert.assertEquals("1    I'm Joys",testingWebMvcService.sayHey(1));
    }




}