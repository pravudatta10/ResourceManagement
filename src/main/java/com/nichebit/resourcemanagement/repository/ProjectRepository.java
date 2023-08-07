package com.nichebit.resourcemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nichebit.resourcemanagement.dto.dtoForProjectResponse;
import com.nichebit.resourcemanagement.entity.Projects;

public interface ProjectRepository extends JpaRepository<Projects,Long>{
	
	
	@Query("SELECT DISTINCT new com.nichebit.resourcemanagement.dto.dtoForProjectResponse(m.projectname,m.id) FROM Projects m")
	List<dtoForProjectResponse> findAllProjects();
	
	

}
