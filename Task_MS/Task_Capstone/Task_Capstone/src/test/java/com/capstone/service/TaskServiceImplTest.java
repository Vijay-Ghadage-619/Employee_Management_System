package com.capstone.service;

import com.capstone.dto.TaskDto;
import com.capstone.entity.Task;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;
    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        task = Task.builder()
            .id(1L)
            .title("Creating the dashboard")
            .description("Create the dashboard for the sales of our new product")
            .status("IN_PROGRESS")
            .build();
            
        taskDto = TaskDto.builder()
        		  .title("Creating the dashboard")
                  .description("Create the dashboard for the sales of our new product")
                  .status("IN_PROGRESS")
                  .build();
    }

    @Test
    void givenTaskDto_whenCreateTask_thenTaskDtoReturned() {
        // Given
        when(taskRepo.save(any(Task.class))).thenReturn(task);
        when(modelMapper.map(any(TaskDto.class), eq(Task.class))).thenReturn(task);
        when(modelMapper.map(any(Task.class), eq(TaskDto.class))).thenReturn(taskDto);

        // When
        TaskDto result = taskService.createTask(taskDto);

        // Then
        assertNotNull(result);
        assertEquals(taskDto.getTitle(), result.getTitle());
        verify(taskRepo, times(1)).save(any(Task.class));
    }

    @Test
    void givenTasks_whenGetAllTasks_thenTaskDtoListReturned() {
        // Given
        List<Task> tasks = Arrays.asList(task);
        when(taskRepo.findAll()).thenReturn(tasks);
        when(modelMapper.map(any(Task.class), eq(TaskDto.class))).thenReturn(taskDto);

        // When
        List<TaskDto> result = taskService.getAllTasks();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(taskDto.getTitle(), result.get(0).getTitle());
        verify(taskRepo, times(1)).findAll();
    }

    @Test
    void givenTaskExists_whenGetTaskById_thenTaskDtoReturned() {
        // Given
        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
        when(modelMapper.map(any(Task.class), eq(TaskDto.class))).thenReturn(taskDto);

        // When
        TaskDto result = taskService.getTaskById(1L);

        // Then
        assertNotNull(result);
        assertEquals(taskDto.getTitle(), result.getTitle());
        verify(taskRepo, times(1)).findById(1L);
    }

    @Test
    void givenTaskNotFound_whenGetTaskById_thenIdNotFoundExceptionThrown() {
        // Given
        when(taskRepo.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IdNotFound.class, () -> taskService.getTaskById(1L));
        verify(taskRepo, times(1)).findById(1L);
    }

    @Test
    void givenTaskExists_whenUpdateTask_thenUpdatedTaskDtoReturned() {
        // Given
        when(taskRepo.existsById(1L)).thenReturn(true);
        when(taskRepo.save(any(Task.class))).thenReturn(task);
        when(modelMapper.map(any(TaskDto.class), eq(Task.class))).thenReturn(task);
        when(modelMapper.map(any(Task.class), eq(TaskDto.class))).thenReturn(taskDto);

        // When
        TaskDto result = taskService.updateTask(1L, taskDto);

        // Then
        assertNotNull(result);
        assertEquals(taskDto.getTitle(), result.getTitle());
        verify(taskRepo, times(1)).existsById(1L);
        verify(taskRepo, times(1)).save(any(Task.class));
    }

    @Test
    void givenTaskNotExists_whenUpdateTask_thenIdNotFoundExceptionThrown() {
        // Given
        when(taskRepo.existsById(1L)).thenReturn(false);

        // When & Then
        assertThrows(IdNotFound.class, () -> taskService.updateTask(1L, taskDto));
        verify(taskRepo, times(1)).existsById(1L);
    }

    @Test
    void givenTaskExists_whenDeleteTask_thenSuccessMessageReturned() {
        // Given
        when(taskRepo.existsById(1L)).thenReturn(true);

        // When
        String result = taskService.deleteTask(1L);

        // Then
        assertEquals("Task successfully deleted with ID 1", result);
        verify(taskRepo, times(1)).deleteById(1L);
    }

    @Test
    void givenTaskNotExists_whenDeleteTask_thenIdNotFoundExceptionThrown() {
        // Given
        when(taskRepo.existsById(1L)).thenReturn(false);

        // When & Then
        assertThrows(IdNotFound.class, () -> taskService.deleteTask(1L));
        verify(taskRepo, times(1)).existsById(1L);
    }
}
