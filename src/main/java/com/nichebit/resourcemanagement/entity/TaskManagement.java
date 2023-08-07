package com.nichebit.resourcemanagement.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="TaskManagement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String task;
	private String tasktype;
	private Date planstartdate;
	private Date planenddate;
	private Date actualstartdate;
	private Date actualenddate;
	private String taskstatus;
	private Date holdfrom;
	private Date resumefrom;
	private Date discardedfrom;
	private String createdby;
	private String updatedby;
	private Date createdon;
	private Date updationon;

	
//	@Column(name = "projectid", unique = true)
//	private long projectid;
	@ManyToOne
	@JoinColumn(name="Project_id")
	private Projects Project;
}
