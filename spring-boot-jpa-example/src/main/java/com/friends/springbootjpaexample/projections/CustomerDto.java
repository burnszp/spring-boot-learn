package com.friends.springbootjpaexample.projections;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerDto {

    private final String firstname;
}
