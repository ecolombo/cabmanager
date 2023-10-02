package com.edesign.cabmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edesign.cabmanager.repository.UserRepository;
import com.edesign.cabmanager.exception.BadRequestException;
import com.edesign.cabmanager.exception.NotFoundException;
import com.edesign.cabmanager.dto.LoginRequestDto;
import com.edesign.cabmanager.entity.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	BCryptPasswordEncoder passwordEncoder ;
	
	
	// Get all Users
	public Page<User> getUsers(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return userRepository.findAll(pageable);
	}	
	
	
	// Get one User by UserId
	public User getUser(int UserId) {
		return userRepository.findById(UserId).get();
	}
	
	// Add User
	public User addUser(User user) {
		if(userRepository.existsByEmail(user.getEmail()))
			throw new BadRequestException("User with this email address already exists.");
		passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	// Update User
	public User updateUser(User user) {
		if(user.getUserId() <=0 )
			throw new BadRequestException("UserId cannot be null or empty.");
		passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(userRepository.existsById(user.getUserId()))
			return userRepository.save(user);
		else 
			throw new NotFoundException("User does not exist with provided userId.");

	}
	
	// Delete User
	public void deleteUser(int userId) {
		if(userId <=0 )
			throw new BadRequestException("userId id cannot be null or empty.");
		if(userRepository.existsById(userId))
			userRepository.deleteById(userId);
		else 
			throw new NotFoundException("The user does not exist with provided userId.");
	}
	
	public User validateLogin(LoginRequestDto loginDto) {
		boolean exist = userRepository.existsByEmail(loginDto.getEmail()) ;
		if(exist) {
			User user = userRepository.findByEmail(loginDto.getEmail());
			passwordEncoder = new BCryptPasswordEncoder();
			if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
				return user;
			} else {
				throw new NotFoundException("Invalid password, Password mismatch error.");
			}
		} else {
			throw new NotFoundException("User does not exist.");
		}
	}	

}
