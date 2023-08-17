package com.nichebit.resourcemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.dto.SumOfHrsInDaysDto;
import com.nichebit.resourcemanagement.entity.TimesheetManagement;

public interface TimeSheetManagementRepository extends JpaRepository<TimesheetManagement, Long> {

 
	
	@Query("SELECT m FROM TimesheetManagement m WHERE empid =:empidd AND financialyear =:financialyearr AND month =:monthh")
	List<TimesheetManagement> findByempidmonthfy(@Param("empidd") long empid,@Param("financialyearr") int financialyear,@Param("monthh") String month);
	 @Query("SELECT new com.nichebit.resourcemanagement.dto.SumOfHrsInDaysDto(" +
	           "SUM(m.day01), SUM(m.day02), SUM(m.day03), SUM(m.day04), SUM(m.day05)," +
	           "SUM(m.day06), SUM(m.day07), SUM(m.day08), SUM(m.day09), SUM(m.day10)," +
	           "SUM(m.day11), SUM(m.day12), SUM(m.day13), SUM(m.day14), SUM(m.day15)," +
	           "SUM(m.day16), SUM(m.day17), SUM(m.day18), SUM(m.day19), SUM(m.day20)," +
	           "SUM(m.day21), SUM(m.day22), SUM(m.day23), SUM(m.day24), SUM(m.day25)," +
	           "SUM(m.day26), SUM(m.day27), SUM(m.day28), SUM(m.day29), SUM(m.day30), SUM(m.day31)) " +
	           "FROM TimesheetManagement m " +
	           "WHERE m.empid = :empidd " +
	           "AND m.financialyear = :financialyearr " +
	           "AND m.month = :monthh " +
	           "GROUP BY m.empid")
	    SumOfHrsInDaysDto findByempTS(@Param("empidd") long empid,
	                                  @Param("financialyearr") int financialyear,
	                                  @Param("monthh") String month);
}
