package com.nichebit.resourcemanagement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
	private long id;
	private String projectname;
	private String clientname;
	private String financialyear;
	private String ponumber;
	private double poamount;
	private String projectstatus;
	private String postatus;
	private double poclearedpercentage;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date creationdate;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date actualstartdate;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date actualenddate;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date planenddate;
}
