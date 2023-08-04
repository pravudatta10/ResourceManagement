package com.nichebit.resourcemanagement.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column (name="empid",  unique = true)
	private int empid;
	
	@Column(name = "empname", unique = true)	
	private String empname;
	
	private String email;
	private String password;
	private String mobileno;
	private String reportingmanager;
	private Date joiningdate;
	private String status;
	private String inactivefrom;
	private String client;
	private String roles;
	 
}
