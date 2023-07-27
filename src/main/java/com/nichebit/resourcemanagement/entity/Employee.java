package com.nichebit.resourcemanagement.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int empid;
	private String empname;
	private String email;
	private String password;
	private int mobileno;
	private String reportingmanager;
	private Date joiningdate;
	private String status;
	private String inactivefrom;
	private String client;
	private String roles;
	 
}
