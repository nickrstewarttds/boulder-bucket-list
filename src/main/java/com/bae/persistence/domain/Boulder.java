package com.bae.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bae.service.Status;

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
	private Status status;
	private Date attemptDate;
	private Date completionDate;
	

	public Boulder() {}
	
	public Boulder(String name, String location, String grade, Status status) {
  		this.name = name;
  		this.location = location;
  		this.grade = grade;
  		this.status = status;
  	}
  	
  	public Boulder(String name, String location, String grade, Status status, Date attemptDate) {
  		this.name = name;
  		this.location = location;
  		this.grade = grade;
  		this.status = status;
  		this.attemptDate = attemptDate;
  	}
  	
 	public Boulder(String name, String location, String grade, Status status, Date attemptDate, Date completionDate) {
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attemptDate == null) ? 0 : attemptDate.hashCode());
		result = prime * result + ((completionDate == null) ? 0 : completionDate.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boulder other = (Boulder) obj;
		if (attemptDate == null) {
			if (other.attemptDate != null)
				return false;
		} else if (!attemptDate.equals(other.attemptDate))
			return false;
		if (completionDate == null) {
			if (other.completionDate != null)
				return false;
		} else if (!completionDate.equals(other.completionDate))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}


  	


}
