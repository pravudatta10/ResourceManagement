package com.nichebit.resourcemanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;

import com.nichebit.resourcemanagement.entity.JobCardManagement;

@DataJpaTest
public class JobCardManagementRepositoryTest {

	
	@Autowired
	private JobCardManagementRepository repository;
	private JobCardManagement jobcard;
	
	@BeforeEach
	void setUp()
	{
		jobcard = new JobCardManagement(1L,1L,"P1","T1","C1",null,null,"R1","100","8","","",null,null);
		repository.save(jobcard);
	}
	@Test
	public void findbyEmpIdTest()
	{
		List<JobCardManagement> jobcardList = repository.findbyEmpId(1L);
		assertEquals("P1",jobcardList.get(0).getProject());
	}
	@AfterEach
	void tearDown()
	{
		jobcard = null;
		repository.deleteAll();
		
	}
}
