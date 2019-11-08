package com.friends.springframeworkvalidation.validationexplain;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class HandledCheckErrorsWebController {

    @PostMapping("/testValidated")
    public @Length(min = 1,max = 10)String testValidated( @Length(min = 1,max = 3)@RequestParam String firstName ){
        String LastName="Joey";
        return  firstName+"."+LastName;
    }
}
