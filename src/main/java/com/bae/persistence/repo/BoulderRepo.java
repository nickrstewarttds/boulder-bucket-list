package com.bae.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.persistence.domain.Boulder;

public interface BoulderRepo extends JpaRepository<Boulder, Long> {

}
