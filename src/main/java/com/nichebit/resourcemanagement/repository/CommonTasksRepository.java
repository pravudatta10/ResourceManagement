package com.nichebit.resourcemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichebit.resourcemanagement.entity.CommonTasks;

public interface CommonTasksRepository extends JpaRepository<CommonTasks,Long>{

}
