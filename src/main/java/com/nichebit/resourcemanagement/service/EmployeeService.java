package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.EmployeeRequestGetReportingManager;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.entity.Employee;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public String saveEmployee(EmployeeRequest employeeRequest) {
		Employee employee = new Employee();
		employee.setEmpid(employeeRequest.getEmpid());
		employee.setEmpname(employeeRequest.getEmpname());
		employee.setEmail(employeeRequest.getEmail());
		employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
		employee.setMobileno(employeeRequest.getMobileno());
		employee.setReportingmanager(employeeRequest.getReportingmanager());
		employee.setJoiningdate(employeeRequest.getJoiningdate());
		employee.setStatus(employeeRequest.getStatus());
		employee.setInactivefrom(employeeRequest.getInactivefrom());
		employee.setClient(employeeRequest.getClient());
		employee.setRoles(employeeRequest.getRoles());
		employeeRepository.save(employee);
		return "Employee Data Saved Successfully";

	}

	public String updateEmployee(EmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.findById(employeeRequest.getId()).orElse(null);
		employee.setEmpid(employeeRequest.getEmpid());
		employee.setEmpname(employeeRequest.getEmpname());
		employee.setEmail(employeeRequest.getEmail());
		employee.setPassword(employeeRequest.getPassword());
		employee.setMobileno(employeeRequest.getMobileno());
		employee.setReportingmanager(employeeRequest.getReportingmanager());
		employee.setJoiningdate(employeeRequest.getJoiningdate());
		employee.setStatus(employeeRequest.getStatus());
		employee.setInactivefrom(employeeRequest.getInactivefrom());
		employee.setClient(employeeRequest.getClient());
		employee.setRoles(employeeRequest.getRoles());
		employeeRepository.save(employee);
		return "Employee Data Updated Successfully";

	}

	public String deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return "Employee Deleted Successfully";
	}

	public List<EmployeeResponse> getEmployee() {
		return employeeRepository.findAll().stream()
				.map(employee -> new EmployeeResponse(employee.getId(), employee.getEmpid(), employee.getEmpname(),
						employee.getEmail(), employee.getPassword(), employee.getMobileno(),
						employee.getReportingmanager(), employee.getJoiningdate(), employee.getStatus(),
						employee.getInactivefrom(), employee.getClient(), employee.getRoles()))
				.toList();

	}

	/*
	 * @PostConstruct public void AddAdminEmployee() { Employee employee = new
	 * Employee(); employee.setEmpid(1); employee.setEmpname("Admin");
	 * employee.setEmail("admin@gmail.com");
	 * employee.setPassword(passwordEncoder.encode("Admin@123"));
	 * employee.setMobileno("1134567891"); employee.setReportingmanager("Admin");
	 * employee.setJoiningdate(null); employee.setStatus("Active");
	 * employee.setInactivefrom(null); employee.setClient("NB");
	 * employee.setRoles("ROLE_ADMIN"); employeeRepository.save(employee);
	 * System.out.println("Admin Added Successfully");
	 * 
	 * <<<<<<< HEAD }
	 */

	public List<EmployeeResponse> getEmployeesByRm(EmployeeRequestGetReportingManager reportingmanager) {
		
		return employeeRepository.findByReportingManager(reportingmanager.getReportingmanager()).stream()
				.map(employee -> new EmployeeResponse(employee.getId(), employee.getEmpid(), employee.getEmpname(),
						employee.getEmail(), employee.getPassword(), employee.getMobileno(),
						employee.getReportingmanager(), employee.getJoiningdate(), employee.getStatus(),
						employee.getInactivefrom(), employee.getClient(), employee.getRoles()))
				.toList();
	}

}
