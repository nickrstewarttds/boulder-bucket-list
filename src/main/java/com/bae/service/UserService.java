package com.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.exceptions.UserNotFoundException;
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
		if (!userRepo.existsById(userId)) {
			throw new UserNotFoundException();
		}
		userRepo.deleteById(userId);
		return "User deleted";
	}
	
	public User findUserById(Long userId) {
		return userRepo.findById(userId).orElseThrow(
				() -> new UserNotFoundException());
	}
	
	public User updateUser(User user, Long id) {
		User toUpdate = findUserById(id);
		toUpdate.setUsername(user.getUsername());
		toUpdate.setPassword(user.getPassword());
		toUpdate.setBoulders(user.getBoulders());
		return this.userRepo.save(toUpdate);
	}

}
