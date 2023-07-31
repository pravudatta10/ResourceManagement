package com.nichebit.resourcemanagement.dto;

import com.nichebit.resourcemanagement.entity.MasterMangement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterMangementResponse {
	private long id;
	private long lov_id;
	private String lov_desc;
	private String type;
	private String status;
}
