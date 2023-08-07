package com.nichebit.resourcemanagement.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.dto.SendMailRequest;
import com.nichebit.resourcemanagement.dto.SendMailResponse;
import com.nichebit.resourcemanagement.entity.Employee;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.MessagingException;

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

	public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) throws Exception {
		Employee employee = this.modelMapper.map(employeeRequest, Employee.class);
		employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
		employeeRepository.save(employee);
		EmployeeResponse employeeResponse = this.modelMapper.map(employee, EmployeeResponse.class);

		SendMailRequest sendMailRequest = new SendMailRequest();
		sendMailRequest.setFrom("kpds0932@gmail.com");
		sendMailRequest.setName(employeeResponse.getEmpname());
		sendMailRequest.setSubject(employeeResponse.getEmpname() + " " + " Registered Sucessfully With Nichebit");
		sendMailRequest.setTo(employeeResponse.getEmail());
		Map<String, Object> model = new HashMap<>();
		ClassPathResource logoResouce=new ClassPathResource("assets/nblogo.png");
		String logoPath = "assets/nblogo.png";  
		model.put("UserName", sendMailRequest.getName());
		model.put("logoPath", logoPath);
		//service.sendMail(sendMailRequest, model);
		return employeeResponse;
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
