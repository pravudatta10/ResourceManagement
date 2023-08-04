package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.MasterMangementRequest;
import com.nichebit.resourcemanagement.dto.MasterMangementResponse;
import com.nichebit.resourcemanagement.dto.MasterMangementResponseForType;
import com.nichebit.resourcemanagement.entity.MasterMangement;
import com.nichebit.resourcemanagement.repository.MasterMangementRepository;

@Service
public class MasterMangementService {

	@Autowired
	MasterMangementRepository masterMangementRepository;

	@Autowired
	private ModelMapper modelMapper;

	public MasterMangementResponse addMasterdata(MasterMangementRequest masterMangementRequest) {
		MasterMangement masterMangement = this.modelMapper.map(masterMangementRequest, MasterMangement.class);
		masterMangementRepository.save(masterMangement);
		return this.modelMapper.map(masterMangement, MasterMangementResponse.class);
	}

	public ResponseEntity<?> updateMasterdata(MasterMangementRequest masterMangementRequest) {
		MasterMangement masterMangement = masterMangementRepository.findById(masterMangementRequest.getId())
				.orElse(null);
		if (masterMangement == null) {
			return ResponseEntity.notFound().build();
		}
		masterMangement = this.modelMapper.map(masterMangementRequest, MasterMangement.class);
		masterMangementRepository.save(masterMangement);
		return ResponseEntity.ok(masterMangement);
	}

	public String deleteMasterData(Long id) {
		masterMangementRepository.deleteById(id);
		return "Master Data Deleted Successfully";
	}

	public List<MasterMangementResponse> getallMasterData() {
		return masterMangementRepository.findAll().stream()
				.map(MasterMangement -> new MasterMangementResponse(MasterMangement.getId(),
						MasterMangement.getLov_id(), MasterMangement.getLov_desc(), MasterMangement.getType(),
						MasterMangement.getStatus()))
				.toList();

	}

	public List<MasterMangementResponse> getbytype(String type) {
		return masterMangementRepository.findBytype(type).stream()
				.map(MasterMangement -> new MasterMangementResponse(MasterMangement.getId(),
						MasterMangement.getLov_id(), MasterMangement.getLov_desc(), MasterMangement.getType(),
						MasterMangement.getStatus()))
				.toList();

	}

	public List<MasterMangementResponseForType> getDistinctTypes() {

		return masterMangementRepository.findDistinctTypes().stream()
				.map(MasterMangement -> new MasterMangementResponseForType(MasterMangement)).toList();
	}

}
