package com.spring.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class UniversityManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityManagementSystemApplication.class, args);
	}

}
