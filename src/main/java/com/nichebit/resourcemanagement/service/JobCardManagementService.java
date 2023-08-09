package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.JobCardManagementRequest;
import com.nichebit.resourcemanagement.dto.JobCardManagementResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.entity.JobCardManagement;
import com.nichebit.resourcemanagement.repository.JobCardManagementRepository;

@Service
public class JobCardManagementService {

	@Autowired
	JobCardManagementRepository jobCardManagementRepository;

	@Autowired
	private ModelMapper modelMapper;

	ReturnResponse returnResponse = new ReturnResponse();

	public ResponseEntity<?> addjobcardmanagent(JobCardManagementRequest jobCardManagementRequest) {
		JobCardManagement jobCardManagement = this.modelMapper.map(jobCardManagementRequest, JobCardManagement.class);
		jobCardManagementRepository.save(jobCardManagement);
		returnResponse.setStatus("JobCard Saved successfully.");
		return ResponseEntity.ok(returnResponse);
	}

	public ResponseEntity<?> updatejobcardmanagent(JobCardManagementRequest jobCardManagementRequest) {
		JobCardManagement jobCardManagement = jobCardManagementRepository.findById(jobCardManagementRequest.getId())
				.orElse(null);
		if (jobCardManagement == null) {
			returnResponse.setStatus("JobCard Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
		jobCardManagement = this.modelMapper.map(jobCardManagementRequest, JobCardManagement.class);
		jobCardManagementRepository.save(jobCardManagement);
		returnResponse.setStatus("JobCard Updated successfully.");
		return ResponseEntity.ok(returnResponse);
	}

	public ResponseEntity<?> deletejobcardmanagent(Long id) {
		JobCardManagement jobCardManagement = jobCardManagementRepository.findById(id).orElse(null);
		if (jobCardManagement == null) {
			returnResponse.setStatus("JobCard Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
		jobCardManagementRepository.deleteById(id);
		returnResponse.setStatus("Delete JobCardManagement Sucessfully");
		return ResponseEntity.ok(returnResponse);
	}

	public List<JobCardManagementResponse> getJobCardManagents() {

		return jobCardManagementRepository.findAll().stream()
				.map(JobCardManagement -> new JobCardManagementResponse(JobCardManagement.getId(),
						JobCardManagement.getEmpId(), JobCardManagement.getProject(), JobCardManagement.getTask(),
						JobCardManagement.getClient(), JobCardManagement.getStartdate(), JobCardManagement.getEnddate(),
						JobCardManagement.getRemarks(), JobCardManagement.getAllocationpercentage(),
						JobCardManagement.getAllocationhours(), JobCardManagement.getCreatedby(),
						JobCardManagement.getUpdatedby(), JobCardManagement.getCreatedon(),
						JobCardManagement.getUpdationon()))
				.toList();
	}

	public List<JobCardManagementResponse> getJobCardbyEmpID(Long EMP_ID) {

		return jobCardManagementRepository.findbyEmpId(EMP_ID).stream()
				.map(JobCardManagement -> new JobCardManagementResponse(JobCardManagement.getId(),
						JobCardManagement.getEmpId(), JobCardManagement.getProject(), JobCardManagement.getTask(),
						JobCardManagement.getClient(), JobCardManagement.getStartdate(), JobCardManagement.getEnddate(),
						JobCardManagement.getRemarks(), JobCardManagement.getAllocationpercentage(),
						JobCardManagement.getAllocationhours(), JobCardManagement.getCreatedby(),
						JobCardManagement.getUpdatedby(), JobCardManagement.getCreatedon(),
						JobCardManagement.getUpdationon()))
				.toList();
	}
}
