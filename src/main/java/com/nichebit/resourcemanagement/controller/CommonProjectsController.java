package com.nichebit.resourcemanagement.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.CommonProjectsRequest;
import com.nichebit.resourcemanagement.dto.CommonProjectsResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.service.CommonProjectsService;

@RestController
@RequestMapping("/commonprojects")
public class CommonProjectsController {
	
	@Autowired
	CommonProjectsService commonProjectsService;
	
	@PostMapping("/add")
	public ResponseEntity<ReturnResponse> addCommonProjects(@RequestBody CommonProjectsRequest commonProjectsRequest) throws IllegalArgumentException {
		return commonProjectsService.saveCommonProjects(commonProjectsRequest);
	}

	@PutMapping("/update")
	public ResponseEntity<ReturnResponse> updateCommonProjects(@RequestBody CommonProjectsRequest commonProjectsRequest)
	{
		return commonProjectsService.updateCommonProjects(commonProjectsRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReturnResponse> deleteCommonProjects(@PathVariable Long id)
	{
		return commonProjectsService.deleteCommonProject(id);
	}
	
	@GetMapping("/all")
	public List<CommonProjectsResponse> getHolidayMasters()
	{
		return commonProjectsService.getHolidayMaster();
	}
 
}
