package com.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TaskApiResponseDto {

	private EmployeeDto employee;
	private TaskDto task;
	
}
