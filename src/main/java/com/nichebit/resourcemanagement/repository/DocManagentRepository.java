package com.nichebit.resourcemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.DocManagement;

public interface DocManagentRepository extends JpaRepository<DocManagement, Long> {
	
	Optional<DocManagement> findBydocname(String docname); 
	
	
	   @Query("SELECT m FROM DocManagement m WHERE entityname=:entityname AND entitygeneratedid=:entitygeneratedid")
		List<DocManagement> findDocByentityid(@Param("entityname") String entityname,
				@Param("entitygeneratedid") String entitygeneratedid);
}
