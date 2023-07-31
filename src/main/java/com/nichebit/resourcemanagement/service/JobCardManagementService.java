package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.JobCardManagementRequest;
import com.nichebit.resourcemanagement.dto.JobCardManagementResponse;
import com.nichebit.resourcemanagement.entity.JobCardManagement;
import com.nichebit.resourcemanagement.repository.JobCardManagementRepository;
@Service
public class JobCardManagementService {

	
	@Autowired
	JobCardManagementRepository jobCardManagementRepository;
	
	public String addjobcardmanagent(JobCardManagementRequest jobCardManagementRequest)
	{
		JobCardManagement jobCardManagement=new JobCardManagement();
		
		jobCardManagement.setEmpId(jobCardManagementRequest.getEmpId());
		jobCardManagement.setProject(jobCardManagementRequest.getProject());
		jobCardManagement.setTask(jobCardManagementRequest.getTask());
		jobCardManagement.setClient(jobCardManagementRequest.getClient());
		jobCardManagement.setStartdate(jobCardManagementRequest.getStartdate());
		jobCardManagement.setEnddate(jobCardManagementRequest.getEnddate());
		jobCardManagement.setRemarks(jobCardManagementRequest.getRemarks());
		jobCardManagement.setAllocationpercentage(jobCardManagementRequest.getAllocationpercentage());
		jobCardManagement.setAllocationhours(jobCardManagementRequest.getAllocationhours());
		jobCardManagement.setCreatedby(jobCardManagementRequest.getCreatedby());
		jobCardManagement.setUpdatedby(jobCardManagementRequest.getUpdatedby());
		jobCardManagement.setCreatedon(jobCardManagementRequest.getCreatedon());
		jobCardManagement.setUpdationon(jobCardManagementRequest.getUpdationon());
		jobCardManagementRepository.save(jobCardManagement);
		return "JobCardManagement Is Created Sucessfully";
		
	}
	
	public String updatejobcardmanagent(JobCardManagementRequest jobCardManagementRequest)
	{
		JobCardManagement jobCardManagement=jobCardManagementRepository.findById(jobCardManagementRequest.getId()).orElse(null);
		jobCardManagement.setEmpId(jobCardManagementRequest.getEmpId());
		jobCardManagement.setProject(jobCardManagementRequest.getProject());
		jobCardManagement.setTask(jobCardManagementRequest.getTask());
		jobCardManagement.setClient(jobCardManagementRequest.getClient());
		jobCardManagement.setStartdate(jobCardManagementRequest.getStartdate());
		jobCardManagement.setEnddate(jobCardManagementRequest.getEnddate());
		jobCardManagement.setRemarks(jobCardManagementRequest.getRemarks());
		jobCardManagement.setAllocationpercentage(jobCardManagementRequest.getAllocationpercentage());
		jobCardManagement.setAllocationhours(jobCardManagementRequest.getAllocationhours());
		jobCardManagement.setCreatedby(jobCardManagementRequest.getCreatedby());
		jobCardManagement.setUpdatedby(jobCardManagementRequest.getUpdatedby());
		jobCardManagement.setCreatedon(jobCardManagementRequest.getCreatedon());
		jobCardManagement.setUpdationon(jobCardManagementRequest.getUpdationon());
		jobCardManagementRepository.save(jobCardManagement);
		return "JobCardManagement Is Updated Sucessfully";
	}
	
	
	public String deletejobcardmanagent(Long id)
	{
		jobCardManagementRepository.deleteById(id);
		return "delete JobCardManagement Sucessfully";
		
	}
	
	public List<JobCardManagementResponse> getJobCardManagents() {

		return jobCardManagementRepository.findAll().stream()
				.map(JobCardManagement -> new JobCardManagementResponse(JobCardManagement.getId(),JobCardManagement.getEmpId(),JobCardManagement.getProject(),
						JobCardManagement.getTask(),JobCardManagement.getClient(),JobCardManagement.getStartdate(),JobCardManagement.getEnddate(),
						JobCardManagement.getRemarks(),JobCardManagement.getAllocationpercentage(),JobCardManagement.getAllocationhours(),JobCardManagement.getCreatedby()
						,JobCardManagement.getUpdatedby(),JobCardManagement.getCreatedon(),JobCardManagement.getUpdationon())).toList();
	}
	
	
	
}
