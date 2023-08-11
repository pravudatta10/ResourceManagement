package com.nichebit.resourcemanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.nichebit.resourcemanagement.dto.SendMailRequest;
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

	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private SendMailService service;

	ReturnResponse returnResponse = new ReturnResponse();

	public ResponseEntity<?> saveEmployee(EmployeeRequest employeeRequest) throws Exception {

		Optional<Employee> id = employeeRepository.findByempid(employeeRequest.getEmpid());
		Optional<Employee> mail = employeeRepository.findByemailid(employeeRequest.getEmail());
		Optional<Employee> phone = employeeRepository.findBymobileno(employeeRequest.getMobileno());
		if (!id.isEmpty() || !phone.isEmpty() || !mail.isEmpty()) {
			String res = "";
			if (!id.isEmpty()) {
				res = res + "empid" + " ";
			}
			if (!mail.isEmpty()) {
				res = res + "mail" + " ";
			}
			if (!phone.isEmpty()) {
				res = res + "mobile" + " ";
			}
			returnResponse.setStatus(res + "Details Already Exists.");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(returnResponse);
		}

		else {

			Employee employee = this.modelMapper.map(employeeRequest, Employee.class);
			employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
			employeeRepository.save(employee);
			SendMailRequest sendMailRequest = new SendMailRequest();
			sendMailRequest.setFrom("kpds0932@gmail.com");
			sendMailRequest.setName(employeeRequest.getEmpname());
			sendMailRequest.setSubject(employeeRequest.getEmpname() + " " + " Registered Sucessfully With Nichebit");
			sendMailRequest.setTo(employeeRequest.getEmail());
			Map<String, String> model = new HashMap<String, String>();
			// Load the image file from the classpath and convert it to a byte array
			String inlineImage = "<img src=\"cid:nblogo.png\"></img><br/>";
			model.put("UserName", employeeRequest.getEmpname());
			model.put("Password", employeeRequest.getPassword());
			// model.put("logoPath",inlineImage);
			service.sendMail(sendMailRequest, model);
			returnResponse.setStatus("Employee Saved successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public ResponseEntity<?> updateEmployee(EmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.findById(employeeRequest.getId()).orElse(null);
		if (employee == null) {
			returnResponse.setStatus("Employee not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			employee = this.modelMapper.map(employeeRequest, Employee.class);
			employeeRepository.save(employee);
			returnResponse.setStatus("Employee updated successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public ResponseEntity<?> deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		if (employee == null) {
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

	@PostConstruct
	public void AddAdminEmployee() {
		Employee employee = new Employee();
		employee = employeeRepository.findById((long) 1).orElse(null);
		if (employee == null) {
			employee.setEmpid(1);
			employee.setEmpname("Admin");
			employee.setEmail("admin@gmail.com");
			employee.setPassword(passwordEncoder.encode("NBRMS"));
			employee.setMobileno("1134567891");
			employee.setReportingmanager("Admin");
			employee.setJoiningdate(null);
			employee.setStatus("Active");
			employee.setInactivefrom(null);
			employee.setClient("NB");
			employee.setRoles("ROLE_ADMIN");
			employeeRepository.save(employee);
			System.out.println("Admin Added Successfully");
		} else {
			System.out.println("Alrady Exist");
		}

	}

	public List<EmployeeResponse> getEmployeesByRm(String reportingmanager) {

		return employeeRepository.findByReportingManager(reportingmanager).stream()
				.map(employee -> new EmployeeResponse(employee.getId(), employee.getEmpid(), employee.getEmpname(),
						employee.getEmail(), employee.getPassword(), employee.getMobileno(),
						employee.getReportingmanager(), employee.getJoiningdate(), employee.getStatus(),
						employee.getInactivefrom(), employee.getClient(), employee.getRoles()))
				.toList();
	}

	public ResponseEntity<?> getEmployeesByEmpName(String empname) {
		if (employeeRepository.findByempname(empname).isEmpty()) {
			returnResponse.setStatus("Employee  not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			List<EmployeeResponse> employeeResponse = employeeRepository.findByempname(empname).stream()
					.map(employee -> new EmployeeResponse(employee.getId(), employee.getEmpid(), employee.getEmpname(),
							employee.getEmail(), employee.getPassword(), employee.getMobileno(),
							employee.getReportingmanager(), employee.getJoiningdate(), employee.getStatus(),
							employee.getInactivefrom(), employee.getClient(), employee.getRoles()))
					.toList();
			return ResponseEntity.ok(employeeResponse);
		}

	}

}
