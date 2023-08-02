package com.nichebit.resourcemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichebit.resourcemanagement.entity.MasterMangement;

public interface MasterMangementRepository extends JpaRepository<MasterMangement, Long> {

	List<MasterMangement> findBytype(String type);
}
