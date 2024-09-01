package com.capstone.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class DepartmentDto {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotEmpty(message = "Department name cannot be empty.")
	    private String name;

	    @NotEmpty(message = "Department description cannot be empty.")
	    @Size(max = 200, message = "Description cannot be more than 200 characters.")
	    private String description;
	    
	    @NotEmpty(message= "Department shouldn't be empty.")
	    private String departmentCode;

}
