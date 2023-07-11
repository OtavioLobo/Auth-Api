package com.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.auth.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	public UserDetails findByLogin(String login);
}
