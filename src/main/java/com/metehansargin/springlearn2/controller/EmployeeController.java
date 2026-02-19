package com.metehansargin.springlearn2.controller;

import com.metehansargin.springlearn2.dto.dtoEmployee;
import com.metehansargin.springlearn2.model.RootEntity;
import com.metehansargin.springlearn2.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController extends RootBaseController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/get/{id}")
    public RootEntity<dtoEmployee> getById(@PathVariable(value = "id") Long id){
        return ok(employeeService.getById(id));
    }
}
