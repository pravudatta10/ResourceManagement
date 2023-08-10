package com.nichebit.resourcemanagement.dto;

import com.nichebit.resourcemanagement.entity.ClientMaster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientMasterResponse {
	private Long id;
	  private String client;
	  private String workinghours;
}
