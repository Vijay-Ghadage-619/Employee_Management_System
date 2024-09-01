package com.capstone.controller;

import com.capstone.dto.TaskDto;
import com.capstone.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        taskDto = TaskDto.builder()
                .id(1L)
                .title("Creating the dashboard")
                .description("Create the dashboard for the sales of our new product")
                .status("IN_PROGRESS")
                .build();
    }

    @Test
    void givenTaskDto_whenCreateTask_thenCreatedResponseWithTaskDto() throws Exception {
        // Given
        when(taskService.createTask(any(TaskDto.class))).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(taskDto.getId().intValue())))
                .andExpect(jsonPath("$.title", is(taskDto.getTitle())))
                .andExpect(jsonPath("$.description", is(taskDto.getDescription())))
                .andExpect(jsonPath("$.status", is(taskDto.getStatus())));
    }

    @Test
    void givenTasks_whenGetAllTasks_thenOkResponseWithTasks() throws Exception {
        // Given
        List<TaskDto> taskDtos = Collections.singletonList(taskDto);
        when(taskService.getAllTasks()).thenReturn(taskDtos);

        // When & Then
        mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(taskDto.getId().intValue())))
                .andExpect(jsonPath("$[0].title", is(taskDto.getTitle())))
                .andExpect(jsonPath("$[0].description", is(taskDto.getDescription())))
                .andExpect(jsonPath("$[0].status", is(taskDto.getStatus())));
    }

    @Test
    void givenTask_whenGetTaskById_thenOkResponseWithTask() throws Exception {
        // Given
        when(taskService.getTaskById(anyLong())).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(taskDto.getId().intValue())))
                .andExpect(jsonPath("$.title", is(taskDto.getTitle())))
                .andExpect(jsonPath("$.description", is(taskDto.getDescription())))
                .andExpect(jsonPath("$.status", is(taskDto.getStatus())));
    }

    @Test
    void givenTaskDto_whenUpdateTask_thenOkResponseWithUpdatedTask() throws Exception {
        // Given
        when(taskService.updateTask(anyLong(), any(TaskDto.class))).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(taskDto.getId().intValue())))
                .andExpect(jsonPath("$.title", is(taskDto.getTitle())))
                .andExpect(jsonPath("$.description", is(taskDto.getDescription())))
                .andExpect(jsonPath("$.status", is(taskDto.getStatus())));
    }

    @Test
    void givenTask_whenDeleteTask_thenOkResponseWithDeletionMessage() throws Exception {
        // Given
        when(taskService.deleteTask(anyLong())).thenReturn("Task successfully deleted with ID 1");

        // When & Then
        mockMvc.perform(delete("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Task successfully deleted with ID 1"));
    }
}
