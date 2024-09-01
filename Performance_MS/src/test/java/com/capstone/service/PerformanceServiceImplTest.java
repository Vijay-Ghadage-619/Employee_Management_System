package com.capstone.service;

import com.capstone.dto.PerformanceDto;
import com.capstone.entity.Performance;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.PerformanceRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class PerformanceServiceImplTest {

    @Mock
    private PerformanceRepo performanceRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenPerformanceDto_whenCreatePerformance_thenReturnCreatedPerformance() {
        // given
        PerformanceDto performanceDto = PerformanceDto.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();
        
        Performance performance = Performance.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();

        when(modelMapper.map(performanceDto, Performance.class)).thenReturn(performance);
        when(performanceRepo.save(performance)).thenReturn(performance);
        when(modelMapper.map(performance, PerformanceDto.class)).thenReturn(performanceDto);

        // when
        PerformanceDto result = performanceService.createPerformance(performanceDto);

        // then
        assertEquals(performanceDto, result);
    }

    @Test
    public void givenPerformancesExist_whenGetAllPerformances_thenReturnPerformanceList() {
        // given
        PerformanceDto performanceDto = PerformanceDto.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();
        
        Performance performance = Performance.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();

        when(performanceRepo.findAll()).thenReturn(Arrays.asList(performance));
        when(modelMapper.map(performance, PerformanceDto.class)).thenReturn(performanceDto);

        // when
        List<PerformanceDto> result = performanceService.getAllPerformances();

        // then
        assertEquals(1, result.size());
        assertEquals(performanceDto, result.get(0));
    }

    @Test
    public void givenPerformanceId_whenGetPerformanceById_thenReturnPerformance() {
        // given
        PerformanceDto performanceDto = PerformanceDto.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();
        
        Performance performance = Performance.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();

        when(performanceRepo.findById(anyLong())).thenReturn(Optional.of(performance));
        when(modelMapper.map(performance, PerformanceDto.class)).thenReturn(performanceDto);

        // when
        PerformanceDto result = performanceService.getPerformanceById(1L);

        // then
        assertEquals(performanceDto, result);
    }

    @Test
    public void givenUpdateData_whenUpdatePerformance_thenReturnUpdatedPerformance() {
        // given
        PerformanceDto performanceDto = PerformanceDto.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.5)
                .build();
        
        Performance performance = Performance.builder()
                .id(1L)
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.5)
                .build();

        when(performanceRepo.existsById(anyLong())).thenReturn(true);
        when(modelMapper.map(performanceDto, Performance.class)).thenReturn(performance);
        when(performanceRepo.save(performance)).thenReturn(performance);
        when(modelMapper.map(performance, PerformanceDto.class)).thenReturn(performanceDto);

        // when
        PerformanceDto result = performanceService.updatePerformance(1L, performanceDto);

        // then
        assertEquals(performanceDto, result);
    }

    @Test
    public void givenPerformanceId_whenDeletePerformance_thenReturnSuccessMessage() {
        // given
        when(performanceRepo.existsById(anyLong())).thenReturn(true);

        // when
        String result = performanceService.deletePerformance(1L);

        // then
        assertEquals("Performance successfully deleted with ID 1", result);
    }

    @Test
    public void givenPerformanceId_whenDeletePerformanceNotFound_thenThrowIdNotFoundException() {
        // given
        when(performanceRepo.existsById(anyLong())).thenReturn(false);

        // when
        IdNotFound thrown = assertThrows(IdNotFound.class, () -> performanceService.deletePerformance(1L));

        // then
        assertEquals("Performance with ID 1 not found.", thrown.getMessage());
    }
}
