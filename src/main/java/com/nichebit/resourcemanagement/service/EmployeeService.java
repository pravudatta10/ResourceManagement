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
import com.nichebit.resourcemanagement.dto.ReturnResponse;
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

	ReturnResponse returnResponse = new ReturnResponse();
	
	public ResponseEntity<?> saveEmployee(EmployeeRequest employeeRequest) throws Exception {
		//ReturnResponse returnResponse = new ReturnResponse();
		Optional<Employee> id = employeeRepository.findByempid(employeeRequest.getEmpid());
		// Check if an employee with the provided ID already exists
		if (!id.isEmpty()) {
			returnResponse.setStatus("Employee already exists.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			// Create and save the employee
			Employee employee = this.modelMapper.map(employeeRequest, Employee.class);
			employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
			employeeRepository.save(employee);
			/*
			 * SendMailRequest sendMailRequest = new SendMailRequest();
			 * sendMailRequest.setFrom("kpds0932@gmail.com");
			 * sendMailRequest.setName(employeeResponse.getEmpname());
			 * sendMailRequest.setSubject(employeeResponse.getEmpname() + " " +
			 * " Registered Sucessfully With Nichebit");
			 * sendMailRequest.setTo(employeeResponse.getEmail()); 
			 * Map<String, Object> model= new HashMap<>(); ClassPathResource logoResouce = new
			 * ClassPathResource("assets/nblogo.png"); String logoPath =
			 * "assets/nblogo.png"; model.put("UserName", sendMailRequest.getName());
			 * model.put("logoPath", logoPath); service.sendMail(sendMailRequest, model);
			 */
			returnResponse.setStatus("Employee Saved successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public ResponseEntity<?> updateEmployee(EmployeeRequest employeeRequest) {
		//ReturnResponse returnResponse = new ReturnResponse();
		Employee employee = employeeRepository.findById(employeeRequest.getId()).orElse(null);
		if (employee == null) {
			// Return a custom error message for 404 Not Found
			returnResponse.setStatus("Employee not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			employee = this.modelMapper.map(employeeRequest, Employee.class);
			employeeRepository.save(employee);
			// Return a custom success message for the updated employee
			returnResponse.setStatus("Employee updated successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public ResponseEntity<?> deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		//ReturnResponse returnResponse = new ReturnResponse();
		if (employee == null) {
			// Return a custom error message for 404 Not Found
			returnResponse.setStatus("Employee  not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
			} else {
			employeeRepository.deleteById(id);
			returnResponse.setStatus("Employee deleted successfully.");
			return ResponseEntity.ok(returnResponse);
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
	
	public ResponseEntity<?> getEmployeesByEmpName(String empname) {
	if(employeeRepository.findByempname(empname).isEmpty()) {
		returnResponse.setStatus("Employee  not found.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
	}
	else {
		List<EmployeeResponse> employeeResponse= employeeRepository.findByempname(empname).stream()
				.map(employee -> new EmployeeResponse(employee.getId(), employee.getEmpid(), employee.getEmpname(),
						employee.getEmail(), employee.getPassword(), employee.getMobileno(),
						employee.getReportingmanager(), employee.getJoiningdate(), employee.getStatus(),
						employee.getInactivefrom(), employee.getClient(), employee.getRoles()))
				.toList();
		return ResponseEntity.ok(employeeResponse);
	}
	
	}

}
