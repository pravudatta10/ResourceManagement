package com.nichebit.resourcemanagement.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="DocManagement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String docname;
	private String docpath;
	private String doctype;
	private String entityname;
	private String entitygeneratedid;
	private String uploadedby;
	private Date uploadedon;
	private String remarks;
	private String name;
}
