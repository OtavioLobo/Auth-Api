package com.auth.dtos;

import javax.validation.constraints.NotBlank;

public class AuthenticationDto {

	@NotBlank
	private String login;
	
	@NotBlank
	private String password;

	public AuthenticationDto(@NotBlank String login, @NotBlank String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	public AuthenticationDto() {
		super();
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
