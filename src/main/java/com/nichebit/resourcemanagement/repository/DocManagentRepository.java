package com.nichebit.resourcemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichebit.resourcemanagement.entity.DocManagement;

public interface DocManagentRepository extends JpaRepository<DocManagement, Long> {
	
	Optional<DocManagement> findByName(String name);
	 
}
