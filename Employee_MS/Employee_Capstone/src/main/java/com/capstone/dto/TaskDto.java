package com.capstone.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class TaskDto {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotEmpty(message = "Task title cannot be empty.")
	    private String title;

	    @NotEmpty(message = "Task description cannot be empty.")
	    private String description;
	    
	    @NotEmpty
	    private String status; // e.g., "Pending", "In Progress", "Completed"
	
//	    private Long taskId;
}
