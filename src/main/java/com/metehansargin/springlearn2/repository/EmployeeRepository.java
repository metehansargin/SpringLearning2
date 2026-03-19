package com.metehansargin.springlearn2.repository;

import com.metehansargin.springlearn2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> getEmployeesBy(Employee employee);
}
