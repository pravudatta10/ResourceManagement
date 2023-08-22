package com.nichebit.resourcemanagement.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nichebit.resourcemanagement.dto.JobCardManagementRequest;
import com.nichebit.resourcemanagement.entity.JobCardManagement;
import com.nichebit.resourcemanagement.repository.JobCardManagementRepository;
import com.nichebit.resourcemanagement.service.JobCardManagementService;

 class JobCardManagementServiceTest {
	
	@Mock
	private JobCardManagementRepository repository;
	@Mock
    private ModelMapper modelMapper;
	AutoCloseable autoClosable;
	JobCardManagementService service;
	@Mock
	private JobCardManagement jobcard;
	
	@Mock
	private JobCardManagementRequest jobcardRequest;
	
	ResponseEntity<?> response = null;
	
	@BeforeEach
	void setUp()
	{
		autoClosable = MockitoAnnotations.openMocks(this);
		service = new JobCardManagementService(repository,modelMapper);
		//modelMapper = new JobCardManagementService(repository);
		//jobcard = new JobCardManagement(1L,1L,"P1","T1","C1",null,null,"R1","100","8","","",null,null);
		jobcardRequest = new JobCardManagementRequest(1L,1L,"P1","T1","C1",null,null,"R1","100","8","","",null,null);
		//repository.save(jobcard);
		response=new ResponseEntity<>(jobcard,HttpStatus.OK);
	}
	@Test
	public void addjobcardmanagentTest()
	{
		when(modelMapper.map(jobcardRequest, JobCardManagement.class)).thenReturn(jobcard);
		when(repository.save(jobcard)).thenReturn(jobcard);
		//List<JobCardManagement> jobcardList = repository.findbyEmpId(1L);
		//addjobcardmanagent(JobCardManagementRequest jobCardManagementRequest)
		assertEquals(service.addjobcardmanagent(jobcardRequest).getStatusCode(),HttpStatus.OK);
	}
	@AfterEach
	void tearDown()
	{
		jobcard = null;
		repository.deleteAll();
		
	}

}
