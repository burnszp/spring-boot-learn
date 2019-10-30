package com.friends.springbootconfiguration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class TestValueAnnotation {
    @Value("${user-defined.attribute}")
    private String box;
}
