package com.nichebit.resourcemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterMangementRequest {
	private long id;
	private String lovId;
	private String lovDesc;
	private String type;
	private String status;
}
