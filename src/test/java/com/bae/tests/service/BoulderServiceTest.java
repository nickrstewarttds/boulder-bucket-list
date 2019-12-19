package com.bae.tests.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Boulder;
import com.bae.persistence.repo.BoulderRepo;
import com.bae.service.BoulderService;

@RunWith(SpringRunner.class)
public class BoulderServiceTest {

	@InjectMocks
	private BoulderService service;
	
	@Mock
	private BoulderRepo repo;
	
	private List<Boulder> boulderList;
	
	private Boulder testBoulder;
	
	private Boulder testBoulderWithID;
	
	private Date testAttemptDate;
	
	private Date testCompletionDate;
	
	final long id = 1L;
	
	@Before
	public void init() {
		this.testAttemptDate = new Date(2001-01-01);
		this.testCompletionDate = new Date(2001-01-01);
		this.boulderList = new ArrayList<>();
		this.boulderList.add(testBoulder);
		this.testBoulder = new Boulder("testName", "testLocation", "testGrade","testStatus",testAttemptDate,testCompletionDate);
		this.testBoulderWithID = new Boulder(testBoulder.getName(), testBoulder.getLocation(), testBoulder.getGrade(), testBoulder.getStatus(), 
												testBoulder.getAttemptDate(), testBoulder.getCompletionDate());
		this.testBoulderWithID.setId(id);
	}
	
	@Test
	public void addBoulderTest() {
		when(this.repo.save(testBoulder)).thenReturn(testBoulderWithID);
		
		assertEquals(this.testBoulderWithID, this.service.addBoulder(testBoulder));
		
		verify(this.repo, times(1)).save(this.testBoulder);
	}
	
	@Test
	public void deleteBoulderTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);
		
		this.service.deleteBoulder(id);
		
		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(1)).existsById(id);
	}
	
	@Test
	public void findBoulderByIdTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testBoulderWithID));
		
		assertEquals(this.testBoulderWithID, this.service.findBoulderById(this.id));
		
		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void updateBoulderTest() {
		Date newAttemptDate = new Date(2019-12-18);
		Date newCompletionDate = new Date(2019-12-18);
		Boulder newBoulder = new Boulder("Chris Rock", "Madagascar", "6A", "Completed", newAttemptDate, newCompletionDate);
		Boulder updatedBoulder = new Boulder(newBoulder.getName(), newBoulder.getLocation(),
												newBoulder.getGrade(), newBoulder.getStatus(),
												newBoulder.getAttemptDate(), newBoulder.getCompletionDate());
		updatedBoulder.setId(this.id);
		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testBoulderWithID));
		when(this.repo.save(updatedBoulder)).thenReturn(updatedBoulder);
		
		assertEquals(updatedBoulder, this.service.updateBoulder(newBoulder, this.id));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedBoulder); 
	}

}
