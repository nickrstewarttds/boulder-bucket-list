package com.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.exceptions.BoulderNotFoundException;
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
		if (!boulderRepo.existsById(boulderId)) {
			throw new BoulderNotFoundException();
		}
		boulderRepo.deleteById(boulderId);
		return "Boulder deleted";
	}
	
	public Boulder findBoulderById(Long boulderId) {
		return boulderRepo.findById(boulderId).orElseThrow(
				() -> new BoulderNotFoundException());
	}
	
	public Boulder updateBoulder(Boulder boulder, Long id) {
		Boulder toUpdate = findBoulderById(id);
		toUpdate.setName(boulder.getName());
		toUpdate.setLocation(boulder.getLocation());
		toUpdate.setGrade(boulder.getGrade());
		toUpdate.setStatus(boulder.getStatus());
		toUpdate.setAttemptDate(boulder.getAttemptDate());
		toUpdate.setCompletionDate(boulder.getCompletionDate());
		return boulderRepo.save(toUpdate);
	}

}
