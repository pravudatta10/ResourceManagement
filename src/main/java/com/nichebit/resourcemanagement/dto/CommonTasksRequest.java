package com.nichebit.resourcemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonTasksRequest {

	private Long id;
	private String task;
	private String commonprojectid;

}
