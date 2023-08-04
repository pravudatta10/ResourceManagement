package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.entity.Employee;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
		Employee employee = this.modelMapper.map(employeeRequest, Employee.class);
		employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
		employeeRepository.save(employee);
		return this.modelMapper.map(employee, EmployeeResponse.class);

	}

	public ResponseEntity<?> updateEmployee(EmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.findById(employeeRequest.getId()).orElse(null);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		employee = this.modelMapper.map(employeeRequest, Employee.class);
		employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
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

	/*@PostConstruct
	public void AddAdminEmployee() {
		Employee employee = new Employee();
		employee.setEmpid(1);
		employee.setEmpname("Admin");
		employee.setEmail("admin@gmail.com");
		employee.setPassword(passwordEncoder.encode("Admin@123"));
		employee.setMobileno("1134567891");
		employee.setReportingmanager("Admin");
		employee.setJoiningdate(null);
		employee.setStatus("Active");
		employee.setInactivefrom(null);
		employee.setClient("NB");
		employee.setRoles("ROLE_ADMIN");
		employeeRepository.save(employee);
		System.out.println("Admin Added Successfully");

	}*/

	public List<EmployeeResponse> getEmployeesByRm(String reportingmanager) {

		return employeeRepository.findByReportingManager(reportingmanager).stream()
				.map(employee -> new EmployeeResponse(employee.getId(), employee.getEmpid(), employee.getEmpname(),
						employee.getEmail(), employee.getPassword(), employee.getMobileno(),
						employee.getReportingmanager(), employee.getJoiningdate(), employee.getStatus(),
						employee.getInactivefrom(), employee.getClient(), employee.getRoles()))
				.toList();
	}

}
