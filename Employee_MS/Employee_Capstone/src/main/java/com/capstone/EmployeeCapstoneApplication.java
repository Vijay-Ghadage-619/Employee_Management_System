package com.capstone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
                                   
public class EmployeeCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeCapstoneApplication.class, args);
	}
	
	@Bean
	public ModelMapper getMmodelMapper() {
		return new ModelMapper();
	}
	
	
	
	
	
	
}
