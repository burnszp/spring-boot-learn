package com.friends.springframeworkvalidation.validationhandleerror;

import com.friends.springframeworkvalidation.validationuse.ValidationTestWebBeanController;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@Validated
public class HandledCheckErrorsWebController {

    @PostMapping("/1")
    public String testValidated(@Length(max = 3) String firstName ){
        String LastName="Joey";
        return  firstName+"."+LastName;
    }

    @PostMapping("/2")
    public String testValidated2( @Range(max = 3) String firstName ){
        String LastName="Joey";
        return  firstName+"."+LastName;
    }

    @PostMapping("/3")
    public String testValidated3( @NotNull String firstName ){
        String LastName="Joey";
        return  firstName+"."+LastName;
    }

    @PostMapping("/4")
    public String testValidated4( @NotBlank String firstName ){
        String LastName="Joey";
        return  firstName+"."+LastName;
    }

    @PostMapping("/5")
    public  Integer testValidatedBeanPropertiesCollection(@RequestBody @NotEmpty List<@Valid ValidationTestBean> validationTestBeans ){
        return  validationTestBeans.size();
    }

    @PostMapping("/6")
    public  ValidationTestBean testValidatedBeanPropertiesCollection(@Valid ValidationTestBean validationTestBeans ){
        return  validationTestBeans;
    }

    @Data
    static class ValidationTestBean {

        @NotBlank(message = "不能为空或者\"\"")
        private String lastName;

        @Length(min = 2)
        private String firstName;
    }
}
