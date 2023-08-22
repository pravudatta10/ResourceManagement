package com.nichebit.resourcemanagement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskManagementRequest {

	private long id;
	private String task;
	private String tasktype;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private  Date planstartdate ;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private  Date planenddate ;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date actualstartdate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date actualenddate ;
	private String taskstatus;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date holdfrom ;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date resumefrom ;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date discardedfrom ;
	private String createdby;
	private String updatedby;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdon ;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date updationon ;
	private long projectid;
}
