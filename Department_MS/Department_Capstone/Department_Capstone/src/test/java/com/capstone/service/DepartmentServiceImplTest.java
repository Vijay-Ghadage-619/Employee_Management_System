package com.capstone.service;

import com.capstone.dto.DepartmentDto;
import com.capstone.entity.Department;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.DepartmentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepo departmentRepo;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createDepartmentTest() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "HR", "Human Resources", "HR001");
        Department department = new Department(1L, "HR", "Human Resources", "HR001");

        when(mapper.map(departmentDto, Department.class)).thenReturn(department);
        when(departmentRepo.save(department)).thenReturn(department);
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.createDepartment(departmentDto);
        assertNotNull(result);
        assertEquals(departmentDto.getId(), result.getId());
    }

    @Test
    public void getAllDepartmentsTest() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "HR", "Human Resources", "HR001");
        Department department = new Department(1L, "HR", "Human Resources", "HR001");

        when(departmentRepo.findAll()).thenReturn(Collections.singletonList(department));
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        assertNotNull(departmentService.getAllDepartments());
        assertEquals(1, departmentService.getAllDepartments().size());
    }

    @Test
    public void getDepartmentByIdTest() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "HR", "Human Resources", "HR001");
        Department department = new Department(1L, "HR", "Human Resources", "HR001");

        when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.getDepartmentById(1L);
        assertNotNull(result);
        assertEquals(departmentDto.getId(), result.getId());
    }

    @Test
    public void updateDepartmentTest() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "HR", "Human Resources", "HR001");
        Department department = new Department(1L, "HR", "Human Resources", "HR001");

        when(departmentRepo.existsById(1L)).thenReturn(true);
        when(mapper.map(departmentDto, Department.class)).thenReturn(department);
        when(departmentRepo.save(department)).thenReturn(department);
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.updateDepartment(1L, departmentDto);
        assertNotNull(result);
        assertEquals(departmentDto.getId(), result.getId());
    }

    @Test
    public void deleteDepartmentTest() {
        when(departmentRepo.existsById(1L)).thenReturn(true);

        String result = departmentService.deleteDepartment(1L);
        assertEquals("Department successfully deleted with ID 1", result);
    }

    @Test
    public void getDepartmentByCodeTest() {
        DepartmentDto departmentDto = new DepartmentDto(1L, "HR", "Human Resources", "HR001");
        Department department = new Department(1L, "HR", "Human Resources", "HR001");

        when(departmentRepo.findByDepartmentCode("HR001")).thenReturn(department);
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.getDepartmentByCode("HR001");
        assertNotNull(result);
        assertEquals(departmentDto.getDepartmentCode(), result.getDepartmentCode());
    }
}
