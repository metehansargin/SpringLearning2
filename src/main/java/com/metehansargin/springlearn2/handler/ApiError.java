package com.metehansargin.springlearn2.handler;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"status", "exception"})
public class ApiError<E> {
    private Integer status;
    private Exception<E> exception;
}
