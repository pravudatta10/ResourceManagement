package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.CommonProjectsRequest;
import com.nichebit.resourcemanagement.dto.CommonProjectsResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.entity.CommonProjects;
import com.nichebit.resourcemanagement.repository.CommonProjectsRepository;


@Service
public class CommonProjectsService {

	@Autowired
	CommonProjectsRepository commonProjectsRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
ReturnResponse returnResponse = new ReturnResponse();
	
	public ResponseEntity<?> saveCommonProjects(CommonProjectsRequest commonProjectsRequest) {
		CommonProjects commonProjects = this.modelMapper.map(commonProjectsRequest, CommonProjects.class);
		commonProjectsRepository.save(commonProjects);
		returnResponse.setStatus("CommonProjects Saved successfully.");
		return ResponseEntity.ok(returnResponse);
	}
	public ResponseEntity<?> updateCommonProjects(CommonProjectsRequest commonProjectsRequest) {
		CommonProjects commonProjects = commonProjectsRepository.findById(commonProjectsRequest.getId()).orElse(null);
		if (commonProjects == null) {
			returnResponse.setStatus("CommonProjects not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			commonProjects = this.modelMapper.map(commonProjectsRequest, CommonProjects.class);
			commonProjectsRepository.save(commonProjects);
			returnResponse.setStatus("CommonProjects updated successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public ResponseEntity<?> deleteCommonProject(Long id) {
		CommonProjects commonProjects = commonProjectsRepository.findById(id).orElse(null);
		if (commonProjects == null) {
			returnResponse.setStatus("CommonProjects  not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			commonProjectsRepository.deleteById(id);
			returnResponse.setStatus("CommonProjects deleted successfully.");
			return ResponseEntity.ok(returnResponse);
		}
	}
	public List<CommonProjectsResponse> getHolidayMaster() {
		return commonProjectsRepository.findAll().stream()
				.map(commonProjects -> new CommonProjectsResponse(commonProjects.getId(), commonProjects.getProjectname()))
				.toList();
	}

}
