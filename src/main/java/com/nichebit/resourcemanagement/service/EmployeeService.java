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
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.dto.SendMailRequest;
import com.nichebit.resourcemanagement.entity.Employee;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;

import freemarker.template.Template;
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
	private FreeMarkerConfigurer freeMarkerConfigurer;

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
			try {
				Employee employee = this.modelMapper.map(employeeRequest, Employee.class);
				employee.setPassword(passwordEncoder.encode("NBRMS"));				
				SendMailRequest sendMailRequest = new SendMailRequest();
				sendMailRequest.setFrom("apparao.m@nichebit.com");
				sendMailRequest.setName(employeeRequest.getEmpname());
				sendMailRequest.setSubject(employeeRequest.getEmpname() + " " + " Registered Sucessfully With Nichebit");
				sendMailRequest.setTo(employeeRequest.getEmail());
				Map<String, String> model = new HashMap<String, String>();
				// Load the image file from the classpath and convert it to a byte array
				// String inlineImage = "<img src=\"cid:nblogo.png\"></img><br/>";
				String url="http://192.168.2.163:3001/";
				model.put("UserName", employeeRequest.getEmpname());
				model.put("Password", "NBRMS");
				model.put("URL",url);
				freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
				configuration.setSetting("template_update_delay", "1");
				Template template = configuration.getTemplate("registerTemplate.ftl");
				service.sendMail(sendMailRequest, model, template);
				returnResponse.setStatus("Employee Saved successfully.");
				employeeRepository.save(employee);
				return ResponseEntity.ok(returnResponse);
			}
			catch (Exception e) {
				returnResponse.setStatus("Employee Saved successfully.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnResponse);
			}
		}
		

	}

	public ResponseEntity<?> updateEmployee(EmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.findById(employeeRequest.getId()).orElse(null);
		if (employee == null) {
			returnResponse.setStatus("Employee not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			employee = this.modelMapper.map(employeeRequest, Employee.class);
			employee.setPassword(passwordEncoder.encode("NBRMS"));
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
	public void AddAdminEmployee() throws Exception {
		Employee employee = new Employee();
		employee = employeeRepository.findByEmpidOrEmailOrMobileno(7, "9493194820", "venkateswar.andra@nichebit.com");
		if (null == employee) {
			employee.setEmpid(7);
			employee.setEmpname("Venkat");
			employee.setEmail("venkateswar.andra@nichebit.com");
			employee.setPassword(passwordEncoder.encode("NBRMS"));
			employee.setMobileno("9493194820");
			employee.setReportingmanager("Nagesh");
			employee.setJoiningdate(null);
			employee.setStatus("Active");
			employee.setInactivefrom(null);
			employee.setClient("NB");
			employee.setRoles("Admin,HR,Reporting Manager,Project Manager,User");
			employeeRepository.save(employee);
			SendMailRequest sendMailRequest = new SendMailRequest();
			sendMailRequest.setFrom("apparao.m@nichebit.com");
			sendMailRequest.setName(employee.getEmpname());
			sendMailRequest.setSubject(employee.getEmpname() + " " + " Registered Sucessfully With Nichebit");
			sendMailRequest.setTo(employee.getEmail());
			Map<String, String> model = new HashMap<String, String>();
			model.put("UserName", employee.getEmpname());
			model.put("Password", "NBRMS");
			freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate("registerTemplate.ftl");
			service.sendMail(sendMailRequest, model, template);
			System.out.println("Admin Added Successfully");
		} else {
			System.out.println("Already Exist");
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
