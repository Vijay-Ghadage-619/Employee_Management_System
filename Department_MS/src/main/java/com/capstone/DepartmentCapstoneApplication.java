package com.capstone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title="Department_Management_Microservice",
				description="This is the Department Microservice created for the Employee Management System",
				version="Final",
				contact=@Contact (
						name="Vijay Ghadage",
						email="vijayghadage2110@gmail.com"
						),
				license=@License(
						name="Apache 2.0",
						url="https://www.vijayg.com/license"
						)
				)
		)
public class DepartmentCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentCapstoneApplication.class, args);
	}
	
	@Bean
	public ModelMapper getMmodelMapper() {
		return new ModelMapper();
	}
	

}
