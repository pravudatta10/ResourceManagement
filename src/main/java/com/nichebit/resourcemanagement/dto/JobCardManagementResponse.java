package com.nichebit.resourcemanagement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobCardManagementResponse {
	private long id;
	private long empId;
	private String project;
	private String task;
	private String client;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startdate;  
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date enddate;  
	private String remarks;
	private String allocationpercentage;
	private float allocationhours;  
	private String createdby;
	private String updatedby;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdon;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date updationon;
}
