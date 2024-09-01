package com.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.dto.TaskDto;
import com.capstone.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name="CRUD Rest Api For Task Table",
description="Api like -Creare Task,Get Task,Update Task,Delete Task")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    
    @Operation(
    		summary="Create Task Rest API",
    		description="This Rest API is used for creating a new Task..."
    		)
    @ApiResponse(
    		responseCode="201",
    		description="Http Status 201 Created..."
    		)
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    
    
    @Operation(
    		summary="Get Task Rest API",
    		description="This Rest API is used for getting all the Tasks from table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 201 Success..."
    		)
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }


    
    @Operation(
    		summary="Get Task Rest API",
    		description="This Rest API is used for getting Specific Task from table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 201 Success..."
    		)
    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable("id") Long id) {
        return taskService.getTaskById(id);
    }
    
    
    
    @Operation(
    		summary="Update Task Rest API",
    		description="This Rest API is used for Updating the Specific Task in table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 201 Success..."
    		)
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDto taskDto) {
        return new ResponseEntity<>(taskService.updateTask(id, taskDto), HttpStatus.OK);
    }

    
    
    @Operation(
    		summary="Delete Task Rest API",
    		description="This Rest API is used for Deleting the Specific Task in table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 201 Success..."
    		)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.OK);
    }
    
    
}
