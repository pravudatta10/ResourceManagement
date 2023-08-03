package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	// ProjectResponse projectResponse;
	public String saveProject(ProjectRequest projectRequest) {
		Projects project = new Projects();
		project.setProjectname(projectRequest.getProjectname());
		project.setClientname(projectRequest.getClientname());
		project.setFinancialyear(projectRequest.getFinancialyear());
		project.setPonumber(projectRequest.getPonumber());
		project.setPoamount(projectRequest.getPoamount());
		project.setPostatus(projectRequest.getPostatus());
		project.setPostatus(projectRequest.getPostatus());
		project.setPoclearedpercentage(projectRequest.getPoclearedpercentage());
		project.setActualenddate(projectRequest.getActualenddate());
		project.setActualstartdate(projectRequest.getActualstartdate());
		project.setCreationdate(projectRequest.getCreationdate());
		project.setPlanenddate(projectRequest.getPlanenddate());
		project.setPlanstartdate(projectRequest.getPlanstartdate());
		project.setHoldfrom(projectRequest.getHoldfrom());
		project.setResumefrom(projectRequest.getResumefrom());
		project.setDiscardedfrom(projectRequest.getDiscardedfrom());
		project.setCreatedon(projectRequest.getCreationdate());
		project.setCreatedby("Admin");
		project.setUpdatedon(projectRequest.getPlanstartdate());
		project.setUpdatedby(projectRequest.getUpdatedby());
		projectRepository.save(project);
		return "Data Saved Successfully";
	}

	public String updateProject(ProjectRequest projectRequest) {
		Projects project = projectRepository.findById(projectRequest.getId()).orElse(null);
		project.setProjectname(projectRequest.getProjectname());
		project.setClientname(projectRequest.getClientname());
		project.setFinancialyear(projectRequest.getFinancialyear());
		project.setPonumber(projectRequest.getPonumber());
		project.setPoamount(projectRequest.getPoamount());
		project.setPostatus(projectRequest.getPostatus());
		project.setPostatus(projectRequest.getPostatus());
		project.setPoclearedpercentage(projectRequest.getPoclearedpercentage());
		project.setActualenddate(projectRequest.getActualenddate());
		project.setActualstartdate(projectRequest.getActualstartdate());
		project.setCreationdate(projectRequest.getCreationdate());
		project.setPlanenddate(projectRequest.getPlanenddate());
		project.setPlanstartdate(projectRequest.getPlanstartdate());
		project.setHoldfrom(projectRequest.getHoldfrom());
		project.setResumefrom(projectRequest.getResumefrom());
		project.setDiscardedfrom(projectRequest.getDiscardedfrom());
		project.setCreatedon(projectRequest.getCreationdate());
		project.setCreatedby("Admin");
		project.setUpdatedon(projectRequest.getPlanstartdate());
		project.setUpdatedby(projectRequest.getUpdatedby());
		projectRepository.save(project);
		return "Data Updated Successfully";
	}

	public String deleteProject(Long id) {
		projectRepository.deleteById(id);
		return "Data Deleted Successfully";
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
				.map(project -> new ProjectsnameResponse(project.getProjectname()
)).toList();			
	}
	
	
}


