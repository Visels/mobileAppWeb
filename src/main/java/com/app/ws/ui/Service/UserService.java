package com.app.ws.ui.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.ws.ui.shared.dto.UserDto;



public interface UserService extends UserDetailsService {
	
	UserDto createUser(UserDto user);

	UserDto getUserByEmail(String email);

	UserDto getUserById(String userId);



}
