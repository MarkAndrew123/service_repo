package com.phegondev.PhegonHotel.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Email is required")
	private String email;
    @NotBlank(message = "Password is required")
	private String password;
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
