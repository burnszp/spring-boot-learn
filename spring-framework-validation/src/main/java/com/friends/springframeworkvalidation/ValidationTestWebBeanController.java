package com.friends.springframeworkvalidation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@Validated
public class ValidationTestWebBeanController {

    @PostMapping("/testValidatedBean")
    @NotNull
    public @Valid ValidationTestBean testValidatedBean(@Valid ValidationTestBean validationTestBean ){
        validationTestBean.setFirstName("xxxxxxx");
        return  validationTestBean;
    }
}
