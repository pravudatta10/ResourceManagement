package com.nichebit.resourcemanagement.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetRequestBygettingays {
	private Long id;
	private int empid;
	private String reportingmanager;
	private String project;
	private String task;
	private String client;
	private String remarks;
	@Column(name="financialyear",unique = true)
	private int financialyear;
	@Column(name="month_name",unique = true)
	private String month_name;
	private TimeSheetDaysRequest timeSheetDaysRequest;
	private String status;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date submittedon;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date approvedon;
}
