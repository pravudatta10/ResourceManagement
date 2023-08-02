package com.nichebit.resourcemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.MasterMangementRequest;
import com.nichebit.resourcemanagement.dto.MasterMangementResponse;
import com.nichebit.resourcemanagement.entity.MasterMangement;
import com.nichebit.resourcemanagement.repository.MasterMangementRepository;

@Service
public class MasterMangementService {

	@Autowired
	MasterMangementRepository masterMangementRepository;

	public String addMasterdata(MasterMangementRequest masterMangementRequest) {
		MasterMangement masterMangement = new MasterMangement();
		masterMangement.setLov_id(masterMangementRequest.getLov_id());
		masterMangement.setLov_desc(masterMangementRequest.getLov_desc());
		masterMangement.setType(masterMangementRequest.getType());
		masterMangement.setStatus(masterMangementRequest.getStatus());
		masterMangementRepository.save(masterMangement);
		return "Added Master Data Successfully";
	}

	public String updateMasterdata(MasterMangementRequest masterMangementRequest) {
		MasterMangement masterMangement = masterMangementRepository.findById(masterMangementRequest.getId())
				.orElse(null);
		masterMangement.setLov_id(masterMangementRequest.getLov_id());
		masterMangement.setLov_desc(masterMangementRequest.getLov_desc());
		masterMangement.setType(masterMangementRequest.getType());
		masterMangement.setStatus(masterMangementRequest.getStatus());
		masterMangementRepository.save(masterMangement);
		return " Master Data updated Successfully";
	}

	public String deleteMasterData(Long id) {
		masterMangementRepository.deleteById(id);
		return "Master Data Deleted Successfully";
	}

	public List<MasterMangementResponse> getallMasterData() {
		return masterMangementRepository.findAll().stream()
				.map(MasterMangement -> new MasterMangementResponse(MasterMangement.getId(),MasterMangement.getLov_id(),
						MasterMangement.getLov_desc(), MasterMangement.getType(), MasterMangement.getStatus())).toList();

	}
	
	
	
	public Optional<MasterMangement> getbytype(String type)
	{
		
		return masterMangementRepository.findBytype(type);
		
	}
	
	
	
}
