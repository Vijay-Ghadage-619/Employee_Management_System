package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
		description="DepartmentDto Model Information"
		)
public class DepartmentDto {
	@Schema(
			description="ID of department"
			)
    private Long id;

	@Schema(
			description="Name of department"
			)
    @NotEmpty(message = "Department name cannot be empty,Plz Give Properly...")
    private String name;

	@Schema(
			description="Description  of department"
			)
    @NotEmpty(message = "Department description cannot be empty.")
    @Size(max = 200, message = "Description cannot be more than 200 charscters...")
    private String description;
    
	@Schema(
			description="Code of department"
			)
    @NotEmpty(message = "Department code should not be empty,Plz Give Properly...")
    private String departmentCode;
}
