package com.nichebit.resourcemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichebit.resourcemanagement.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> getByEmpname(String username);

	Optional<Employee> findByEmpname(String username);
	
	Optional<Employee> findByreportingmanager(String reportingmanager);
	

}
