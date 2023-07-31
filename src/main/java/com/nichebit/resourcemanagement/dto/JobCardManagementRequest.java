package com.nichebit.resourcemanagement.dto;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobCardManagementRequest {
	private long id;
	private long empId;
	private String project;
	private String task;
	private String client;
	private Date startdate;  
	private Date enddate;  
	private String remarks;
	private String allocationpercentage;
	private float allocationhours;  
	private String createdby;
	private String updatedby;
	private Date createdon;
	private Date updationon;
}
