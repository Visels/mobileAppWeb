package com.app.ws.ui.Service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.ws.ui.Entities.UserEntity;
import com.app.ws.ui.Repositories.UserRepository;
import com.app.ws.ui.Service.UserService;
import com.app.ws.ui.shared.Utils;
import com.app.ws.ui.shared.dto.UserDto;



@Service
public class UserServiceImpl implements UserService {

	


	@Autowired
	UserRepository userRepo;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		
		
		if(userRepo.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("User already exists");
		}
	
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(user, userEntity);
		
		String generatedUserId = utils.generatedUserId(7);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(generatedUserId);
		
		UserEntity StoredUserDetails=userRepo.save(userEntity);
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(StoredUserDetails, userDto);
		
		return userDto;
	
	}

	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity user = userRepo.findByEmail(email);

		if(user == null){throw new UsernameNotFoundException(email);}

		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(user, returnValue);

		return returnValue;
	}

	@Override
	public UserDto getUserById(String userId) {
		UserDto returnValue = new UserDto();

		UserEntity foundUser = userRepo.findByUserId(userId);

		if(foundUser != null){
			BeanUtils.copyProperties(foundUser, returnValue);
		}
		else{
			throw new UsernameNotFoundException(userId);
		}

		return returnValue;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity foundUserDetails = userRepo.findByEmail(email);
		
		if(foundUserDetails == null) { throw new UsernameNotFoundException(email);}
			
		return  new User(foundUserDetails.getEmail(), foundUserDetails.getEncryptedPassword(), new ArrayList<>());
		}
	

}
