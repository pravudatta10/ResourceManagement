package com.nichebit.resourcemanagement.controller;

import java.util.ArrayList;
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

import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementResponse;
import com.nichebit.resourcemanagement.service.TimesheetManagementService;

@RestController
@RequestMapping("/timesheet")
public class TimesheetManagementController {

	@Autowired
	TimesheetManagementService timesheetManagementService;

	@PostMapping("/add")
	public List<TimeSheetManagementResponse> addTimesheetManagement(@RequestBody List<TimeSheetManagementRequest> timeSheetManagementRequests) {

	    List<TimeSheetManagementResponse> timesheetResponses = new ArrayList<>();
	    for (TimeSheetManagementRequest request : timeSheetManagementRequests) {
	        timesheetResponses.add(timesheetManagementService.savetimsheet(request));
	    }
	    return timesheetResponses;
	}


	@PutMapping("/update")
	public List<ResponseEntity<?>> updateTimesheetManagement(@RequestBody List<TimeSheetManagementRequest> timeSheetManagementRequest) {

		List<ResponseEntity<?>> timesheetresponse=new ArrayList<ResponseEntity<?>>();
		for(TimeSheetManagementRequest request:timeSheetManagementRequest) {
			ResponseEntity<?> rr=	timesheetManagementService.updatetimsheet(request);
			timesheetresponse.add(rr);
		}
		return timesheetresponse;

	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTimesheetManagement(@PathVariable Long id) {
		return timesheetManagementService.deletetimsheet(id);

	}
	
//	@GetMapping("/all")
//	public List<TimeSheetManagementResponse> getallTimesheetManagement() {
//		return timesheetManagementService.alltimesheet();
//		
//	}
	
	@GetMapping("/alls")
	public void getallTimesheetdaysandhours(TimeSheetManagementRequest timeSheetManagementRequest) {
		 timesheetManagementService.timesheetTimesheet(timeSheetManagementRequest);
	}
	

}
