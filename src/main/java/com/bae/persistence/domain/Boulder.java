package com.bae.persistence.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bae.util.Grade;
import com.bae.util.Status;

@Entity
@Table(name = "boulder")
public class Boulder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String location;
	private Grade grade;
	@Column(nullable = false)
	private Status status;
	private Date attemptDate;
	private Date completionDate;

	public Boulder() {
	}

	public Boulder(String name, String location, Grade grade, Status status) {
		this.name = name;
		this.location = location;
		this.grade = grade;
		this.status = status;
	}

	public Boulder(String name, String location, Grade grade, Status status, Date attemptDate) {
		this.name = name;
		this.location = location;
		this.grade = grade;
		this.status = status;
		this.attemptDate = attemptDate;
	}

	public Boulder(String name, String location, Grade grade, Status status, Date attemptDate, Date completionDate) {
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getLocation(), getGrade(), getStatus(), getAttemptDate(), getCompletionDate());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Boulder)) return false;
		Boulder boulder = (Boulder) o;
		return getId() == boulder.getId() &&
				getName().equals(boulder.getName()) &&
				getLocation().equals(boulder.getLocation()) &&
				getGrade() == boulder.getGrade() &&
				getStatus() == boulder.getStatus() &&
				Objects.equals(getAttemptDate(), boulder.getAttemptDate()) &&
				Objects.equals(getCompletionDate(), boulder.getCompletionDate());
	}

}
