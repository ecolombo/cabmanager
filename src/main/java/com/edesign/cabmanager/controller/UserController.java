package com.edesign.cabmanager.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edesign.cabmanager.dto.ResponseDto;
import com.edesign.cabmanager.entity.User;
import com.edesign.cabmanager.service.UserService;

// CRUD operations for user
//todo: implement ResponseDto, exception handling etc.

@RestController
public class UserController {

	@Autowired 
	UserService userService;
	
	/**
	 * Get all users
	 * @return
	 */
	@GetMapping("/users")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "userId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){
		return userService.getUsers(page,size, sort, sortOrder);
	}
	
	/**
	 * Get one user
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId){
		return userService.getUser(userId);
	}
	
	/**
	 * Add user
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public User addUser(@RequestBody User user){
		return userService.addUser(user);
	}
	
	/**
	 * Update user
	 * @param user
	 * @return
	 */
	@PutMapping("/users")
	public User updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	/**
	 * Delete user
	 * @param userId
	 * @return
	 */
	@DeleteMapping("/users/{userId}")
	public ResponseDto updateUser(@PathVariable int userId){
		userService.deleteUser(userId);
		return new ResponseDto("User is deleted sucessfully with userId : "+userId, new Date(),HttpStatus.OK.name(),null);
	}
}
