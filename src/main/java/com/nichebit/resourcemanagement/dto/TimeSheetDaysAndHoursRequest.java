package com.nichebit.resourcemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheetDaysAndHoursRequest {
	
	private int date;
	private String dayName;
	private  float time;

}
