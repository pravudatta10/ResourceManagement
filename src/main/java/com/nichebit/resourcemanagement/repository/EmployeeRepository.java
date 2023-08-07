package com.nichebit.resourcemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> getByEmpname(String username);

	Optional<Employee> findByEmpname(String username);
	
	@Query("SELECT m FROM Employee m WHERE reportingmanager =:rm")
	List<Employee> findByReportingManager(@Param("rm") String reportingmanager);

	@Query("SELECT m FROM Employee m WHERE empid =:EMP_ID")
	Optional<Employee> findByempid(@Param("EMP_ID") long empid);


}
