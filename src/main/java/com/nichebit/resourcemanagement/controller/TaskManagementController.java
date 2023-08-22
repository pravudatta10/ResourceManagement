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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.dto.TaskManagementRequest;
import com.nichebit.resourcemanagement.dto.TaskManagementResponse;
import com.nichebit.resourcemanagement.dto.TasksResponse;
import com.nichebit.resourcemanagement.service.TaskManagementService;

@RestController
@RequestMapping("/task")
public class TaskManagementController {

	@Autowired
	TaskManagementService taskManagementService;

	@PostMapping("/add")
	public ResponseEntity<ReturnResponse> addTaskmanagement(@RequestBody TaskManagementRequest taskManagementRequest) {
		return taskManagementService.savetaskmanagement(taskManagementRequest);
	}

	@PutMapping("/update")
	public ResponseEntity<ReturnResponse> updateTaskmanagement(@RequestBody TaskManagementRequest taskManagementRequest) {
		return taskManagementService.updatetaskmanagement(taskManagementRequest);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReturnResponse> deleteTaskmanagement(@PathVariable Long id) {
		return taskManagementService.deletetaskmanagement(id);
	}

	@GetMapping("/all")
	public List<TaskManagementResponse> gettaskmanagementforproject(@RequestParam("Project_id")long projectId) {
		return taskManagementService.gettaskmanagements(projectId);
	}

	@GetMapping("/tasks")
	public List<TasksResponse> gettaskmanagement(@RequestParam("Project_id") long projectId) {
		return taskManagementService.gettasks(projectId);
	}

}
