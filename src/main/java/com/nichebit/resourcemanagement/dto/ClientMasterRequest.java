package com.nichebit.resourcemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientMasterRequest {
	private Long id;
	private String client;
	private String workinghours;
}
