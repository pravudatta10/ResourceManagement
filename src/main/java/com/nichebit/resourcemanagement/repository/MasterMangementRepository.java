package com.nichebit.resourcemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nichebit.resourcemanagement.entity.MasterMangement;



public interface MasterMangementRepository extends JpaRepository<MasterMangement, Long>{

	
//	@Query("SELECT m FROM MasterMangement m WHERE m.type = ?1")
//	public Optional<MasterMangement> findBytype(String type);
	
	 Optional<MasterMangement> findBytype(String type);
}
