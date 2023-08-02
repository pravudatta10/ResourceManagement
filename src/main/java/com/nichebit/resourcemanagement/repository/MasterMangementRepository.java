package com.nichebit.resourcemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nichebit.resourcemanagement.entity.MasterMangement;


@Repository("MasterMangementRepository")
public interface MasterMangementRepository extends JpaRepository<MasterMangement, Long>{

	
	@Query("SELECT m FROM MasterMangement m WHERE m.type = ?1")
	public Optional<MasterMangement> findBytype(String type);
}
