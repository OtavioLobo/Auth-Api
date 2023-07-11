package com.auth.service;

import org.springframework.stereotype.Service;

import com.auth.models.UserModel;
import com.auth.repositories.UserRepository;

@Service
public class UserService {

	final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public Object findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	public Object save(UserModel newUser) {
		return userRepository.save(newUser);
	}
	
	 
	
}
