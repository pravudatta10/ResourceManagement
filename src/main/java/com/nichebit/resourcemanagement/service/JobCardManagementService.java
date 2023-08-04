package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.JobCardManagementRequest;
import com.nichebit.resourcemanagement.dto.JobCardManagementResponse;
import com.nichebit.resourcemanagement.entity.JobCardManagement;
import com.nichebit.resourcemanagement.repository.JobCardManagementRepository;

@Service
public class JobCardManagementService {

	@Autowired
	JobCardManagementRepository jobCardManagementRepository;

	@Autowired
	private ModelMapper modelMapper;

	public JobCardManagementResponse addjobcardmanagent(JobCardManagementRequest jobCardManagementRequest) {
		JobCardManagement jobCardManagement = this.modelMapper.map(jobCardManagementRequest, JobCardManagement.class);
		jobCardManagementRepository.save(jobCardManagement);
		return this.modelMapper.map(jobCardManagement, JobCardManagementResponse.class);

	}

	public ResponseEntity<?> updatejobcardmanagent(JobCardManagementRequest jobCardManagementRequest) {
		JobCardManagement jobCardManagement = jobCardManagementRepository.findById(jobCardManagementRequest.getId())
				.orElse(null);
		if (jobCardManagement == null) {
			return ResponseEntity.notFound().build();
		}
		jobCardManagement = this.modelMapper.map(jobCardManagementRequest, JobCardManagement.class);
		jobCardManagementRepository.save(jobCardManagement);
		return ResponseEntity.ok(jobCardManagement);
	}

	public String deletejobcardmanagent(Long id) {
		jobCardManagementRepository.deleteById(id);
		return "delete JobCardManagement Sucessfully";

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

}
