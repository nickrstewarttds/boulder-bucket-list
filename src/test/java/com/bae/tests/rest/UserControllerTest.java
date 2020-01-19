package com.bae.tests.rest;

import com.bae.persistence.domain.User;
import com.bae.rest.UserController;
import com.bae.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    private List<User> userList;

    private User testUser;

    private User testUserWithID;

    final long userID = 1L;

    @Before
    public void init() {
        this.userList = new ArrayList<>();
        this.userList.add(testUser);
        this.testUser = new User("name");
        this.testUserWithID = new User(testUser.getName());
        this.testUserWithID.setId(userID);
    }

    @Test
    public void getAllUsersTest() {
        when(service.getAllUsers()).thenReturn(this.userList);

        assertEquals(this.userList, this.controller.getAllUsers());
    }

    @Test
    public void getUserByIDTest() {
        when(service.findUserById(userID)).thenReturn(this.testUserWithID);

        assertEquals(this.testUserWithID, this.controller.getUserByID(userID));
    }

    @Test
    public void addUserTest() {
        when(service.addUser(testUser)).thenReturn(testUserWithID);

        assertEquals(this.testUserWithID, this.controller.addUser(testUser));

        verify(this.service, times(1)).addUser(this.testUser);
    }

    @Test
    public void deleteUserTest() {
        this.controller.deleteUser(userID);

        verify(this.service, times(1)).deleteUser(userID);
    }

    @Test
    public void updateUserTest() {
        User newUser = new User("James");
        User updatedUser = new User(newUser.getName());
        updatedUser.setId(this.userID);

        when(this.service.updateUser(newUser,this.userID)).thenReturn(updatedUser);

        assertEquals(updatedUser, this.controller.updateUser(newUser, this.userID));

        verify(this.service, times(1)).updateUser(newUser,this.userID);
    }
}
