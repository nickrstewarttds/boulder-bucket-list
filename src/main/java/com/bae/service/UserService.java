package com.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.persistence.domain.User;
import com.bae.persistence.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public User addUser(User newUser) {
		return userRepo.save(newUser);
	}
	
	public String deleteUser(Long userId) {
		userRepo.deleteById(userId);
		return "User deleted";
	}

}
