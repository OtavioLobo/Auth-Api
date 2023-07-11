package com.auth.dtos;

public class LoginResponseDto {

	private String token;

	public LoginResponseDto(String token) {
		super();
		this.token = token;
	}

	public LoginResponseDto() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
