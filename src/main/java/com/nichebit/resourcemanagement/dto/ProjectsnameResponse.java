package com.nichebit.resourcemanagement.dto;

import java.util.Date;

import com.nichebit.resourcemanagement.entity.Projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsnameResponse {
	private String projectname;
	private long id;
}
