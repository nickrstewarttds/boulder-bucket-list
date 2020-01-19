package com.bae.persistence.domain;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Set<Boulder> boulders;

	public User() {
		super();
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Boulder> getBoulders() {
		return boulders;
	}

	public void setBoulders(Set<Boulder> boulders) {
		this.boulders = boulders;
	}

	public String getName() {
		return name;
	}

	public void setName(String username) {
		this.name = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, boulders);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return getId() == user.getId() &&
				getName().equals(user.getName()) &&
				Objects.equals(getBoulders(), user.getBoulders());
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", boulders=" + boulders + ", name=" + name + "]";
	}

}
