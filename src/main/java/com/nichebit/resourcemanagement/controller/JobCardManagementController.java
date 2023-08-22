package com.nichebit.resourcemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.JobCardManagementRequest;
import com.nichebit.resourcemanagement.dto.JobCardManagementResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.service.JobCardManagementService;

@RestController

@RequestMapping("/jobcard")
public class JobCardManagementController {

	@Autowired
	JobCardManagementService jobCardManagementService;

	@PostMapping("/add")
	public List<ResponseEntity<ReturnResponse>> addjobCardManagement(
			@RequestBody List<JobCardManagementRequest> jobCardManagementRequest) {
		List<ResponseEntity<ReturnResponse>> jrs = new ArrayList<>();
		for (JobCardManagementRequest jre : jobCardManagementRequest) {
			ResponseEntity<ReturnResponse> managementResponse = jobCardManagementService.addjobcardmanagent(jre);
			jrs.add(managementResponse);
		}
		return jrs;

	}

	@PutMapping("/update")
	public ResponseEntity<ReturnResponse> updatejobCardManagement(@RequestBody JobCardManagementRequest jobCardManagementRequest) {
		return jobCardManagementService.updatejobcardmanagent(jobCardManagementRequest);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReturnResponse> deletejobCardManagement(@PathVariable Long id) {

		return jobCardManagementService.deletejobcardmanagent(id);
	}

	@GetMapping("/all")
	public List<JobCardManagementResponse> getjobCardManagements() {
		return jobCardManagementService.getJobCardManagents();

	}

	@GetMapping("/findbyempid")
	public  List<JobCardManagementResponse> getJobCardbyEmpID(@RequestParam("empid") long empId ) {
		return jobCardManagementService.getJobCardbyEmpID(empId);
	}

}
