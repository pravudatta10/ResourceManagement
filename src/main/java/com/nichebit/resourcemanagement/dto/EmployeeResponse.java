package com.nichebit.resourcemanagement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
	private long id;
	private int empid;
	private String empname;
	private String email;
	private String password;
	private int mobileno;
	private String reportingmanager;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date joiningdate;
	private String status;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String inactivefrom;
	private String client;
	private String roles;
}
