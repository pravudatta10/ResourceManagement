package com.nichebit.resourcemanagement.dto;

import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetManagementResponse {
	private long id;
	private long empid;
	private String reportingmanager;
	private String project;
	private String task;
	private String client;
	private String remarks;
	@Column(name="financialyear",unique = true)
	private int financialyear;
	@Column(name="month_name",unique = true)
	private String month;
	private List<TimeSheetDaysAndHoursResponse> dateAndDayArray;
	private List<TimesheetDaysAndHolidaysResponse> holidayLists;
	private String status;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date submittedon;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date approvedon;
	private List<TasksResponse> taskArr;
	 
}
