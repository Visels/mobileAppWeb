package com.app.ws.ui.controllers;

import com.app.ws.ui.Exceptions.UserServiceExceptions;
import com.app.ws.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.ws.ui.Service.impl.UserServiceImpl;
import com.app.ws.ui.model.request.UserDetailsRequestModel;
import com.app.ws.ui.model.response.UserRest;
import com.app.ws.ui.shared.dto.UserDto;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
		
	@GetMapping(path = "/{id}")
	public UserRest getUsers( @PathVariable String id) {

		UserRest returnValue = new UserRest();

		UserDto foundUser = userService.getUserById(id);

		BeanUtils.copyProperties(foundUser, returnValue);


		return returnValue;

	}
	
	
	@PostMapping("/create")
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {


		//sample error message
		if(userDetails.getFirstName().isEmpty()) {

			throw new UserServiceExceptions(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}


		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
		
		}
	
	
	@PutMapping("/update")
	public String updateUser() {
		return "User has been updated";
	}
	
	
	@DeleteMapping("/delete")
	public String deleteUser() {
		return "User has been deleted";
	}
		
}