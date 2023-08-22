package com.nichebit.resourcemanagement.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="HolidayMaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String client;
	private Date hDate;
	private String hType;
	private String remarks;

}
