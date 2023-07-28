package com.nichebit.resourcemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichebit.resourcemanagement.entity.TaskManagement;



public interface TaskManagementRepository extends JpaRepository<TaskManagement, Long>{

}
