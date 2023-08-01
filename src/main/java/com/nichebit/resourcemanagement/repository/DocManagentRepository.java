package com.nichebit.resourcemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.DocManagement;

public interface DocManagentRepository extends JpaRepository<DocManagement, Long> {
	
	Optional<DocManagement> findBydocname(String docname); 
}
