package com.capstone.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.dto.PerformanceDto;

@FeignClient("PERFORMANCE-MS")
public interface PerformanceApiClient {

	 @GetMapping("/api/performance/{id}")
	    public PerformanceDto getPerformanceById(@PathVariable("id") Long id);
	
}