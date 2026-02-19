package com.metehansargin.springlearn2.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "name","department"})
public class dtoEmployee {
    private Long id;

    private String name;

    private dtoDepartment department;
}
