package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.TaskManagementRequest;
import com.nichebit.resourcemanagement.dto.TaskManagementResponse;
import com.nichebit.resourcemanagement.entity.TaskManagement;
import com.nichebit.resourcemanagement.repository.TaskManagementRepository;
@Service
public class TaskManagementService {

	@Autowired
	TaskManagementRepository taskManagementRepository;
	
	public String savetaskmanagement(TaskManagementRequest taskManagementRequest)
	{
		TaskManagement taskmanagement=new TaskManagement();
		taskmanagement.setTask(taskManagementRequest.getTask());
		taskmanagement.setTasktype(taskManagementRequest.getTasktype());
		taskmanagement.setPlanstartdate(taskManagementRequest.getPlanstartdate());
		taskmanagement.setPlanenddate(taskManagementRequest.getPlanenddate());
		taskmanagement.setActualstartdate(taskManagementRequest.getActualstartdate());
		taskmanagement.setActualenddate(taskManagementRequest.getActualenddate());
		taskmanagement.setTaskstatus(taskManagementRequest.getTaskstatus());
		taskmanagement.setHoldfrom(taskManagementRequest.getHoldfrom());
		taskmanagement.setResumefrom(taskManagementRequest.getResumefrom());
		taskmanagement.setDiscardedfrom(taskManagementRequest.getDiscardedfrom());
		taskmanagement.setCreatedby(taskManagementRequest.getCreatedby());
		taskmanagement.setUpdatedby(taskManagementRequest.getUpdatedby());
		taskmanagement.setCreatedon(taskManagementRequest.getCreatedon());
		taskmanagement.setUpdationon(taskManagementRequest.getUpdationon());
		taskmanagement.setProjectid(taskManagementRequest.getProjectid());
		taskManagementRepository.save(taskmanagement);
		return "Task Saved Successfully";
		
	}
	
	
	public String updatetaskmanagement(TaskManagementRequest taskManagementRequest)
	{
		TaskManagement taskmanagement=taskManagementRepository.findById(taskManagementRequest.getId()).orElse(null);
		taskmanagement.setTask(taskManagementRequest.getTask());
		taskmanagement.setTasktype(taskManagementRequest.getTasktype());
		taskmanagement.setPlanstartdate(taskManagementRequest.getPlanstartdate());
		taskmanagement.setPlanenddate(taskManagementRequest.getPlanenddate());
		taskmanagement.setActualstartdate(taskManagementRequest.getActualstartdate());
		taskmanagement.setActualenddate(taskManagementRequest.getActualenddate());
		taskmanagement.setTaskstatus(taskManagementRequest.getTaskstatus());
		taskmanagement.setHoldfrom(taskManagementRequest.getHoldfrom());
		taskmanagement.setResumefrom(taskManagementRequest.getResumefrom());
		taskmanagement.setDiscardedfrom(taskManagementRequest.getDiscardedfrom());
		taskmanagement.setCreatedby(taskManagementRequest.getCreatedby());
		taskmanagement.setUpdatedby(taskManagementRequest.getUpdatedby());
		taskmanagement.setCreatedon(taskManagementRequest.getCreatedon());
		taskmanagement.setUpdationon(taskManagementRequest.getUpdationon());
		taskmanagement.setProjectid(taskManagementRequest.getProjectid());
		taskManagementRepository.save(taskmanagement);
		return "Task Updated Successfully";
		
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
						TaskManagement.getProjectid())).toList();
				
	}
	
	
	
}
