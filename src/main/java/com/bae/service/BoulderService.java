package com.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.persistence.domain.Boulder;
import com.bae.persistence.repo.BoulderRepo;

@Service
public class BoulderService {
	
	@Autowired
	private BoulderRepo boulderRepo;
	
	public BoulderService(BoulderRepo boulderRepo) {
		this.boulderRepo = boulderRepo;
	}
	
	public List<Boulder> getAllBoulders() {
		return boulderRepo.findAll();
	}
	
	public Boulder addBoulder(Boulder newBoulder) {
		return boulderRepo.save(newBoulder);
	}
	
	public String deleteBoulder(Long boulderId) {
		boulderRepo.deleteById(boulderId);
		return "Boulder deleted";
	}
	
	public Boulder updateBoulder(Boulder boulderToUpdate) {
		return boulderRepo.save(boulderToUpdate);
	}

}
