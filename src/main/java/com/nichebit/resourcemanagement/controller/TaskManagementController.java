package com.nichebit.resourcemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.TaskManagementRequest;
import com.nichebit.resourcemanagement.dto.TaskManagementResponse;
import com.nichebit.resourcemanagement.service.TaskManagementService;
@RestController
@CrossOrigin("http://localhost:4200")
public class TaskManagementController {

	@Autowired
	TaskManagementService taskManagementService;
	
	
	@PostMapping("/addtaskmanagement")
	public String addTaskmanagement(@RequestBody TaskManagementRequest taskManagementRequest)
	{
		return taskManagementService.savetaskmanagement(taskManagementRequest);
	}
	
	@PutMapping("/updatetaskmanagement")
	public String updateTaskmanagement(@RequestBody TaskManagementRequest taskManagementRequest)
	{
		return taskManagementService.updatetaskmanagement(taskManagementRequest);
	}
	
	@DeleteMapping("/deletetaskmanagement/{id}")
	public String deleteTaskmanagement(@PathVariable Long id)
	{
		return taskManagementService.deletetaskmanagement(id);
	}
	
	@GetMapping("/taskmanagements")
	public List<TaskManagementResponse> gettaskmanagement()
	{
		return taskManagementService.gettaskmanagements();
	}
	
}
