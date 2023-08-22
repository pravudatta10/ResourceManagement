package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.dto.TaskManagementRequest;
import com.nichebit.resourcemanagement.dto.TaskManagementResponse;
import com.nichebit.resourcemanagement.dto.TasksResponse;
import com.nichebit.resourcemanagement.entity.Projects;
import com.nichebit.resourcemanagement.entity.TaskManagement;
import com.nichebit.resourcemanagement.repository.TaskManagementRepository;

@Service
public class TaskManagementService {

	@Autowired
	TaskManagementRepository taskManagementRepository;

	@Autowired
	ModelMapper modelmapper;

	ReturnResponse returnResponse = new ReturnResponse();

	public ResponseEntity<ReturnResponse> savetaskmanagement(TaskManagementRequest taskManagementRequest) {

		Projects taskProjectid = new Projects();
		taskProjectid.setId(taskManagementRequest.getProjectid());
		TaskManagement taskmanagement = this.modelmapper.map(taskManagementRequest, TaskManagement.class);
		taskmanagement.setProject(taskProjectid);
		taskManagementRepository.save(taskmanagement);
		ReturnResponse res = new ReturnResponse();
		res.setStatus("Saved Sucessfully");
		return ResponseEntity.ok(res);

	}

	public ResponseEntity<ReturnResponse> updatetaskmanagement(TaskManagementRequest taskManagementRequest) {

		TaskManagement taskmanagement = taskManagementRepository.findById(taskManagementRequest.getId()).orElse(null);
		if (taskmanagement == null) {
			returnResponse.setStatus("Task Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			Projects taskProjectid = new Projects();
			taskProjectid.setId(taskManagementRequest.getProjectid());
			taskmanagement = this.modelmapper.map(taskManagementRequest, TaskManagement.class);
			taskmanagement.setProject(taskProjectid);
			taskManagementRepository.save(taskmanagement);
			returnResponse.setStatus("Updated Sucessfully");
			return ResponseEntity.ok(returnResponse);
		}
	}

	public ResponseEntity<ReturnResponse> deletetaskmanagement(long id) {
		TaskManagement taskmanagement = taskManagementRepository.findById(id).orElse(null);
		if (taskmanagement == null) {
			returnResponse.setStatus("Task Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			taskManagementRepository.deleteById(id);
			returnResponse.setStatus("Task Deleted Successfully");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public List<TaskManagementResponse> gettaskmanagements(long projectId) {
		return taskManagementRepository.findtasksByPid(projectId).stream()
				.map(taskManagement -> new TaskManagementResponse(taskManagement.getId(), taskManagement.getTask(),
						taskManagement.getTasktype(), taskManagement.getPlanstartdate(),
						taskManagement.getPlanenddate(), taskManagement.getActualstartdate(),
						taskManagement.getActualenddate(), taskManagement.getTaskstatus(), taskManagement.getHoldfrom(),
						taskManagement.getResumefrom(), taskManagement.getDiscardedfrom(),
						taskManagement.getCreatedby(), taskManagement.getUpdatedby(), taskManagement.getCreatedon(),
						taskManagement.getUpdationon(), taskManagement.getProject()))
				.toList();

	}

	public List<TasksResponse> gettasks(long projectId) {
		return taskManagementRepository.findByPid(projectId).stream()
				.map(taskManagement -> new TasksResponse(taskManagement)).toList();

	}

}
