package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Details about the employee")
public class EmployeeDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the employee", example = "1")
    private Long id;

    @NotBlank(message = "Name field cannot be empty or null. Please enter a valid value.")
    @Schema(description = "Name of the employee", example = "John Doe")
    private String name;

    @Email(message = "Email should be valid")
    @Schema(description = "Email of the employee", example = "john.doe@example.com")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Min(value = 1000000000L, message = "Phone number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be at most 10 digits")
    @Schema(description = "Phone number of the employee", example = "1234567890")
    private Long phoneNumber;

    @NotBlank(message = "Job role cannot be empty")
    @Schema(description = "Job role of the employee", example = "Software Engineer")
    private String jobRole;

    @Max(value = 10000000L, message = "Salary is too high. Enter a proper value.")
    @Min(value = 999L, message = "Salary is too low. Enter a proper value.")
    @Schema(description = "Salary of the employee", example = "75000.00")
    private Double salary;

    @Size(min = 2, max = 30, message = "Department name must be between 2 and 30 characters")
    @Schema(description = "Department code of the employee", example = "IT")
    private String departmentCode;

    @Schema(description = "Task ID associated with the employee", example = "123")
    private Long taskId;

    @Schema(description = "Performance ID associated with the employee", example = "456")
    private Long performanceId;
}
