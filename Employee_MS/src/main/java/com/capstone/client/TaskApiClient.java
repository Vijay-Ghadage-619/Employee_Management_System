package com.capstone.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.dto.TaskDto;

@FeignClient("TASK-MS")
public interface TaskApiClient {

	 @GetMapping("/api/tasks/{id}")
	    public TaskDto getTaskById(@PathVariable("id") Long id);
	
}

