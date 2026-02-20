package com.metehansargin.springlearn2.controller;

import com.metehansargin.springlearn2.dto.dtoEmployee;
import com.metehansargin.springlearn2.model.RootEntity;
import com.metehansargin.springlearn2.service.EmployeeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/save")
    public RootEntity<dtoEmployee> save(@Validated @RequestBody dtoEmployee dtoEmployee){
        return ok(employeeService.save(dtoEmployee));
    }
    @PutMapping("/update/{id}")
    public RootEntity<dtoEmployee> update(@PathVariable Long id,@Validated @RequestBody dtoEmployee dtoEmployee){
        return ok(employeeService.update(id,dtoEmployee));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        employeeService.delete(id);
    }
}
