package com.nichebit.resourcemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichebit.resourcemanagement.entity.Projects;

public interface ProjectRepository extends JpaRepository<Projects,Long>{

}
