package com.nichebit.resourcemanagement.entity;

import java.util.Date;

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
				)		
})
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column (nullable = false)
	private int empid;
	
	@Column (nullable = false)
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
