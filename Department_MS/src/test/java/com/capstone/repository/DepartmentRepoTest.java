package com.capstone.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.capstone.entity.Department;

@DataJpaTest
public class DepartmentRepoTest {

    @Autowired
    private DepartmentRepo repository;

    @Test
    @DisplayName("Test for Creating the Department and saving it into DB...")
    public void givenEmployee_whenSave_thenReturnSavedEmployee() {
        // Given - setup for our test
        Department d1 = Department.builder()
            .name("Data science")
            .description("This department is related to data science field...")
            .departmentCode("DS-001")
            .id(1L)
            .build();
        
        // When
        Department savedDepartment = repository.save(d1);

        // Then
        assertThat(savedDepartment).isNotNull();
        assertThat(savedDepartment.getId()).isNotNull(); // ID should be generated
        assertThat(savedDepartment.getDepartmentCode()).isEqualTo("DS-001");
    }

    
    @Test
    @DisplayName("Test for Fetching all Departments from DB...")
    public void givenDepartmentList_whenFindAll_thenDepartmentList() {
        // Given - setup for our test
    	Department d1 = Department.builder()
    			  .name("Data science")
    	            .description("This department is related to data science field...")
    	            .departmentCode("DS-001")
    	            .id(1L)
    	            .build();

    	Department d2 = Department.builder()
  			  .name("Full Stack Java")
  	            .description("This department is related to Java Development only...")
  	            .departmentCode("FS-001")
  	            .id(2L)
  	            .build();


        repository.save(d1);
        repository.save(d2);

        // When
        List<Department> departmentList = repository.findAll();

        // Then
        assertThat(departmentList).isNotNull();
 //    assertThat(departmentList.size()).isEqualTo(2);

    }
    
 
    @Test
    @DisplayName("Test for fetching the Department data of given Id from DB...")
    public void givenDepartment_whenFindById_thenReturnDepartment() {
        // Given - setup for our test
    	Department d1 = Department.builder()
    			  .name("Full Stack Java")
    	            .description("This department is related to Java Development only...")
    	            .departmentCode("FS-001")
    	            .id(2L)
    	            .build();
        
        Department savedDepartment = repository.save(d1);

        // When
        Optional<Department> depa = repository.findById(savedDepartment.getId());

        // Then
        assertThat(depa).isPresent(); // Check if the department is present
        assertThat(depa.get()).isNotNull(); // check whether it is not Null
        assertThat(depa.get().getId()).isGreaterThan(0); // Check that the ID is greater than 0
    }
    
    
    @Test
    @DisplayName("Test for fetching the Department data of given Code from DB...")
    public void givenDepartment_whenFindByCode_thenReturnDepartment() {
        // Given - setup for our test
        Department d1 = Department.builder()
            .name("Full Stack Java")
            .description("This department is related to Java Development only...")
            .departmentCode("FS-001")
            .id(2L)
            .build();
        
        Department savedDepartment = repository.save(d1);

        // When
        Department depa = repository.findByDepartmentCode(savedDepartment.getDepartmentCode());

        // Then
        assertThat(depa).isNotNull(); // Check if the department is present
        assertThat(depa.getId()).isGreaterThan(0); // Check that the ID is greater than 0
    }

    
    
 
    @Test
    @DisplayName("Test for Updating the Department of given Id in DB...")
    public void givenDepartment_whenFindById_thenReturnUpdatedEmployee() {
        // Given - setup for our test
    	Department d1 = Department.builder()
  			  .name("Full Stack Java")
  	            .description("This department is related to Java Development only...")
  	            .departmentCode("FS-001")
  	            .id(2L)
  	            .build();
        
        // When
        Department savedDepa = repository.save(d1);
        savedDepa.setName("Full Stack Java");
        Department updatedEmployee = repository.save(savedDepa);
        
        // Then
        assertThat(updatedEmployee.getName()).isEqualTo("Full Stack Java");
    }
    
    
    @Test
    @DisplayName("Test for Deleting the Department of given Id from DB...")
    public void givenDepartment_whenFindById_thenDeleteThatDepartment() {
        // Given - setup for our test
    	Department d1 = Department.builder()
    			  .name("Full Stack Java")
    	            .description("This department is related to Java Development only...")
    	            .departmentCode("FS-001")
    	            .id(2L)
    	            .build();
 
        Department savedDepa = repository.save(d1);
        
        //when
        repository.delete(savedDepa);
        
        Optional<Department> Optional =repository.findById(savedDepa.getId());
       
    }
    
   
}
