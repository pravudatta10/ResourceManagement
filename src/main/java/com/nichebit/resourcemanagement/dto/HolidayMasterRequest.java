package com.nichebit.resourcemanagement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayMasterRequest {
	
	private long id;
	private String client;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date h_date;
	private String h_type;
	private String remarks;
}
