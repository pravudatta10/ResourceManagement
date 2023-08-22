package com.nichebit.resourcemanagement.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="MasterMangement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterMangement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String lovId;
	private String lovDesc;
	private String type;
	private String status;
}
