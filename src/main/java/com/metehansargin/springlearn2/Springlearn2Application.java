package com.metehansargin.springlearn2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = {"com.metehansargin"})
@EnableJpaRepositories(basePackages = {"com.metehansargin"})
@ComponentScan(basePackages = {"com.metehansargin"})
@EnableScheduling
@SpringBootApplication
public class Springlearn2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springlearn2Application.class, args);
	}

}
