package com.nichebit.resourcemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.TaskManagement;



public interface TaskManagementRepository extends JpaRepository<TaskManagement, Long>{

	
	@Query("select m.task From TaskManagement m where Project_id=:Pro")
	Optional<String> findByPid(@Param("Pro")long Project_id);
}
