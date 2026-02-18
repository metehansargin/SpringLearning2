package com.metehansargin.springlearn2.service;

import com.metehansargin.springlearn2.dto.dtoDepartment;
import com.metehansargin.springlearn2.dto.dtoEmployee;
import com.metehansargin.springlearn2.exception.BaseException;
import com.metehansargin.springlearn2.exception.ErrorMesage;
import com.metehansargin.springlearn2.exception.MessageType;
import com.metehansargin.springlearn2.model.Department;
import com.metehansargin.springlearn2.model.Employee;
import com.metehansargin.springlearn2.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public dtoEmployee getById(Long id){
        dtoEmployee dtoEmployee=new dtoEmployee();
        dtoDepartment dtoDepartment=new dtoDepartment();

        Optional<Employee> optionalEmployee=employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
            throw new BaseException(new ErrorMesage(MessageType.NO_RECORD_EXIST,id.toString()));
        }
        Employee employee=optionalEmployee.get();
        Department department=optionalEmployee.get().getDepartment();

        BeanUtils.copyProperties(employee,dtoEmployee);
        BeanUtils.copyProperties(department,dtoDepartment);

        dtoEmployee.setDepartment(dtoDepartment);//burada aslında kullanıcağımız olan department id yide maplemiş olduk

        return dtoEmployee;
    }

}
