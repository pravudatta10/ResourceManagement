package com.nichebit.resourcemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nichebit.resourcemanagement.dto.JobCardManagementResponse;
import com.nichebit.resourcemanagement.service.JobCardManagementService;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
 class JobCardManagementControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private Authentication authentication; // Mock the Authentication object
    
    @MockBean
    private JobCardManagementService jobCardManagementService;
    
    @Mock
    JobCardManagementResponse jobCardResponse;
    
   
    List<JobCardManagementResponse> jobCardResponseList = new ArrayList<>();
    
    @BeforeEach
	void setUp()
	{
    	jobCardResponse = new JobCardManagementResponse(1L,1L,"P1","T1","C1",null,null,"R1","100","8","","",null,null,null);
    	jobCardResponseList.add(jobCardResponse);
    	jobCardResponse = new JobCardManagementResponse(2L,2L,"P2","T2","C2",null,null,"R2","100","8","","",null,null,null);
    	jobCardResponseList.add(jobCardResponse);
	}
    
    @Test
    @WithMockUser(username = "testuser", roles = "USER")
     void testProtectedEndpoint() throws Exception {
        Mockito.when(authentication.getName()).thenReturn("testuser");
        Mockito.when(jobCardManagementService.getJobCardManagents()).thenReturn(jobCardResponseList);
        mockMvc.perform(MockMvcRequestBuilders.get("/jobcard/all")
        .contentType(MediaType.APPLICATION_JSON))
           
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

}
