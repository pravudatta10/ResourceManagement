package com.nichebit.resourcemanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientMaster {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String client;
  private String workinghours;
}
