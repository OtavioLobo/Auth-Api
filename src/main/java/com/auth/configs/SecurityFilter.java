package com.auth.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.service.UserService;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	final TokenService tokenService;
	final UserService userService;

	public SecurityFilter(TokenService tokenService, UserService userService) {
		super();
		this.tokenService = tokenService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var token = this.recoverToken(request);
		
		if(token != null) {
			var login = tokenService.validateToken(token);
			UserDetails user = (UserDetails) userService.findByLogin(login);
			
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

		}
		
		filterChain.doFilter(request, response);
		
	}
	
	public String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader == null) return null;
		
		return authHeader.replace("Bearer ", "");
	}

}
