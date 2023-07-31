package com.nichebit.resourcemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichebit.resourcemanagement.entity.TimesheetManagement;


public interface TimeSheetManagementRepository extends JpaRepository<TimesheetManagement, Long> {

}
