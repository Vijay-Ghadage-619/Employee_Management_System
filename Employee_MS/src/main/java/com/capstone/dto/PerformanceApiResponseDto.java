package com.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PerformanceApiResponseDto {

	private EmployeeDto Employee;
	private PerformanceDto performance;
	
}
