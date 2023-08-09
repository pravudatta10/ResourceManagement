package com.nichebit.resourcemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.TimesheetManagement;

public interface TimeSheetManagementRepository extends JpaRepository<TimesheetManagement, Long> {

	
	@Query("SELECT m FROM TimesheetManagement m WHERE empid=:empidd AND financialyear=:financialyearr AND month=:monthh")
	List<TimesheetManagement> findByempidmonthfy(@Param("empidd") long empid,@Param("financialyearr") int financialyear,@Param("monthh") String month);
}
