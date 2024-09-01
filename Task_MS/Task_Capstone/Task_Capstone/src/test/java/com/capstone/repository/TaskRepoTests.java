package com.capstone.repository;

import com.capstone.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test") // Use this if you have a specific profile for tests
public class TaskRepoTests {

    @Autowired
    private TaskRepo taskRepo;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = Task.builder()
                .title("Creating the dashboard")
                .description("Create the dashboard for the sales of our new product")
                .status("IN_PROGRESS")
                .build();

        taskRepo.save(task);
    }

    @Test
    @DisplayName("Given a task, when saved, then it should be retrievable by ID")
    public void givenTask_whenSaved_thenShouldBeRetrievableById() {
        // Given a saved task in setUp()

        // When
        Task foundTask = taskRepo.findById(task.getId()).orElse(null);

        // Then
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getId()).isEqualTo(task.getId());
        assertThat(foundTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(foundTask.getDescription()).isEqualTo(task.getDescription());
        assertThat(foundTask.getStatus()).isEqualTo(task.getStatus());
    }

    @Test
    @DisplayName("Given a task, when deleted, then it should no longer be retrievable")
    public void givenTask_whenDeleted_thenShouldNotBeRetrievable() {
        // Given a saved task in setUp()

        // When
        taskRepo.delete(task);
        Optional<Task> deletedTask = taskRepo.findById(task.getId());

        // Then
        assertThat(deletedTask).isEmpty();
    }

    @Test
    @DisplayName("Given multiple tasks, when saved, then they should all be retrievable")
    public void givenMultipleTasks_whenSaved_thenShouldBeRetrievable() {
        // Given
        Task task1 = Task.builder()
                 .title("Creating the Presentation")
                 .description("Create the Presentation for tomorrow's meeting")
                 .status("DONE")
                 .build();
        Task task2 = Task.builder()
                 .title("Creating the Report")
                 .description("Create the Report for tomorrow's meeting")
                 .status("IN_PROGRESS")
                 .build();

        taskRepo.save(task1);
        taskRepo.save(task2);

        // When
        Iterable<Task> tasks = taskRepo.findAll();

        // Then
        assertThat(tasks).hasSize(3); // Including the initial task
        assertThat(tasks).extracting(Task::getTitle).containsExactlyInAnyOrder(
                "Creating the dashboard", "Creating the Presentation", "Creating the Report"
        );
    }

    @Test
    @DisplayName("Given a task, when updated, then the changes should be saved")
    public void givenTask_whenUpdated_thenChangesShouldBeSaved() {
        // Given a saved task in setUp()

        // When
        Task foundTask = taskRepo.findById(task.getId()).orElse(null);
        assertThat(foundTask).isNotNull();

        // Update the task
        foundTask.setTitle("Updated Task Title");
        foundTask.setDescription("Updated description for the task.");
        foundTask.setStatus("DONE");

        taskRepo.save(foundTask);

        // Then
        Task updatedTask = taskRepo.findById(task.getId()).orElse(null);
        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getTitle()).isEqualTo("Updated Task Title");
        assertThat(updatedTask.getDescription()).isEqualTo("Updated description for the task.");
        assertThat(updatedTask.getStatus()).isEqualTo("DONE");
    }
}
