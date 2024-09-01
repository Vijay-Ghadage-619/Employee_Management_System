package com.capstone.controller;

import com.capstone.dto.DepartmentDto;
import com.capstone.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DepartmentControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    @DisplayName("Test for Creating the Department and saving it into DB...")
    public void givenDepartmentDto_whenCreateDepartment_thenReturnDepartmentDto() throws Exception {
        // Given
        DepartmentDto departmentDto = DepartmentDto.builder()
            .id(2L)
            .name("Full Stack Java")
            .description("This department is related to Java Development only...")
            .departmentCode("FS-001")
            .build();

        when(departmentService.createDepartment(any(DepartmentDto.class))).thenReturn(departmentDto);

        // When
        mockMvc.perform(post("/api/departments")
                .contentType("application/json")
                .content(asJsonString(departmentDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(departmentDto.getId()))
                .andExpect(jsonPath("$.name").value(departmentDto.getName()));
    }

    @Test
    @DisplayName("Test for Fetching all Departments from DB...")
    public void givenDepartmentList_whenGetAllDepartments_thenReturnDepartmentList() throws Exception {
        // Given
        DepartmentDto departmentDto = DepartmentDto.builder()
            .id(2L)
            .name("Full Stack Java")
            .description("This department is related to Java Development only...")
            .departmentCode("FS-001")
            .build();

        when(departmentService.getAllDepartments()).thenReturn(Collections.singletonList(departmentDto));

        // When
        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(departmentDto.getId()))
                .andExpect(jsonPath("$[0].name").value(departmentDto.getName()));
    }

    @Test
    @DisplayName("Test for Fetching the Department details of a given Id from DB...")
    public void givenDepartmentId_whenGetDepartmentById_thenReturnDepartmentDto() throws Exception {
        // Given
        DepartmentDto departmentDto = DepartmentDto.builder()
            .id(2L)
            .name("Full Stack Java")
            .description("This department is related to Java Development only...")
            .departmentCode("FS-001")
            .build();

        when(departmentService.getDepartmentById(2L)).thenReturn(departmentDto);

        // When
        mockMvc.perform(get("/api/departments/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentDto.getId()))
                .andExpect(jsonPath("$.name").value(departmentDto.getName()));
    }

    @Test
    @DisplayName("Test for Updating the Department details of a given Id in DB...")
    public void givenDepartmentId_whenUpdateDepartment_thenReturnUpdatedDepartmentDto() throws Exception {
        // Given
        DepartmentDto departmentDto = DepartmentDto.builder()
            .id(2L)
            .name("Updated Department")
            .description("Updated description")
            .departmentCode("FS-001")
            .build();

        when(departmentService.updateDepartment(any(Long.class), any(DepartmentDto.class))).thenReturn(departmentDto);

        // When
        mockMvc.perform(put("/api/departments/2")
                .contentType("application/json")
                .content(asJsonString(departmentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentDto.getId()))
                .andExpect(jsonPath("$.name").value(departmentDto.getName()));
    }

    @Test
    @DisplayName("Test for Deleting the Department of a given Id from DB...")
    public void givenDepartmentId_whenDeleteDepartment_thenReturnSuccessMessage() throws Exception {
        // Given
        when(departmentService.deleteDepartment(2L)).thenReturn("Department successfully deleted with ID 2");

        // When
        mockMvc.perform(delete("/api/departments/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Department successfully deleted with ID 2"));
    }

    @Test
    @DisplayName("Test for Fetching the Department by Department Code...")
    public void givenDepartmentCode_whenGetDepartmentByCode_thenReturnDepartmentDto() throws Exception {
        // Given
        DepartmentDto departmentDto = DepartmentDto.builder()
            .id(2L)
            .name("Full Stack Java")
            .description("This department is related to Java Development only...")
            .departmentCode("FS-001")
            .build();

        when(departmentService.getDepartmentByCode("FS-001")).thenReturn(departmentDto);

        // When
        mockMvc.perform(get("/api/departments/code/FS-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentCode").value(departmentDto.getDepartmentCode()));
    }

    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
