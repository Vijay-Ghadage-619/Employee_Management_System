package com.capstone.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerformanceDto {
	
	 private Long id;

	    @NotBlank(message = "Performance title cannot be empty")
	    private String title;

	    private String description;
	    
	    @NotNull(message = "Rating Can't be kept Null...")
	    @Min(value = 0, message = "Rating can be Given at least 0")
	    @Max(value = 5, message = "Rating can be Given at most 5")
	    private Double rating;

}
