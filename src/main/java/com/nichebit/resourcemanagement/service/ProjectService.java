package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.ProjectRequest;
import com.nichebit.resourcemanagement.dto.ProjectResponse;
import com.nichebit.resourcemanagement.dto.ProjectsnameResponse;
import com.nichebit.resourcemanagement.entity.Projects;
import com.nichebit.resourcemanagement.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	public ProjectResponse saveProject(ProjectRequest projectRequest) {
		Projects project = this.modelMapper.map(projectRequest, Projects.class);		 
		projectRepository.save(project);
		return this.modelMapper.map(project, ProjectResponse.class);
	}

	public ResponseEntity<?> updateProject(ProjectRequest projectRequest) {
		Projects project = projectRepository.findById(projectRequest.getId()).orElse(null);
		if(null == project) {
			return ResponseEntity.notFound().build();
		} 
		project=this.modelMapper.map(projectRequest, Projects.class);
		projectRepository.save(project);
		return  ResponseEntity.ok(project);
	}

	public ResponseEntity deleteProject(Long id) {
		Projects project = projectRepository.findById(id).orElse(null);
		if(null == project) {
			return ResponseEntity.notFound().build();
		} 
		else {
			projectRepository.deleteById(id);
			return ResponseEntity.ok("Deleted Successfully");
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
	
	public List<ProjectsnameResponse> getDistinctProjects()
	{
		return projectRepository.findAllProjects().stream()
				.map(project -> new ProjectsnameResponse(project)).toList();			
	}
	
	
}


