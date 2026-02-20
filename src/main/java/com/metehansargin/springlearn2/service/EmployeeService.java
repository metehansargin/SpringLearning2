package com.metehansargin.springlearn2.service;

import com.metehansargin.springlearn2.dto.dtoDepartment;
import com.metehansargin.springlearn2.dto.dtoEmployee;
import com.metehansargin.springlearn2.exception.BaseException;
import com.metehansargin.springlearn2.exception.ErrorMesage;
import com.metehansargin.springlearn2.exception.MessageType;
import com.metehansargin.springlearn2.mapper.BaseMapper;
import com.metehansargin.springlearn2.model.Department;
import com.metehansargin.springlearn2.model.Employee;
import com.metehansargin.springlearn2.repository.DepartmentRepository;
import com.metehansargin.springlearn2.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final BaseMapper baseMapper;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, BaseMapper baseMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.baseMapper = baseMapper;
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
    public dtoEmployee save(dtoEmployee dto){
        Employee employee=baseMapper.toEntity(dto, Employee.class);
        if (dto.getDepartment()!=null&&dto.getDepartment().getId()!=null){
            Department department=departmentRepository.findById(dto.getDepartment().getId())
                    .orElseThrow(()->new BaseException(
                            new ErrorMesage(MessageType.NO_RECORD_EXIST,dto.getDepartment().getId().toString())));
            employee.setDepartment(department);
        }
        Employee saved=employeeRepository.save(employee);

        return baseMapper.toDto(saved, dtoEmployee.class);

    }
    public dtoEmployee update(Long id,dtoEmployee dto){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()->new BaseException(
                        new ErrorMesage(MessageType.NO_RECORD_EXIST,dto.getDepartment().getId().toString())));

        employee.setName(dto.getName());
        baseMapper.update(dto,employee);

        Employee saved=employeeRepository.save(employee);

        return baseMapper.toDto(saved, dtoEmployee.class);

    }
    public void delete(Long id){

        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()->new BaseException(
                        new ErrorMesage(MessageType.NO_RECORD_EXIST,id.toString())));

        employeeRepository.delete(employee);
    }



}
