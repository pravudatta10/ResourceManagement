package com.nichebit.resourcemanagement.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String projectname;
	private String clientname;
	private String financialyear;
	private String ponumber;
	private double poamount;
	private String projectstatus;
	private String postatus;
	private double poclearedpercentage;
	private Date creationdate;
	private Date actualstartdate;
	private Date actualenddate;
	private Date planenddate;
	private Date planstartdate;
	private Date holdfrom;
	private Date resumefrom;
	private Date discardedfrom;
	private Date createdon;
	private String createdby;
	private Date updatedon;
	private String updatedby;

}
