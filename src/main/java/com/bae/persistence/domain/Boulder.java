package com.bae.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "boulder")
public class Boulder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name; 
	private String location;
	private String grade;
	@Column(nullable=false)
	private String status;
	private Date attemptDate;
	private Date completionDate;
	

	public Boulder() {}
	
	public Boulder(String name, String location, String grade, String status) {
  		this.name = name;
  		this.location = location;
  		this.grade = grade;
  		this.status = status;
  	}
  	
  	public Boulder(String name, String location, String grade, String status, Date attemptDate) {
  		this.name = name;
  		this.location = location;
  		this.grade = grade;
  		this.status = status;
  		this.attemptDate = attemptDate;
  	}
  	
 	public Boulder(String name, String location, String grade, String status, Date attemptDate, Date completionDate) {
  		this.name = name;
  		this.location = location;
  		this.grade = grade;
  		this.status = status;
  		this.attemptDate = attemptDate;
  		this.completionDate = completionDate;
  	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAttemptDate() {
		return attemptDate;
	}

	public void setAttemptDate(Date attemptDate) {
		this.attemptDate = attemptDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}


  	


}
