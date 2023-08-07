package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.TimeSheetManagementRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementResponse;
import com.nichebit.resourcemanagement.entity.DocManagement;
import com.nichebit.resourcemanagement.entity.TimesheetManagement;
import com.nichebit.resourcemanagement.repository.TimeSheetManagementRepository;

@Service
public class TimesheetManagementService {

	@Autowired
	TimeSheetManagementRepository timeSheetManagementRepository;

	@Autowired
	private ModelMapper modelMapper;
	public TimeSheetManagementResponse savetimsheet(TimeSheetManagementRequest timeSheetManagementRequest) {
		TimesheetManagement timesheetManagement = this.modelMapper.map(timeSheetManagementRequest,TimesheetManagement.class);
		
		timeSheetManagementRepository.save(timesheetManagement);
		return this.modelMapper.map(timesheetManagement,TimeSheetManagementResponse.class);
	}

	public TimeSheetManagementResponse updatetimsheet(TimeSheetManagementRequest timeSheetManagementRequest) {
		TimesheetManagement timesheetManagement = timeSheetManagementRepository
				.findById(timeSheetManagementRequest.getId()).orElse(null);
		if (timesheetManagement == null) {
			return null;
		}
		timesheetManagement = this.modelMapper.map(timeSheetManagementRequest,TimesheetManagement.class);
		timeSheetManagementRepository.save(timesheetManagement);
		return this.modelMapper.map(timesheetManagement,TimeSheetManagementResponse.class);
	}

	public String deletetimsheet(Long id) {
		timeSheetManagementRepository.deleteById(id);
		return "TimeSheet Deleted Successfully";
	}

	public List<TimeSheetManagementResponse> alltimesheet() {
		return timeSheetManagementRepository.findAll().stream()
				.map(TimesheetManagement -> new TimeSheetManagementResponse(TimesheetManagement.getId(),
						TimesheetManagement.getEmpid(), TimesheetManagement.getReportingmanager(),
						TimesheetManagement.getProject(), TimesheetManagement.getTask(),
						TimesheetManagement.getClient(), TimesheetManagement.getRemarks(),
						TimesheetManagement.getFinancialyear(), TimesheetManagement.getMonth_name(),
						TimesheetManagement.getDay01(), TimesheetManagement.getDay02(), TimesheetManagement.getDay03(),
						TimesheetManagement.getDay04(), TimesheetManagement.getDay05(), TimesheetManagement.getDay06(),
						TimesheetManagement.getDay07(), TimesheetManagement.getDay08(), TimesheetManagement.getDay09(),
						TimesheetManagement.getDay10(), TimesheetManagement.getDay11(), TimesheetManagement.getDay12(),
						TimesheetManagement.getDay13(), TimesheetManagement.getDay14(), TimesheetManagement.getDay15(),
						TimesheetManagement.getDay16(), TimesheetManagement.getDay17(), TimesheetManagement.getDay18(),
						TimesheetManagement.getDay19(), TimesheetManagement.getDay20(), TimesheetManagement.getDay21(),
						TimesheetManagement.getDay22(), TimesheetManagement.getDay23(), TimesheetManagement.getDay24(),
						TimesheetManagement.getDay25(), TimesheetManagement.getDay26(), TimesheetManagement.getDay27(),
						TimesheetManagement.getDay28(), TimesheetManagement.getDay29(), TimesheetManagement.getDay30(),
						TimesheetManagement.getDay31(), TimesheetManagement.getStatus(),
						TimesheetManagement.getSubmittedon(), TimesheetManagement.getApprovedon()))
				.toList();

	}

}
