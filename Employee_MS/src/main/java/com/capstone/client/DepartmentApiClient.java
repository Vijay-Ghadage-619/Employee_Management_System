package com.capstone.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.dto.DepartmentDto;

@FeignClient(name="DEPARTMENT-MS")
public interface DepartmentApiClient {

	@GetMapping("api/departments/code/{departmentCode}")
	public DepartmentDto getDepartmentByCode(@PathVariable("departmentCode") String deptDto);
	
}