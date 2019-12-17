package com.bae.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.persistence.domain.User;
import com.bae.service.UserService;

@RestController
@RequestMapping("/userApp")
public class UserController {
	
	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	@PostMapping("/user")
	public User addUser(@RequestBody User newUser) {
		return service.addUser(newUser);
	}
	
	@DeleteMapping("/user/{userId}")
	public String deleteUser(@PathVariable(value="userId") Long userId) {
		return service.deleteUser(userId);
	}
	
	@PutMapping("/user/{userId}")
	public User updateUser(@RequestBody User userToUpdate, @PathVariable(value="userId") Long userId) {
		return service.updateUser(userToUpdate, userId);
	}

}
