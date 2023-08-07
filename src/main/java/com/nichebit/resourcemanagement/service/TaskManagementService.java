package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.TaskManagementRequest;
import com.nichebit.resourcemanagement.dto.TaskManagementResponse;
import com.nichebit.resourcemanagement.dto.TasksResponse;
import com.nichebit.resourcemanagement.entity.TaskManagement;
import com.nichebit.resourcemanagement.repository.TaskManagementRepository;
@Service
public class TaskManagementService {

	@Autowired
	TaskManagementRepository taskManagementRepository;
	
	@Autowired
	 ModelMapper modelmapper;
	
	public TaskManagementResponse savetaskmanagement(TaskManagementRequest taskManagementRequest)
	{
		TaskManagement taskmanagement=this.modelmapper.map(taskManagementRequest, TaskManagement.class);
		
		taskManagementRepository.save(taskmanagement);
		return this.modelmapper.map(taskmanagement, TaskManagementResponse.class);
		
	}
	
	
	public ResponseEntity<?> updatetaskmanagement(TaskManagementRequest taskManagementRequest)
	{
		TaskManagement taskmanagement=taskManagementRepository.findById(taskManagementRequest.getId()).orElse(null);
		if(taskmanagement == null)
		{
			return ResponseEntity.notFound().build();
		}
		
		taskmanagement=this.modelmapper.map(taskManagementRequest,TaskManagement.class); 
		
		taskManagementRepository.save(taskmanagement);
		
		TaskManagementResponse taskManagementResponse=this.modelmapper.map(taskmanagement,TaskManagementResponse.class); 
		return  ResponseEntity.ok(taskManagementResponse);
		
	}
	public String deletetaskmanagement(long  id)
	{
		taskManagementRepository.deleteById(id);
		return "Task Deleted Successfully";
	}
	public List<TaskManagementResponse> gettaskmanagements() {
		return taskManagementRepository.findAll().stream()
				.map(TaskManagement -> new TaskManagementResponse(TaskManagement.getId(),TaskManagement.getTask(),TaskManagement.getTasktype(),
						TaskManagement.getPlanstartdate(),TaskManagement.getPlanenddate(),TaskManagement.getActualstartdate(),
						TaskManagement.getActualenddate(),TaskManagement.getTaskstatus(),TaskManagement.getHoldfrom(),
						TaskManagement.getResumefrom(),TaskManagement.getDiscardedfrom(),TaskManagement.getCreatedby(),
						TaskManagement.getUpdatedby(),TaskManagement.getCreatedon(),TaskManagement.getUpdationon(),
						TaskManagement.getProject())).toList();
				
	}
	
	
	public  List<TasksResponse> gettasks(long Project_id) {
		return taskManagementRepository.findByPid(Project_id).stream()
				.map(TaskManagement ->new TasksResponse(TaskManagement)).toList();
				
	}
	
	
}
