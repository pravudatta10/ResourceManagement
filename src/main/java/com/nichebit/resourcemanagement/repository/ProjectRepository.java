package com.nichebit.resourcemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nichebit.resourcemanagement.entity.Projects;

public interface ProjectRepository extends JpaRepository<Projects,Long>{
	
	
	@Query("SELECT DISTINCT m.projectname FROM Projects")
	List<Projects> findAllProjects();

}
