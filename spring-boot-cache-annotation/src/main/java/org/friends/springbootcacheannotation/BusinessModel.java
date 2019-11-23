package org.friends.springbootcacheannotation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class BusinessModel   implements java.io.Serializable {

    private String name;
    private Integer age;
    private Boolean  sex;
    private Timestamp date;
}
