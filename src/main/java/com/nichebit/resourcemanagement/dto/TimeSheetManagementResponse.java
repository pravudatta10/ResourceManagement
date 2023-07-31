package com.nichebit.resourcemanagement.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetManagementResponse {
	private Long id;
	private int empid;
	private String reportingmanager;
	private String project;
	private String task;
	private String client;
	private String remarks;
	@Column(name="financialyear",unique = true)
	private int financialyear;
	@Column(name="month_name",unique = true)
	private String month_name;
	private float day01;
	private float day02;
	private float day03;
	private float day04;
	private float day05;
	private float day06;
	private float day07;
	private float day08;
	private float day09;
	private float day10;
	private float day11;
	private float day12;
	private float day13;
	private float day14;
	private float day15;
	private float day16;
	private float day17;
	private float day18;
	private float day19;
	private float day20;
	private float day21;
	private float day22;
	private float day23;
	private float day24;
	private float day25;
	private float day26;
	private float day27;
	private float day28;
	private float day29;
	private float day30;
	private float day31;
	private String status;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date submittedon;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date approvedon;
}
