package com.bae.tests.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.User;
import com.bae.persistence.repo.UserRepo;
import com.bae.service.UserService;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepo repo;
	
	private List<User> userList;
	
	private User testUser;
	
	private User testUserWithID;
	
	final long id = 1L;
	
	@Before
	public void init() {
		this.userList = new ArrayList<>();
		this.userList.add(testUser);
		this.testUser = new User("James","password");
		this.testUserWithID = new User(testUser.getUsername(),testUser.getPassword());
		this.testUserWithID.setId(id);
	}
	
	@Test
	public void addUserTest() {
		when(this.repo.save(testUser)).thenReturn(testUserWithID);
		
		assertEquals(this.testUserWithID, this.service.addUser(testUser));
		
		verify(this.repo, times(1)).save(this.testUser);
	}
	
	@Test
	public void deleteUserTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);
		
		this.service.deleteUser(id);
		
		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(1)).existsById(id);
	}
	
	@Test
	public void findUserByIdTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testUserWithID));
		
		assertEquals(this.testUserWithID, this.service.findUserById(this.id));
		
		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void updateUserTest() {
		User newUser = new User("Luke", "LukesPassword");
		User updatedUser = new User(newUser.getUsername(),newUser.getPassword());
		updatedUser.setId(this.id);
		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testUserWithID));
		when(this.repo.save(updatedUser)).thenReturn(updatedUser);
		
		assertEquals(updatedUser, this.service.updateUser(newUser, this.id));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedUser);
	}

}
