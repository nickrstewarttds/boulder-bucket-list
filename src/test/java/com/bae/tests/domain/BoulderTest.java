package com.bae.tests.domain;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.bae.persistence.domain.Boulder;
import com.bae.util.Grade;
import com.bae.util.Status;

public class BoulderTest {

	private Date testAttemptDate = new Date(2001 - 01 - 01);
	private Date testCompletionDate = new Date(2001 - 01 - 01);
	private Boulder testBoulder = new Boulder("testName", "testLocation", Grade._5A, Status.ATTEMPTED, testAttemptDate,
			testCompletionDate);

	@Test
	public void getNameTest() {
		Assert.assertEquals("testName", testBoulder.getName());
	}

	@Test
	public void getLocationTest() {
		Assert.assertEquals("testLocation", testBoulder.getLocation());
	}

	@Test
	public void getGradeTest() {
		Assert.assertEquals(Grade._5A, testBoulder.getGrade());
	}

	@Test
	public void getStatusTest() {
		Assert.assertEquals(Status.ATTEMPTED, testBoulder.getStatus());
	}

	@Test
	public void getAttemptDateTest() {
		Assert.assertEquals(testAttemptDate, testBoulder.getAttemptDate());
	}

	@Test
	public void getCompletionDateTest() {
		Assert.assertEquals(testCompletionDate, testBoulder.getCompletionDate());
	}

}
