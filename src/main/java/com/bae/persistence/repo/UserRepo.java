package com.bae.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.persistence.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
