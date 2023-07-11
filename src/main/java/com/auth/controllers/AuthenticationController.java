package com.auth.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.configs.TokenService;
import com.auth.dtos.AuthenticationDto;
import com.auth.dtos.LoginResponseDto;
import com.auth.dtos.RegisterDto;
import com.auth.models.UserModel;
import com.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	final AuthenticationManager authenticationManager;
	final UserService userService;
	final TokenService tokenService;

	public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((UserModel) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDto(token));
	} 
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDto data) {
		if(this.userService.findByLogin(data.getLogin()) != null ) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Nome de usuario ja existente!");
		}
		String encryPassowrd = new BCryptPasswordEncoder().encode(data.getPassword());
		
		UserModel newUser = new UserModel(data.getLogin(), encryPassowrd, data.getRole());
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(newUser));
	}
	
}
