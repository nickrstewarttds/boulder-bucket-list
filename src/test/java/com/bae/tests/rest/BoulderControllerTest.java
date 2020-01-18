package com.bae.tests.rest;

import com.bae.persistence.domain.Boulder;
import com.bae.rest.BoulderController;
import com.bae.service.BoulderService;
import com.bae.util.Grade;
import com.bae.util.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BoulderControllerTest {

    @InjectMocks
    private BoulderController controller;

    @Mock
    private BoulderService service;

    private List<Boulder> boulderList;

    private Boulder testBoulder;

    private Boulder testBoulderWithID;

    final long boulderID = 1L;

    @Before
    public void init() {
        this.boulderList = new ArrayList<>();
        this.boulderList.add(testBoulder);
        this.testBoulder = new Boulder("name","location", Grade._5A, Status.NOT_ATTEMPTED);
        this.testBoulderWithID = new Boulder(testBoulder.getName(),testBoulder.getLocation(),testBoulder.getGrade(),testBoulder.getStatus());
        this.testBoulderWithID.setId(boulderID);
    }

    @Test
    public void getAllBouldersTest() {
        when(service.getAllBoulders()).thenReturn(this.boulderList);

        assertEquals(this.boulderList, this.controller.getAllBoulders());
    }

    @Test
    public void addBoulderTest() {
        when(service.addBoulder(testBoulder)).thenReturn(testBoulderWithID);

        assertEquals(this.testBoulderWithID, this.controller.addBoulder(testBoulder));

        verify(this.service, times(1)).addBoulder(this.testBoulder);
    }

    @Test
    public void deleteBoulderTest() {
        this.controller.deleteBoulder(boulderID);

        verify(this.service, times(1)).deleteBoulder(boulderID);
    }

    @Test
    public void updateBoulderTest() {
        Boulder newBoulder = new Boulder("newName","newLocation",Grade._6A,Status.ATTEMPTED,new Date(2020- 1 - 1));
        Boulder updatedBoulder = new Boulder(newBoulder.getName(),newBoulder.getLocation(),newBoulder.getGrade(),newBoulder.getStatus(),newBoulder.getAttemptDate());
        updatedBoulder.setId(this.boulderID);

        when(this.service.updateBoulder(newBoulder, this.boulderID)).thenReturn(updatedBoulder);

        assertEquals(updatedBoulder, this.controller.updateBoulder(newBoulder, this.boulderID));

        verify(this.service, times(1)).updateBoulder(newBoulder, this.boulderID);
    }

}
