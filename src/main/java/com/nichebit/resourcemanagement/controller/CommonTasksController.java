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

import com.nichebit.resourcemanagement.dto.CommonTasksRequest;
import com.nichebit.resourcemanagement.dto.CommonTasksResponse;
import com.nichebit.resourcemanagement.service.CommonTasksService;

@RestController
@RequestMapping("/commontasks")
public class CommonTasksController {

	@Autowired
	CommonTasksService commonTasksService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addCommonTasks(@RequestBody CommonTasksRequest commonTasksRequest){
		return commonTasksService.saveCommonTasks(commonTasksRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCommonTasks(@RequestBody CommonTasksRequest commonTasksRequest){
		return commonTasksService.updateCommonTasks(commonTasksRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCommonTasks(@PathVariable Long id)
	{
		return commonTasksService.deleteCommonTasks(id);
	}
	
	@GetMapping("/all")
	public List<CommonTasksResponse> getcommonTasks(){
		return commonTasksService.getcommonTasks();
	}


}
