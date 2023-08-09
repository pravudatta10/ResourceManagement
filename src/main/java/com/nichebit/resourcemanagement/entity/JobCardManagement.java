package com.nichebit.resourcemanagement.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "JobCardManagement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobCardManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long empId;
	private String project;
	private String task;
	private String client;
	private Date startdate;
	private Date enddate;
	private String remarks;
	private String allocationpercentage;
	private String allocationhours;
	private String createdby;
	private String updatedby;
	private Date createdon;
	private Date updationon;
}
