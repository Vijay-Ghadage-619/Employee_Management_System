package com.capstone.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.capstone.dto.*;
import com.capstone.service.Employee_Services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee", description = "APIs for managing employees")
public class Employee_Controller {

    @Autowired
    private Employee_Services service;

    @Autowired
    private ModelMapper map;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new employee")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto createdEmployeeDto = service.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all employees")
    @ApiResponse(responseCode = "200", description = "List of all employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeesDto = service.getAllEmployees();
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @Operation(summary = "Get employee by ID")
    @ApiResponse(responseCode = "200", description = "Employee found")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employeeDto = service.getEmployeeById(id);
        return employeeDto != null ? ResponseEntity.ok(employeeDto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update employee by ID")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        try {
            EmployeeDto updatedEmployee = service.updateEmployee(id, employeeDto);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            // Handle the case where the employee is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete employee by ID")
    @ApiResponse(responseCode = "200", description = "Employee deleted successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        String response = service.deleteEmployee(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/department/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @Operation(summary = "Get employee by department code")
    @ApiResponse(responseCode = "200", description = "Employee found by department code")
    public ResponseEntity<ApiResponseDto> getEmployeeByDepartmentCode(@PathVariable("id") Long id) {
        ApiResponseDto apiResponseDto = service.getEmployeeByIdAndCode(id);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @Operation(summary = "Get employee and associated task")
    @ApiResponse(responseCode = "200", description = "Employee and task information retrieved")
    public ResponseEntity<TaskApiResponseDto> getEmployeeAndTask(@PathVariable("id") Long id) {
        TaskApiResponseDto taskApiResponseDto = service.getTaskAndEmployee(id);
        return new ResponseEntity<>(taskApiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/performance/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @Operation(summary = "Get employee performance")
    @ApiResponse(responseCode = "200", description = "Employee performance information retrieved")
    public ResponseEntity<PerformanceApiResponseDto> getEmployeeAndPerformance(@PathVariable("id") Long id) {
        PerformanceApiResponseDto performanceApi = service.getEmployeePerformance(id);
        return new ResponseEntity<>(performanceApi, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all services for employee")
    @ApiResponse(responseCode = "200", description = "All services information retrieved")
    public ResponseEntity<AllApiResponnseDto> getAllServices(@PathVariable("id") Long id) {
        AllApiResponnseDto allApiDto = service.getAllServices(id);
        return new ResponseEntity<>(allApiDto, HttpStatus.OK);
    }
}
