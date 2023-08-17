package com.nichebit.resourcemanagement.controller;

import java.io.FileNotFoundException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.DocManagementRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementResponse;
import com.nichebit.resourcemanagement.service.TimesheetManagementService;
import com.nichebit.resourcemanagement.service.UtilitysServices;

@RestController
@RequestMapping("/timesheet")
public class TimesheetManagementController {

	@Autowired
	TimesheetManagementService timesheetManagementService;
	
	@Autowired
	UtilitysServices utilitysServices ;

	@PostMapping("/add")
	public List<ResponseEntity<?>> saveTimesheetManagement(
			@RequestBody List<TimeSheetManagementRequest> timeSheetManagementRequest) {

		List<ResponseEntity<?>> timesheetresponse = new ArrayList<ResponseEntity<?>>();
		for (TimeSheetManagementRequest request : timeSheetManagementRequest) {
			ResponseEntity<?> rr = timesheetManagementService.savetimsheet(request);
			timesheetresponse.add(rr);
		}
		return timesheetresponse;

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateTimesheetManagement(
			@RequestBody TimeSheetManagementRequest timeSheetManagementRequest) {
		return timesheetManagementService.updatetimsheet(timeSheetManagementRequest);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTimesheetManagement(@PathVariable Long id) {
		return timesheetManagementService.deletetimsheet(id);

	}

	@GetMapping("/all")
	public List<TimeSheetManagementResponse> getallTimesheetManagement(@RequestParam("name") String name,
			@RequestParam("financialyear") int financialyear, @RequestParam("month") String month) {
		return timesheetManagementService.alltimesheet(name, financialyear, month);

	}
	
	@GetMapping("/timesheetbyid")
	public List<TimeSheetManagementResponse> getallTimesheetManagements(@RequestParam("name") String name,
			@RequestParam("financialyear") int financialyear, @RequestParam("month") String month) {
		return timesheetManagementService.alltimesheets(name, financialyear, month);

	}
	 
	@PostMapping("/excel")
	public String  ExcelTimeSheet(@RequestParam("name") String name,@RequestParam("financialyear") int financialyear,@RequestParam("month") String month) throws FileNotFoundException
	{
		return utilitysServices.excelForTimeSheet(name,financialyear,month);
		
	}

}
