package com.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllApiResponnseDto {

	private EmployeeDto employee;
	private DepartmentDto department;
	private TaskDto task;
	private PerformanceDto performance;
	
}