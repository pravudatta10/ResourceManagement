package com.nichebit.resourcemanagement.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.CommonTasksRequest;
import com.nichebit.resourcemanagement.dto.CommonTasksResponse;
import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.entity.CommonTasks;
import com.nichebit.resourcemanagement.entity.Employee;
import com.nichebit.resourcemanagement.repository.CommonTasksRepository;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;

@Service
public class CommonTasksService {

	@Autowired
	CommonTasksRepository commonTasksRepository;

	@Autowired
	private ModelMapper modelMapper;


	ReturnResponse returnResponse = new ReturnResponse();

	
	public ResponseEntity<ReturnResponse> saveCommonTasks(CommonTasksRequest commonTasksRequest){
		CommonTasks commonTasks = this.modelMapper.map(commonTasksRequest, CommonTasks.class);
		commonTasksRepository.save(commonTasks);
		returnResponse.setStatus("CommonTasks Saved successfully.");
		return ResponseEntity.ok(returnResponse);
	}
	
	
	public ResponseEntity<ReturnResponse> updateCommonTasks(CommonTasksRequest commonTasksRequest){
		
		CommonTasks commonTasks = commonTasksRepository.findById(commonTasksRequest.getId()).orElse(null);
		if (commonTasks == null) {
			returnResponse.setStatus("CommonTasks not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			commonTasks = this.modelMapper.map(commonTasksRequest, CommonTasks.class);
			commonTasksRepository.save(commonTasks);
			returnResponse.setStatus("CommonTasks updated successfully.");
			return ResponseEntity.ok(returnResponse);
		}
			

	}
	
	
	
	public ResponseEntity<ReturnResponse> deleteCommonTasks(Long id){
		Optional<CommonTasks> commonTasks = commonTasksRepository.findById(id);
		if (commonTasks .isEmpty()) {
			returnResponse.setStatus("CommonTasks  not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			commonTasksRepository.deleteById(id);
			returnResponse.setStatus("CommonTasks deleted successfully.");
			return ResponseEntity.ok(returnResponse);
		}
	}
	

	public List<CommonTasksResponse> getcommonTasks(){
		return commonTasksRepository.findAll().stream()
				.map(commonTasks -> new CommonTasksResponse(commonTasks.getId(),
				commonTasks.getTask(),commonTasks.getCommonprojectid()))
				.toList();
		
	}


	
	
}
