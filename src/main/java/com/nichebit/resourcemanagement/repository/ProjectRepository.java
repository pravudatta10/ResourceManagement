package com.nichebit.resourcemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.dto.dtoForProjectResponse;
import com.nichebit.resourcemanagement.entity.Projects;

public interface ProjectRepository extends JpaRepository<Projects,Long>{
	
	
	@Query("SELECT DISTINCT new com.nichebit.resourcemanagement.dto.dtoForProjectResponse(m.projectname,m.id) FROM Projects m")
	List<dtoForProjectResponse> findAllProjects();
	
	@Query("SELECT P FROM Projects P WHERE projectname =:PROJECTNAME")
	Optional<Projects> findbyProjectName(@Param("PROJECTNAME") String projectname);
	
	@Query("SELECT DISTINCT P.id FROM Projects P WHERE projectname = :PROJECTNAME")
	Long findProjectIdByName(@Param("PROJECTNAME") String projectname);


}
