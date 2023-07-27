package com.nichebit.resourcemanagement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocManagementResponse {
	private long id;
	private String docname;
	private String docpath;
	private String doctype;
	private String entityname;
	private String entitygeneratedid;
	private String uploadedby;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date uploadedon;
	private String remarks;
}
