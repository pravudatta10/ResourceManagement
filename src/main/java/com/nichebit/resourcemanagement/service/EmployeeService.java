package com.nichebit.resourcemanagement.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.entity.Employee;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private SendMailService service;

	public ResponseEntity<?> saveEmployee(EmployeeRequest employeeRequest) throws Exception {
		Optional<Employee> id = employeeRepository.findById(employeeRequest.getEmpid());
		// Check if an employee with the provided ID already exists
		if (id != null) {
			String errorMessage = "Employee already exists.";
			return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
		} else {
			// Create and save the employee
			Employee employee = this.modelMapper.map(employeeRequest, Employee.class);
			employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
			employeeRepository.save(employee);
			/* SendMailRequest sendMailRequest = new SendMailRequest();
			sendMailRequest.setFrom("kpds0932@gmail.com");
			sendMailRequest.setName(employeeResponse.getEmpname());
			sendMailRequest.setSubject(employeeResponse.getEmpname() + " " + " Registered Sucessfully With Nichebit");
			sendMailRequest.setTo(employeeResponse.getEmail());
			Map<String, Object> model = new HashMap<>();
			ClassPathResource logoResouce = new ClassPathResource("assets/nblogo.png");
			String logoPath = "assets/nblogo.png";
			model.put("UserName", sendMailRequest.getName());
			model.put("logoPath", logoPath);
			service.sendMail(sendMailRequest, model);*/
			String successMessage = "Employee Saved successfully.";
			return ResponseEntity.ok(successMessage);
		}

	}

	public ResponseEntity<?> updateEmployee(EmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.findById(employeeRequest.getId()).orElse(null);
		if (employee == null) {
			// Return a custom error message for 404 Not Found
			String errorMessage = "Employee not found.";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		} else {
			employee = this.modelMapper.map(employeeRequest, Employee.class);
			employeeRepository.save(employee);
			// Return a custom success message for the updated employee
			String successMessage = "Employee updated successfully.";
			return ResponseEntity.ok(successMessage);
		}

	}

	public ResponseEntity<?> deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		if (employee == null) {
			// Return a custom error message for 404 Not Found
			String errorMessage = "Employee not found.";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		} else {
			employeeRepository.deleteById(id);
			String successMessage = "Employee deleted successfully.";
			return ResponseEntity.ok(successMessage);
		}

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
	 * }
	 */

	public List<EmployeeResponse> getEmployeesByRm(String reportingmanager) {

		return employeeRepository.findByReportingManager(reportingmanager).stream()
				.map(employee -> new EmployeeResponse(employee.getId(), employee.getEmpid(), employee.getEmpname(),
						employee.getEmail(), employee.getPassword(), employee.getMobileno(),
						employee.getReportingmanager(), employee.getJoiningdate(), employee.getStatus(),
						employee.getInactivefrom(), employee.getClient(), employee.getRoles()))
				.toList();
	}

}
