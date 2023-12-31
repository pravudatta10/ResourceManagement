package com.nichebit.resourcemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> getByEmpname(String username);

	Optional<Employee> findByEmpname(String username);

	@Query("SELECT m FROM Employee m WHERE reportingmanager =:rm")
	List<Employee> findByReportingManager(@Param("rm") String reportingmanager);

	@Query("SELECT m FROM Employee m  WHERE empid =:EMP_ID")
	Optional<Employee> findByempid(@Param("EMP_ID") long empid);

	@Query("SELECT m FROM Employee m WHERE email =:email")
	Optional<Employee> findByemailid(@Param("email") String email);

	@Query("SELECT m FROM Employee m WHERE mobileno =:mobileno")
	Optional<Employee> findBymobileno(@Param("mobileno") String mobileno);

	@Query("SELECT m FROM Employee m WHERE empname =:EMPNAME")
	Optional<Employee> findByName(@Param("EMPNAME") String empname);

	@Query("SELECT m.empid FROM Employee m WHERE empname =:empname")
	long findempnamebyempid(@Param("empname") String empname);

	@Query("SELECT M.empid FROM Employee M WHERE M.empid is not null")
	List<Long> allEmpid();
	
	@Query("SELECT m FROM Employee m  WHERE empid =:empid")
	Employee findByempidforTS(@Param("empid") long empid);

	@Query("SELECT m FROM Employee m  WHERE empid =:empid AND mobileno =:mobileno AND email =:email")
	Optional<Employee>  findByEmpidOrEmailOrMobileno(@Param("empid") long empid, @Param("mobileno") String mobileno,@Param("email") String email);
	

}
