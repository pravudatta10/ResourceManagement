package com.nichebit.resourcemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.HolidayMaster;

public interface HolidayMasterRepository extends JpaRepository<HolidayMaster, Long>{
	
	
	@Query("SELECT m.h_type FROM HolidayMaster m WHERE m.h_date = TO_DATE(:date, 'dd-MON-yy') AND m.client =:Client")
	String findByDateAndClient(@Param("date") String date, @Param("Client") String Client);

	

}
