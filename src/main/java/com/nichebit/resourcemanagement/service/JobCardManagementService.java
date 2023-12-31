package com.nichebit.resourcemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.JobCardManagementRequest;
import com.nichebit.resourcemanagement.dto.JobCardManagementResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.dto.TasksResponse;
import com.nichebit.resourcemanagement.entity.JobCardManagement;
import com.nichebit.resourcemanagement.entity.Projects;
import com.nichebit.resourcemanagement.repository.JobCardManagementRepository;
import com.nichebit.resourcemanagement.repository.ProjectRepository;
import com.nichebit.resourcemanagement.repository.TaskManagementRepository;

@Service
public class JobCardManagementService {

	@Autowired
	JobCardManagementRepository jobCardManagementRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TaskManagementRepository taskManagementRepository;

	ReturnResponse returnResponse = new ReturnResponse();

	public JobCardManagementService(JobCardManagementRepository repository, ModelMapper modelMapper2) {
		this.jobCardManagementRepository = repository;
		this.modelMapper = modelMapper2;
	}

	public ResponseEntity<ReturnResponse> addjobcardmanagent(JobCardManagementRequest jobCardManagementRequest) {
		JobCardManagement jobCardManagement = this.modelMapper.map(jobCardManagementRequest, JobCardManagement.class);
		jobCardManagementRepository.save(jobCardManagement);
		returnResponse.setStatus("JobCard Saved successfully.");
		return ResponseEntity.ok(returnResponse);
	}

	public ResponseEntity<ReturnResponse> updatejobcardmanagent(JobCardManagementRequest jobCardManagementRequest) {
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

	public ResponseEntity<ReturnResponse> deletejobcardmanagent(Long id) {
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

		List<JobCardManagementResponse> jcr = new ArrayList<>();
		List<JobCardManagement> jobcardmanagements = jobCardManagementRepository.findAll();
		for (JobCardManagement jobcardmanagement : jobcardmanagements) {
			Long projectId = projectRepository.findProjectIdByName(jobcardmanagement.getProject());
			List<String> tasks = taskManagementRepository.findByPid(projectId);
			List<TasksResponse> tr = new ArrayList<>();
			for (String task : tasks) {
				TasksResponse trs = new TasksResponse();
				trs.setTask(task);
				tr.add(trs);

			}

			JobCardManagementResponse jcm = new JobCardManagementResponse(jobcardmanagement.getId(),
					jobcardmanagement.getEmpId(), jobcardmanagement.getProject(), jobcardmanagement.getTask(),
					jobcardmanagement.getClient(), jobcardmanagement.getStartdate(), jobcardmanagement.getEnddate(),
					jobcardmanagement.getRemarks(), jobcardmanagement.getAllocationpercentage(),
					jobcardmanagement.getAllocationhours(), jobcardmanagement.getCreatedby(),
					jobcardmanagement.getUpdatedby(), jobcardmanagement.getCreatedon(),
					jobcardmanagement.getUpdationon(), tr);
			jcr.add(jcm);

		}
		return jcr;
	}

	public List<JobCardManagementResponse> getJobCardbyEmpID(long empId) {

		List<JobCardManagementResponse> jcr = new ArrayList<>();
		List<JobCardManagement> jobcardmanagements = jobCardManagementRepository.findbyEmpId(empId);
		for (JobCardManagement jobcardmanagement : jobcardmanagements) {
			Long projectId = projectRepository.findProjectIdByName(jobcardmanagement.getProject());
			List<String> tasks = taskManagementRepository.findByPid(projectId);
			List<TasksResponse> tr = new ArrayList<>();
			for (String task : tasks) {
				TasksResponse trs = new TasksResponse();
				trs.setTask(task);
				tr.add(trs);

			}

			JobCardManagementResponse jcm = new JobCardManagementResponse(jobcardmanagement.getId(),
					jobcardmanagement.getEmpId(), jobcardmanagement.getProject(), jobcardmanagement.getTask(),
					jobcardmanagement.getClient(), jobcardmanagement.getStartdate(), jobcardmanagement.getEnddate(),
					jobcardmanagement.getRemarks(), jobcardmanagement.getAllocationpercentage(),
					jobcardmanagement.getAllocationhours(), jobcardmanagement.getCreatedby(),
					jobcardmanagement.getUpdatedby(), jobcardmanagement.getCreatedon(),
					jobcardmanagement.getUpdationon(), tr);
			jcr.add(jcm);

		}

		return jcr;
	}

}
