package com.friends.springframeworkvalidation.validationuse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValidationTestWebBeanControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testValidatedAnnotation() {
        try {
            mockMvc.perform(post("/testValidatedAnnotation").param("hair", "10").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), containsString("ConstraintViolationException"));
        }

    }


    @Test
    public void testValidatedCollection() {
        try {
            List<String> words = new ArrayList<>();
            words.add("I");
            words.add("am");
            words.add("Joseph Francis Tribbiani");
            mockMvc.perform(post("/testValidatedCollection").content(new ObjectMapper().writeValueAsBytes(words)).contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), containsString("ConstraintViolationException"));
        }
    }

    @Test
    public void testValidatedBeanProperties() throws Exception {
            mockMvc.perform(post("/testValidatedBeanProperties").param("firstName","Joey").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)).andExpect(status().is(400));
    }


    @Test
    public void testValidatedBeanPropertiesCollection() {
        try {
            List<ValidationTestWebBeanController.ValidationTestBean> validationTestBeans = new ArrayList<>();
            ValidationTestWebBeanController.ValidationTestBean validationTestBean = new ValidationTestWebBeanController.ValidationTestBean();
            validationTestBean.setFirstName("joey");
            validationTestBeans.add(validationTestBean);
            mockMvc.perform(post("/testValidatedBeanPropertiesCollection").content(new ObjectMapper().writeValueAsBytes(validationTestBeans)).contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), containsString("ConstraintViolationException"));
        }
    }


    @Test
    public void testValidationTestBeanCascaded() throws Exception {
            ValidationTestWebBeanController.ValidationTestBeanCascaded validationTestBeanCascaded = new ValidationTestWebBeanController.ValidationTestBeanCascaded();
            validationTestBeanCascaded.setIsMan(true);
            ValidationTestWebBeanController.ValidationTestBean validationTestBean = new ValidationTestWebBeanController.ValidationTestBean();
            validationTestBean.setFirstName("joey");
            validationTestBeanCascaded.setValidationTestBean(validationTestBean);
            mockMvc.perform(post("/testValidationTestBeanCascaded").content(new ObjectMapper().writeValueAsBytes(validationTestBeanCascaded)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
    }


    @Test
    public void testValidationTestBeanGroup() throws Exception {
        ValidationTestWebBeanController.ValidationTestBeanGroup validationTestBeanGroup = new ValidationTestWebBeanController.ValidationTestBeanGroup();
        validationTestBeanGroup.setId(100);
        validationTestBeanGroup.setFirstName("joey");
        mockMvc.perform(post("/testValidationTestBeanGroup").content(new ObjectMapper().writeValueAsBytes(validationTestBeanGroup)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}