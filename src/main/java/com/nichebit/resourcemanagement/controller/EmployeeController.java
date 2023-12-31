package com.nichebit.resourcemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.service.EmployeeService;
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/add")
	public ResponseEntity<ReturnResponse> addEmployee(@RequestBody EmployeeRequest employeeRequest) throws Exception {
		return employeeService.saveEmployee(employeeRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ReturnResponse> updateEmployee(@RequestBody EmployeeRequest employeeRequest)
	{
		return employeeService.updateEmployee(employeeRequest);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReturnResponse> deleteEmployee(@PathVariable Long id)
	{
		return employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/all")
	public List<EmployeeResponse> getEmployees()
	{
		return employeeService.getEmployee();
	}
	
	@GetMapping("/getemployeesbyrm")
	public List<EmployeeResponse> getemployeesByRm(@RequestParam("reportingmanager") String reportingmanager)
	{
		return employeeService.getEmployeesByRm(reportingmanager);
	}
	
	@GetMapping("/getemployeesbyempname")
	public ResponseEntity<?> getEmployeesByEmpName(@RequestParam("empname") String empname)
	{
		return employeeService.getEmployeesByEmpName(empname);
	}
}
