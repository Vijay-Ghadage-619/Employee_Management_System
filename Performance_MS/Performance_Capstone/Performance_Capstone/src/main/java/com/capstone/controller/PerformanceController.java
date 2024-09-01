package com.capstone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capstone.dto.PerformanceDto;
import com.capstone.service.PerformanceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name="CRUD Rest Api For Performance Table",
description="Api like -Creare Performance,Get Performance,Update Performance,Delete Performance")
@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Operation(
    		summary="Create Performance Rest API",
    		description="This Rest API is used for creating a new Performance..."
    		)
    @ApiResponse(
    		responseCode="201",
    		description="Http Status 201 Created..."
    		)
    @PostMapping
    public ResponseEntity<PerformanceDto> createPerformance(@RequestBody @Valid PerformanceDto performanceDto) {
        return new ResponseEntity<>(performanceService.createPerformance(performanceDto), HttpStatus.CREATED);
    }

    
    @Operation(
    		summary="Get Performance Rest API",
    		description="This Rest API is used for getting all the Performances available in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @GetMapping
    public ResponseEntity<List<PerformanceDto>> getAllPerformances() {
        return new ResponseEntity<>(performanceService.getAllPerformances(), HttpStatus.OK);
    }

    
    @Operation(
    		summary="Get Performance Rest API",
    		description="This Rest API is used for getting Specific the Performances available in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceDto> getPerformanceById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(performanceService.getPerformanceById(id), HttpStatus.OK);
    }

    
    @Operation(
    		summary="Update Performance Rest API",
    		description="This Rest API is used for updating Specific the Performances available in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceDto> updatePerformance(@PathVariable("id") Long id, @RequestBody @Valid PerformanceDto performanceDto) {
        return new ResponseEntity<>(performanceService.updatePerformance(id, performanceDto), HttpStatus.OK);
    }

    
    @Operation(
    		summary="Delete Performance Rest API",
    		description="This Rest API is used for Deleting Specific the Performances available in our Table..."
    		)
    @ApiResponse(
    		responseCode="200",
    		description="Http Status 200 Success..."
    		)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerformance(@PathVariable("id") Long id) {
        return new ResponseEntity<>(performanceService.deletePerformance(id), HttpStatus.OK);
    }
}
