package com.friends.springframeworkvalidation.validationuse;

import lombok.Data;
import org.apache.tomcat.util.buf.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;


@RestController
@Validated
public class ValidationTestWebBeanController {

    /**
     * 基础类型以及其包装类约束
     * @param hair
     * @return
     */
    @PostMapping("/testValidatedAnnotation")
    public @Length(min = 1,max =10 ) String testValidatedAnnotation(  @RequestParam @Range(min = 20,max = 1000000) Long hair){
        return  "joey:"+hair;
    }


    /**
     * 集合约束
     * @param words
     * @return
     */
    @PostMapping("/testValidatedCollection")
    public  String testValidatedCollection(@RequestBody @Size(min = 2) @NotEmpty List< @Length(max = 4) String>  words){
        return StringUtils.join(words);
    }

    /**
     * 自定义包装类约束
     * @param validationTestBean
     * @return
     */
    @PostMapping("/testValidatedBeanProperties")
    public  ValidationTestBean testValidatedBeanProperties(@Valid ValidationTestBean validationTestBean ){
        return  validationTestBean;
    }


    /**
     * 集合中包含包装类
     * @param validationTestBeans
     * @return
     */
    @PostMapping("/testValidatedBeanPropertiesCollection")
    public  Integer testValidatedBeanPropertiesCollection(@RequestBody @NotEmpty List<@Valid ValidationTestBean> validationTestBeans ){
        return  validationTestBeans.size();
    }




    /**
     * 对象的级联验证
     * @param validationTestBeanCascaded
     * @return
     */
    @PostMapping("/testValidationTestBeanCascaded")
    public  ValidationTestBeanCascaded testValidationTestBeanCascaded(@RequestBody  @Valid ValidationTestBeanCascaded validationTestBeanCascaded ){
        return  validationTestBeanCascaded;
    }
    @Data
    static class ValidationTestBean {
        @Length(min = 1,max = 8)
        @NotNull
        private String firstName;

        @NotBlank(message = "不能为空或者\"\"")
        private String lastName;
    }
    @Data
    static class ValidationTestBeanCascaded{
        @AssertTrue
        private Boolean  isMan;
        @Valid
        private ValidationTestBean validationTestBean;

    }



    @PostMapping("/testValidationTestBeanGroup")
    public  ValidationTestBeanGroup testValidationTestBeanGroup(@RequestBody  @Validated(value = ValidationTestBeanGroup.Insert.class) ValidationTestBeanGroup validationTestBeanGroup ){
        return  validationTestBeanGroup;
    }

    @PostMapping("/testValidationTestBeanGroups")
    public  ValidationTestBeanGroup testValidationTestBeanGroups(@RequestBody  @Validated(value = {ValidationTestBeanGroup.Insert.class, ValidationTestBeanGroup.Default.class}) ValidationTestBeanGroup validationTestBeanGroup ){
        return  validationTestBeanGroup;
    }

    @Data
    static class ValidationTestBeanGroup {

        public  interface  Insert{}
        public  interface  Default{}
        @NotNull(groups = Insert.class)
        private Integer id;

        @NotNull(groups = Default.class)
        private String firstName;

        @NotBlank(message = "不能为空或者\"\"" )
        private String lastName;
    }


}
