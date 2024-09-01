package com.capstone.controller;

import com.capstone.dto.PerformanceDto;
import com.capstone.service.PerformanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PerformanceControllerTest {

    @Mock
    private PerformanceService performanceService;

    @InjectMocks
    private PerformanceController performanceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(performanceController).build();
    }

    @Test
    @DisplayName("Given valid performance data, when creating a performance, then return created performance")
    public void givenPerformance_whenCreatePerformance_thenReturnCreatedPerformance() throws Exception {
        // given
        PerformanceDto performanceDto = PerformanceDto.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();
        given(performanceService.createPerformance(any(PerformanceDto.class))).willReturn(performanceDto);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/performance")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Quarter_1-2024\", \"description\": \"This performance is given for the first quarter of year 2024\", \"rating\": 4.0}"))

        // then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.title").value("Quarter_1-2024"))
                .andExpect(jsonPath("$.description").value("This performance is given for the first quarter of year 2024"))
                .andExpect(jsonPath("$.rating").value(4.0));
    }

    @Test
    @DisplayName("Given performances exist, when retrieving all performances, then return performance list")
    public void givenPerformancesExist_whenGetAllPerformances_thenReturnPerformanceList() throws Exception {
        // given
        PerformanceDto performanceDto = PerformanceDto.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();
        List<PerformanceDto> performanceDtos = Collections.singletonList(performanceDto);
        given(performanceService.getAllPerformances()).willReturn(performanceDtos);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/performance")
                .contentType(MediaType.APPLICATION_JSON))

        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].title").value("Quarter_1-2024"))
                .andExpect(jsonPath("$[0].description").value("This performance is given for the first quarter of year 2024"))
                .andExpect(jsonPath("$[0].rating").value(4.0));
    }

    @Test
    @DisplayName("Given performance exists, when retrieving by ID, then return performance")
    public void givenPerformanceExists_whenGetPerformanceById_thenReturnPerformance() throws Exception {
        // given
        PerformanceDto performanceDto = PerformanceDto.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();
        given(performanceService.getPerformanceById(anyLong())).willReturn(performanceDto);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/performance/1")
                .contentType(MediaType.APPLICATION_JSON))

        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").value("Quarter_1-2024"))
                .andExpect(jsonPath("$.description").value("This performance is given for the first quarter of year 2024"))
                .andExpect(jsonPath("$.rating").value(4.0));
    }

    @Test
    @DisplayName("Given valid update data, when updating performance, then return updated performance")
    public void givenUpdateData_whenUpdatePerformance_thenReturnUpdatedPerformance() throws Exception {
        // given
        PerformanceDto performanceDto = PerformanceDto.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();
        given(performanceService.updatePerformance(anyLong(), any(PerformanceDto.class))).willReturn(performanceDto);

        // when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/performance/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Quarter_1-2024\", \"description\": \"This performance is given for the first quarter of year 2024\", \"rating\": 4.0}"))

        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").value("Quarter_1-2024"))
                .andExpect(jsonPath("$.description").value("This performance is given for the first quarter of year 2024"))
                .andExpect(jsonPath("$.rating").value(4.0));
    }

    @Test
    @DisplayName("Given performance ID, when deleting performance, then return success message")
    public void givenPerformanceId_whenDeletePerformance_thenReturnSuccessMessage() throws Exception {
        // given
        String expectedMessage = "Performance successfully deleted with ID 1";
        given(performanceService.deletePerformance(anyLong())).willReturn(expectedMessage);

        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/performance/1")
                .contentType(MediaType.APPLICATION_JSON))

        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedMessage));
    }
}
