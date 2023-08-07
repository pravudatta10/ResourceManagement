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

import com.nichebit.resourcemanagement.dto.ProjectRequest;
import com.nichebit.resourcemanagement.dto.ProjectResponse;
import com.nichebit.resourcemanagement.dto.ProjectsnameResponse;
import com.nichebit.resourcemanagement.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;	 
	
	@PostMapping("/add")
	public ProjectResponse addProject(@RequestBody  ProjectRequest projectRequest)
	{
		return projectService.saveProject(projectRequest);
	}
	@PutMapping("/update")
	public ResponseEntity<?> updateProject(@RequestBody  ProjectRequest projectRequest)
	{
		return projectService.updateProject(projectRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteProject(@PathVariable  Long id)
	{
		return projectService.deleteProject(id);
	}

	@GetMapping("/all")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<ProjectResponse> getProjects()
	{
		return projectService.getProjects();
	}

	@GetMapping("/getdistinctprojects")
	public List<ProjectsnameResponse> getallprojects()
	{
		return projectService.getDistinctProjects();
	}
}
