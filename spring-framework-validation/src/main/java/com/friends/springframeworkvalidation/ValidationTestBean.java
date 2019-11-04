package com.friends.springframeworkvalidation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ValidationTestBean {
    @Length(min = 1,max = 3)
    private String firstName;

    @Length(min = 1,max = 3)
    private String lastName;
}
