package com.capstone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.dto.TaskDto;

import jakarta.transaction.Transactional;

@Service
public interface TaskService {

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);

    String deleteTask(Long id);
}
