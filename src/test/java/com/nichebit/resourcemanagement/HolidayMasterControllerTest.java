package com.nichebit.resourcemanagement;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nichebit.resourcemanagement.controller.HolidayMasterController;
import com.nichebit.resourcemanagement.dto.HolidayMasterResponse;
import com.nichebit.resourcemanagement.service.HolidayMasterService;

@WebMvcTest(HolidayMasterController.class)
public class HolidayMasterControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private HolidayMasterService holidayMasterService;
	HolidayMasterResponse holidayMasterOne;
	HolidayMasterResponse holidayMasterTwo;
	List<HolidayMasterResponse> holidayMasterList=new ArrayList<>();
	@BeforeEach
	void setUp() {
		holidayMasterOne=new HolidayMasterResponse(1L, "abc",  new Date(), "weekly","ok"); 
		holidayMasterTwo=new HolidayMasterResponse(2L, "cdf",  new Date(), "monthly","ok");
		holidayMasterList.add(holidayMasterOne);
		holidayMasterList.add(holidayMasterTwo);
	}
	
	@Test
	void testGetHolidayMasters() throws Exception {
		when(holidayMasterService.getHolidayMaster())
		.thenReturn(holidayMasterList);
		this.mockMvc.perform(get("/holidaymaster")).andDo(print()).andExpect(status().isOk());
	}
	
}



