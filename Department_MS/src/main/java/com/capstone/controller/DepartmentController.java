package com.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.dto.DepartmentDto;
import com.capstone.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name="CRUD Rest Api For Department Table",
description="Api like -Creare Department,Get department,Update Department,Delete Department")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    

    @Operation(
    		summary="Create department Rest API",
    		description="This Rest API is used for creating a new Department..."
    		)
    @ApiResponse(
    		responseCode="201",
    		description="Http Status 201 Created..."
    		)
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }
    
    

    @Operation(
    		summary="Get department Rest API",
    		description="This Rest API is used for getting all the Departments available in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    
    
    @Operation(
    		summary="Get department Rest API",
    		description="This Rest API is used for getting  the Specific Departments available in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    
    
    @Operation(
    		summary="Update department Rest API",
    		description="This Rest API is used for Updating the Department details in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.updateDepartment(id, departmentDto), HttpStatus.OK);
    }

    
    
    @Operation(
    		summary="Delete department Rest API",
    		description="This Rest API is used for Deleting  the Specific Departments available in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.deleteDepartment(id), HttpStatus.OK);
    }
    
    

    @Operation(
    		summary="Get department Rest API",
    		description="This Rest API is used for getting  the Specific Departments available in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @GetMapping("code/{departmentCode}")
	public DepartmentDto getDepartmentByCode(@PathVariable("departmentCode") String deptDto)
	{
		return departmentService.getDepartmentByCode(deptDto);
	}
    
    
    
}
