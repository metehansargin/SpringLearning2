package com.metehansargin.springlearn2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dtoEmployee {
    private Long id;
    private String name;
    private dtoDepartment department;
}
