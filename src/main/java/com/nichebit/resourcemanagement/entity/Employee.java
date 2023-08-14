package com.nichebit.resourcemanagement.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Employee",uniqueConstraints = {
		@UniqueConstraint(
				name="empid",
				columnNames = "empid"
				),
		@UniqueConstraint(
				name="mobileno",
				columnNames = "mobileno"
				),
		@UniqueConstraint(
				name="email",
				columnNames = "email"
				)
})
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column (nullable = false)
	private long empid;	
	private String empname;	
	private String email;
	private String password;
	private String mobileno;
	private String reportingmanager;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date joiningdate;
	private String status;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String inactivefrom;
	private String client;
	private String roles;
	 
}
