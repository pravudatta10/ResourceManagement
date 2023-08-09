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


	ReturnResponse returnResponse=new ReturnResponse();

	public ResponseEntity<?> savetaskmanagement(TaskManagementRequest taskManagementRequest) {

		Projects TaskProjectid = new Projects();
		TaskProjectid.setId(taskManagementRequest.getProjectid());
		TaskManagement taskmanagement = new TaskManagement();
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
		taskmanagement.setProject(TaskProjectid);
		taskManagementRepository.save(taskmanagement);

		ReturnResponse res = new ReturnResponse();
		res.setStatus("Saved Sucessfully");
		return ResponseEntity.ok(res);

	}

	public ResponseEntity<?> updatetaskmanagement(TaskManagementRequest taskManagementRequest) {

		TaskManagement taskmanagement = taskManagementRepository.findById(taskManagementRequest.getId()).orElse(null);
		if (taskmanagement == null) {
			returnResponse.setStatus("Task Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			Projects TaskProjectid = new Projects();
			TaskProjectid.setId(taskManagementRequest.getProjectid());
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
			taskmanagement.setProject(TaskProjectid);
			taskManagementRepository.save(taskmanagement);
			returnResponse.setStatus("Updated Sucessfully");
			return ResponseEntity.ok(returnResponse);
		}
	}

	public ResponseEntity<?> deletetaskmanagement(long id) {
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

	public List<TaskManagementResponse> gettaskmanagements(long Project_id) {
		return taskManagementRepository.findtasksByPid(Project_id).stream()
				.map(TaskManagement -> new TaskManagementResponse(TaskManagement.getId(), TaskManagement.getTask(),
						TaskManagement.getTasktype(), TaskManagement.getPlanstartdate(),
						TaskManagement.getPlanenddate(), TaskManagement.getActualstartdate(),
						TaskManagement.getActualenddate(), TaskManagement.getTaskstatus(), TaskManagement.getHoldfrom(),
						TaskManagement.getResumefrom(), TaskManagement.getDiscardedfrom(),
						TaskManagement.getCreatedby(), TaskManagement.getUpdatedby(), TaskManagement.getCreatedon(),
						TaskManagement.getUpdationon(), TaskManagement.getProject()))
				.toList();

	}

	public List<TasksResponse> gettasks(long Project_id) {
		return taskManagementRepository.findByPid(Project_id).stream()
				.map(TaskManagement -> new TasksResponse(TaskManagement)).toList();

	}

}
