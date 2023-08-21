package com.nichebit.resourcemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nichebit.resourcemanagement.dto.HolidayMasterRequest;
import com.nichebit.resourcemanagement.dto.HolidayMasterResponse;
import com.nichebit.resourcemanagement.service.HolidayMasterService;

@RestController
@RequestMapping("/holidaymaster")
public class HolidayMasterController {
	
	@Autowired
	HolidayMasterService holidayMasterService;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@RequestBody HolidayMasterRequest holidayMasterRequest) throws Exception {
		return holidayMasterService.saveHolidayMaster(holidayMasterRequest);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateHolidayMaster(@RequestBody HolidayMasterRequest holidayMasterRequest)
	{
		return holidayMasterService.updateHolidayMaster(holidayMasterRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteHolidayMaster(@PathVariable Long id)
	{
		return holidayMasterService.deleteHolidayMaster(id);
	}
	
	
	
	 public HolidayMasterController(HolidayMasterService holidayMasterService) {
	        this.holidayMasterService = holidayMasterService;
	    }
	@GetMapping("/all")
	public List<HolidayMasterResponse> getHolidayMasters()
	{
		return holidayMasterService.getHolidayMaster();
	}
}
