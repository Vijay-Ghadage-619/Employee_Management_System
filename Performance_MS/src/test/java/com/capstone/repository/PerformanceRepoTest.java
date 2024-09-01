package com.capstone.repository;


import com.capstone.entity.Performance;
import com.capstone.repository.PerformanceRepo;

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
public class PerformanceRepoTest {

    @Autowired
    private PerformanceRepo performanceRepo;

    private Performance performance;

    @BeforeEach
    public void setUp() {
        performance = Performance.builder()
                .title("Quarter_1-2024")
                .description("This performance is given for the first quarter of year 2024")
                .rating(4.0)
                .build();

        performanceRepo.save(performance);
    }
    

     
    @Test
    @DisplayName("Given a performance, when saved, then it should be retrievable by ID")
    public void givenPerformance_whenSaved_thenShouldBeRetrievableById() {
        // when
        Performance foundPerformance = performanceRepo.findById(performance.getId()).orElse(null);

        // then
        assertThat(foundPerformance).isNotNull();
        assertThat(foundPerformance.getId()).isEqualTo(performance.getId());
        assertThat(foundPerformance.getTitle()).isEqualTo(performance.getTitle());
        assertThat(foundPerformance.getDescription()).isEqualTo(performance.getDescription());
        assertThat(foundPerformance.getRating()).isEqualTo(performance.getRating());
    }

    @Test
    @DisplayName("Given a performance, when deleted, then it should no longer be retrievable")
    public void givenPerformance_whenDeleted_thenShouldNotBeRetrievable() {
        // when
        performanceRepo.delete(performance);
        Optional<Performance> deletedPerformance = performanceRepo.findById(performance.getId());

        // then
        assertThat(deletedPerformance).isEmpty();
    }

    @Test
    @DisplayName("When saving multiple performances, then they should all be retrievable")
    public void whenSavingMultiplePerformances_thenShouldBeRetrievable() {
        // given
        Performance performance1 = Performance.builder()
                .title("Quarter_2-2024")
                .description("This performance is given for the second quarter of year 2024")
                .rating(3.5)
                .build();
        Performance performance2 = Performance.builder()
                .title("Quarter_3-2024")
                .description("This performance is given for the third quarter of year 2024")
                .rating(4.5)
                .build();

        performanceRepo.save(performance1);
        performanceRepo.save(performance2);

        // when
        Iterable<Performance> performances = performanceRepo.findAll();

        // then
        assertThat(performances).hasSize(3); // Including the initial performance
        assertThat(performances).extracting(Performance::getTitle).containsExactlyInAnyOrder(
                "Quarter_1-2024", "Quarter_2-2024", "Quarter_3-2024"
        );
    }
}
