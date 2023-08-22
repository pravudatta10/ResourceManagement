package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.MasterMangementRequest;
import com.nichebit.resourcemanagement.dto.MasterMangementResponse;
import com.nichebit.resourcemanagement.dto.MasterMangementResponseForType;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.entity.MasterMangement;
import com.nichebit.resourcemanagement.repository.MasterMangementRepository;

@Service
public class MasterMangementService {

	@Autowired
	MasterMangementRepository masterMangementRepository;

	@Autowired
	private ModelMapper modelMapper;

	ReturnResponse returnResponse = new ReturnResponse();

	public ResponseEntity<ReturnResponse> addMasterdata(MasterMangementRequest masterMangementRequest) {
		MasterMangement masterMangement = this.modelMapper.map(masterMangementRequest, MasterMangement.class);
		masterMangementRepository.save(masterMangement);
		returnResponse.setStatus("Master Data Saved Succesfully");
		return ResponseEntity.ok(returnResponse);
	}

	public ResponseEntity<ReturnResponse> updateMasterdata(MasterMangementRequest masterMangementRequest) {
		MasterMangement masterMangement = masterMangementRepository.findById(masterMangementRequest.getId())
				.orElse(null);
		if (masterMangement == null) {
			returnResponse.setStatus("Master Data not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			masterMangement = this.modelMapper.map(masterMangementRequest, MasterMangement.class);
			masterMangementRepository.save(masterMangement);
			returnResponse.setStatus("Master Data updated successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public ResponseEntity<ReturnResponse> deleteMasterData(Long id) {
		MasterMangement masterMangement = masterMangementRepository.findById(id).orElse(null);
		if (masterMangement == null) {
			returnResponse.setStatus("Master Data not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			masterMangementRepository.deleteById(id);
			returnResponse.setStatus("Master Data deleted successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public List<MasterMangementResponse> getallMasterData() {
		return masterMangementRepository.findAll().stream()
				.map(masterMangement -> new MasterMangementResponse(masterMangement.getId(),
						masterMangement.getLovId(), masterMangement.getLovDesc(), masterMangement.getType(),
						masterMangement.getStatus()))
				.toList();

	}

	public List<MasterMangementResponse> getbytype(String type) {
		return masterMangementRepository.findBytype(type).stream()
				.map(masterMangement -> new MasterMangementResponse(masterMangement.getId(),
						masterMangement.getLovId(), masterMangement.getLovDesc(), masterMangement.getType(),
						masterMangement.getStatus()))
				.toList();

	}

	public List<MasterMangementResponseForType> getDistinctTypes() {

		return masterMangementRepository.findDistinctTypes().stream()
				.map(masterMangement -> new MasterMangementResponseForType(masterMangement)).toList();
	}

}
