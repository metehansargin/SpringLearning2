package com.metehansargin.springlearn2;

import com.metehansargin.springlearn2.dto.dtoEmployee;
import com.metehansargin.springlearn2.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {Springlearn2Application.class})
class Springlearn2ApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@BeforeEach
	void beforeEach(){
		System.out.println("Burası testlerden once calisan kisim...");
	}

	@Test
	void testFindByEmployeeId(){
		dtoEmployee employee=employeeService.getById(4L);
		assertNotNull(employee);
	}

	@AfterEach
	void afterEach(){
		System.out.println("Burası testlerden sonra calisan kisim...");
	}

}
