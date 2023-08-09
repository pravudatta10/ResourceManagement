package com.nichebit.resourcemanagement.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.ProjectRequest;
import com.nichebit.resourcemanagement.dto.ProjectResponse;
import com.nichebit.resourcemanagement.dto.ProjectsnameResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.entity.Projects;
import com.nichebit.resourcemanagement.entity.TaskManagement;
import com.nichebit.resourcemanagement.repository.ProjectRepository;
import com.nichebit.resourcemanagement.repository.TaskManagementRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	TaskManagementRepository taskManagementRepository;

	ReturnResponse returnResponse = new ReturnResponse();

	public ResponseEntity<?> saveProject(ProjectRequest projectRequest) {
		Optional<Projects> projectName = projectRepository.findbyProjectName(projectRequest.getProjectname());
		if (!projectName.isEmpty()) {
			returnResponse.setStatus("Project already exists.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			Projects project = this.modelMapper.map(projectRequest, Projects.class);
			projectRepository.save(project);
			returnResponse.setStatus("Project Created Successfully");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public ResponseEntity<?> updateProject(ProjectRequest projectRequest) {
		Projects project = projectRepository.findById(projectRequest.getId()).orElse(null);
		if (null == project) {
			returnResponse.setStatus("Project Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
		project = this.modelMapper.map(projectRequest, Projects.class);
		projectRepository.save(project);
		returnResponse.setStatus("Project Updated Successfully");
		return ResponseEntity.ok(returnResponse);
	}

	public ResponseEntity<?> deleteProject(Long id) {
		List<TaskManagement> tm = taskManagementRepository.findtasksByPid(id);
		System.out.println("gullapalli" + tm.isEmpty());
		if (tm.isEmpty()) {
			Projects project = projectRepository.findById(id).orElse(null);
			if (null == project) {
				returnResponse.setStatus("Project Not Found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
			} else {
				projectRepository.deleteById(id);
				returnResponse.setStatus("Deleted Successfully");
				return ResponseEntity.ok(returnResponse);
			}
		}
		else {
			returnResponse.setStatus("This Project Having Some Tasks");
			System.out.println(returnResponse);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
	}

	public List<ProjectResponse> getProjects() {
		return projectRepository.findAll().stream()
				.map(project -> new ProjectResponse(project.getId(), project.getProjectname(), project.getClientname(),
						project.getFinancialyear(), project.getPonumber(), project.getPoamount(), project.getPostatus(),
						project.getPostatus(), project.getPoclearedpercentage(), project.getActualenddate(),
						project.getActualstartdate(), project.getCreationdate(), project.getPlanenddate(),
						project.getPlanstartdate(), project.getHoldfrom(), project.getResumefrom(),
						project.getDiscardedfrom(), project.getCreatedon(), project.getCreatedby(),
						project.getUpdatedon(), project.getUpdatedby()))
				.toList();
	}

	public List<ProjectsnameResponse> getDistinctProjects() {

		return projectRepository.findAllProjects().stream()
				.map(dtoForProjectResponse -> new ProjectsnameResponse(dtoForProjectResponse.getProjectname(),
						dtoForProjectResponse.getId()))
				.toList();
	}

}
