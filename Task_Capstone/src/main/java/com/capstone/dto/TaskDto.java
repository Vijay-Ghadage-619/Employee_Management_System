package com.capstone.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

    private Long id;

    @NotEmpty(message = "Task title cannot be empty.")
    private String title;

    @NotEmpty(message = "Task description cannot be empty.")
    private String description;

    @NotEmpty(message = "Task description cannot be empty.")
    private String status; // e.g., "Pending", "In Progress", "Completed"
}
