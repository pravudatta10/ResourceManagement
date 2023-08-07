package com.nichebit.resourcemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.JobCardManagement;

public interface JobCardManagementRepository extends JpaRepository<JobCardManagement, Long> {

	@Query("SELECT M FROM JobCardManagement M WHERE empId =:EMP_ID")
	List<JobCardManagement> findbyEmpId(@Param("EMP_ID") Long EMP_ID);
}
