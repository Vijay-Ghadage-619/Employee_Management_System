package com.capstone.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import com.capstone.dto.*;
import com.capstone.service.Employee_Services;

public class Employee_Controller_Test {

    @Mock
    private Employee_Services service;

    @InjectMocks
    private Employee_Controller controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test for creating a new employee")
    @WithMockUser(roles = "ADMIN")
    public void givenEmployeeDto_whenCreateEmployee_thenReturnCreatedEmployeeDto() {
        // Given
        EmployeeDto employeeDto = EmployeeDto.builder().name("Vijay Ghadage").build();
        when(service.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

        // When
        ResponseEntity<EmployeeDto> response = controller.createEmployee(employeeDto);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(employeeDto);
    }

    @Test
    @DisplayName("Test for getting all employees")
    @WithMockUser(roles = "ADMIN")
    public void whenGetAllEmployees_thenReturnEmployeeDtoList() {
        // Given
        List<EmployeeDto> employeeDtos = List.of(EmployeeDto.builder().name("Vijay Ghadage").build());
        when(service.getAllEmployees()).thenReturn(employeeDtos);

        // When
        ResponseEntity<List<EmployeeDto>> response = controller.getAllEmployees();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(employeeDtos);
    }

    @Test
    @DisplayName("Test for getting an employee by ID")
    @WithMockUser(roles = "EMPLOYEE")
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeDto() {
        // Given
        Long id = 1L;
        EmployeeDto employeeDto = EmployeeDto.builder().id(id).name("Vijay Ghadage").build();
        when(service.getEmployeeById(anyLong())).thenReturn(employeeDto);

        // When
        ResponseEntity<EmployeeDto> response = controller.getEmployeeById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(employeeDto);
    }

    @Test
    @DisplayName("Test for not found employee by ID")
    @WithMockUser(roles = "EMPLOYEE")
    public void givenNonExistentEmployeeId_whenGetEmployeeById_thenReturnNotFound() {
        // Given
        Long id = 1L;
        when(service.getEmployeeById(anyLong())).thenReturn(null);

        // When
        ResponseEntity<EmployeeDto> response = controller.getEmployeeById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Test for updating an employee by ID")
    @WithMockUser(roles = "ADMIN")
    public void givenEmployeeDto_whenUpdateEmployee_thenReturnUpdatedEmployeeDto() {
        // Given
        Long id = 1L;
        EmployeeDto employeeDto = EmployeeDto.builder().id(id).name("Vijay Ghadage Updated").build();
        when(service.updateEmployee(anyLong(), any(EmployeeDto.class))).thenReturn(employeeDto);

        // When
        ResponseEntity<EmployeeDto> response = controller.updateEmployee(id, employeeDto);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(employeeDto);
    }

    @Test
    @DisplayName("Test for not found when updating an employee by ID")
    @WithMockUser(roles = "ADMIN")
    public void givenNonExistentEmployee_whenUpdateEmployee_thenReturnNotFound() {
        // Given
        Long id = 1L;
        EmployeeDto employeeDto = EmployeeDto.builder().id(id).name("Vijay Ghadage Updated").build();
        when(service.updateEmployee(anyLong(), any(EmployeeDto.class)))
            .thenThrow(new RuntimeException("Employee not found"));

        // When
        ResponseEntity<EmployeeDto> response = controller.updateEmployee(id, employeeDto);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Test for deleting an employee by ID")
    @WithMockUser(roles = "ADMIN")
    public void givenEmployeeId_whenDeleteEmployee_thenReturnSuccessMessage() {
        // Given
        Long id = 1L;
        String responseMessage = "Employee is successfully deleted with the ID: 1";
        when(service.deleteEmployee(anyLong())).thenReturn(responseMessage);

        // When
        ResponseEntity<String> response = controller.deleteEmployee(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseMessage);
    }

    @Test
    @DisplayName("Test for getting employee by department code")
    @WithMockUser(roles = "EMPLOYEE")
    public void givenEmployeeId_whenGetEmployeeByDepartmentCode_thenReturnApiResponseDto() {
        // Given
        Long id = 1L;
        EmployeeDto employeeDto = EmployeeDto.builder().departmentCode("DS-001").build();
        DepartmentDto departmentDto = DepartmentDto.builder().build();
        ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto, departmentDto);
        when(service.getEmployeeByIdAndCode(anyLong())).thenReturn(apiResponseDto);

        // When
        ResponseEntity<ApiResponseDto> response = controller.getEmployeeByDepartmentCode(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(apiResponseDto);
    }

    @Test
    @DisplayName("Test for getting employee and task")
    @WithMockUser(roles = "EMPLOYEE")
    public void givenEmployeeId_whenGetEmployeeAndTask_thenReturnTaskApiResponseDto() {
        // Given
        Long id = 1L;
        EmployeeDto employeeDto = EmployeeDto.builder().taskId(1L).build();
        TaskDto taskDto = TaskDto.builder().build();
        TaskApiResponseDto taskApiResponseDto = new TaskApiResponseDto(employeeDto, taskDto);
        when(service.getTaskAndEmployee(anyLong())).thenReturn(taskApiResponseDto);

        // When
        ResponseEntity<TaskApiResponseDto> response = controller.getEmployeeAndTask(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(taskApiResponseDto);
    }

    @Test
    @DisplayName("Test for getting employee performance")
    @WithMockUser(roles = "EMPLOYEE")
    public void givenEmployeeId_whenGetEmployeePerformance_thenReturnPerformanceApiResponseDto() {
        // Given
        Long id = 1L;
        EmployeeDto employeeDto = EmployeeDto.builder().performanceId(1L).build();
        PerformanceDto performanceDto = PerformanceDto.builder().build();
        PerformanceApiResponseDto performanceApiResponseDto = new PerformanceApiResponseDto(employeeDto, performanceDto);
        when(service.getEmployeePerformance(anyLong())).thenReturn(performanceApiResponseDto);

        // When
        ResponseEntity<PerformanceApiResponseDto> response = controller.getEmployeeAndPerformance(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(performanceApiResponseDto);
    }

    @Test
    @DisplayName("Test for getting all services for an employee")
    @WithMockUser(roles = "ADMIN")
    public void givenEmployeeId_whenGetAllServices_thenReturnAllApiResponseDto() {
        // Given
        Long id = 1L;
        EmployeeDto employeeDto = EmployeeDto.builder()
            .departmentCode("DS-001")
            .taskId(1L)
            .performanceId(1L)
            .build();
        DepartmentDto departmentDto = DepartmentDto.builder().build();
        TaskDto taskDto = TaskDto.builder().build();
        PerformanceDto performanceDto = PerformanceDto.builder().build();
        AllApiResponnseDto allApiResponseDto = new AllApiResponnseDto(employeeDto, departmentDto, taskDto, performanceDto);
        when(service.getAllServices(anyLong())).thenReturn(allApiResponseDto);

        // When
        ResponseEntity<AllApiResponnseDto> response = controller.getAllServices(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(allApiResponseDto);
    }
}