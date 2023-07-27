package com.nichebit.resourcemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.service.EmployeeService;
@RestController
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody EmployeeRequest employeeRequest) {
		return employeeService.saveEmployee(employeeRequest);
	}
	
	@PutMapping("/updateEmployee")
	public String updateEmployee(@RequestBody EmployeeRequest employeeRequest)
	{
		return employeeService.updateEmployee(employeeRequest);
	}
	
	
	@DeleteMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Long id)
	{
		return employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/employees")
	public List<EmployeeResponse> getEmployees()
	{
		return employeeService.getEmployee();
	}
	
}
