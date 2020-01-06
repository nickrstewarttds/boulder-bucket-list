package com.bae.tests.domain;

import org.junit.Assert;
import org.junit.Test;

import com.bae.persistence.domain.User;

public class UserTest {
	
	
	private User testUser = new User("testUsername", "testPassword");

	
	@Test
	public void getUsernameTest() {
		Assert.assertEquals("testUsername",testUser.getUsername());
	}
	
	@Test
	public void getPasswordTest() {
		Assert.assertEquals("testPassword",testUser.getPassword());
	}
}
