package com.capstone.entity;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
	
	private String jwtToken;
	private String userName;
	private Collection<String> roles;
	
	

}