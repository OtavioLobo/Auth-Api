package com.auth.dtos;

import javax.validation.constraints.NotBlank;

import com.auth.models.UserRoles;


public class RegisterDto {

	@NotBlank
	private String login;
	
	@NotBlank
	private String password;
	
	private UserRoles role;
	
	public RegisterDto(@NotBlank String login, @NotBlank String password, @NotBlank @NotBlank UserRoles role) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public RegisterDto() {
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

	public UserRoles getRole() {
		return role;
	}

	public void setRole( UserRoles role) {
		this.role = role;
	}
	
	
}
