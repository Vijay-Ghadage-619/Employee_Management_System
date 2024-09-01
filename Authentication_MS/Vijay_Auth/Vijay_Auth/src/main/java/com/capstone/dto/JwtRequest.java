package com.capstone.dto;

import jakarta.validation.constraints.NotBlank;

public class JwtRequest {

	    @NotBlank
	    private String username;

	    @NotBlank
	    private String password;

	    // Default constructor for JSON deserialization
	    public JwtRequest() {
	    }

	    // Constructor with parameters
	    public JwtRequest(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    // Getters and setters
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}
