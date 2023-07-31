package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.TimeSheetManagementRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementResponse;
import com.nichebit.resourcemanagement.entity.TimesheetManagement;
import com.nichebit.resourcemanagement.repository.TimeSheetManagementRepository;

@Service
public class TimesheetManagementService {

	@Autowired
	TimeSheetManagementRepository timeSheetManagementRepository;

	public String savetimsheet(TimeSheetManagementRequest timeSheetManagementRequest) {
		TimesheetManagement timesheetManagement = new TimesheetManagement();
		timesheetManagement.setEmpid(timeSheetManagementRequest.getEmpid());
		timesheetManagement.setReportingmanager(timeSheetManagementRequest.getReportingmanager());
		timesheetManagement.setProject(timeSheetManagementRequest.getProject());
		timesheetManagement.setTask(timeSheetManagementRequest.getTask());
		timesheetManagement.setClient(timeSheetManagementRequest.getClient());
		timesheetManagement.setRemarks(timeSheetManagementRequest.getRemarks());
		timesheetManagement.setFinancialyear(timeSheetManagementRequest.getFinancialyear());
		timesheetManagement.setMonth_name(timeSheetManagementRequest.getMonth_name());
		timesheetManagement.setDay01(timeSheetManagementRequest.getDay01());
		timesheetManagement.setDay02(timeSheetManagementRequest.getDay02());
		timesheetManagement.setDay03(timeSheetManagementRequest.getDay03());
		timesheetManagement.setDay04(timeSheetManagementRequest.getDay04());
		timesheetManagement.setDay05(timeSheetManagementRequest.getDay05());
		timesheetManagement.setDay06(timeSheetManagementRequest.getDay06());
		timesheetManagement.setDay07(timeSheetManagementRequest.getDay07());
		timesheetManagement.setDay08(timeSheetManagementRequest.getDay08());
		timesheetManagement.setDay09(timeSheetManagementRequest.getDay09());
		timesheetManagement.setDay10(timeSheetManagementRequest.getDay10());
		timesheetManagement.setDay11(timeSheetManagementRequest.getDay11());
		timesheetManagement.setDay12(timeSheetManagementRequest.getDay12());
		timesheetManagement.setDay13(timeSheetManagementRequest.getDay13());
		timesheetManagement.setDay14(timeSheetManagementRequest.getDay14());
		timesheetManagement.setDay15(timeSheetManagementRequest.getDay15());
		timesheetManagement.setDay16(timeSheetManagementRequest.getDay16());
		timesheetManagement.setDay17(timeSheetManagementRequest.getDay17());
		timesheetManagement.setDay18(timeSheetManagementRequest.getDay18());
		timesheetManagement.setDay19(timeSheetManagementRequest.getDay19());
		timesheetManagement.setDay20(timeSheetManagementRequest.getDay20());
		timesheetManagement.setDay21(timeSheetManagementRequest.getDay21());
		timesheetManagement.setDay22(timeSheetManagementRequest.getDay22());
		timesheetManagement.setDay23(timeSheetManagementRequest.getDay23());
		timesheetManagement.setDay24(timeSheetManagementRequest.getDay24());
		timesheetManagement.setDay25(timeSheetManagementRequest.getDay25());
		timesheetManagement.setDay26(timeSheetManagementRequest.getDay26());
		timesheetManagement.setDay27(timeSheetManagementRequest.getDay27());
		timesheetManagement.setDay28(timeSheetManagementRequest.getDay28());
		timesheetManagement.setDay29(timeSheetManagementRequest.getDay29());
		timesheetManagement.setDay30(timeSheetManagementRequest.getDay30());
		timesheetManagement.setDay31(timeSheetManagementRequest.getDay31());
		timesheetManagement.setStatus(timeSheetManagementRequest.getStatus());
		timesheetManagement.setSubmittedon(timeSheetManagementRequest.getSubmittedon());
		timesheetManagement.setApprovedon(timeSheetManagementRequest.getApprovedon());
		timeSheetManagementRepository.save(timesheetManagement);
		return "TimeSheet Saved Successfully";
	}

	public String updatetimsheet(TimeSheetManagementRequest timeSheetManagementRequest) {
		TimesheetManagement timesheetManagement = timeSheetManagementRepository
				.findById(timeSheetManagementRequest.getId()).orElse(null);
		timesheetManagement.setEmpid(timeSheetManagementRequest.getEmpid());
		timesheetManagement.setReportingmanager(timeSheetManagementRequest.getReportingmanager());
		timesheetManagement.setProject(timeSheetManagementRequest.getProject());
		timesheetManagement.setTask(timeSheetManagementRequest.getTask());
		timesheetManagement.setClient(timeSheetManagementRequest.getClient());
		timesheetManagement.setRemarks(timeSheetManagementRequest.getRemarks());
		timesheetManagement.setFinancialyear(timeSheetManagementRequest.getFinancialyear());
		timesheetManagement.setMonth_name(timeSheetManagementRequest.getMonth_name());
		timesheetManagement.setDay01(timeSheetManagementRequest.getDay01());
		timesheetManagement.setDay02(timeSheetManagementRequest.getDay02());
		timesheetManagement.setDay03(timeSheetManagementRequest.getDay03());
		timesheetManagement.setDay04(timeSheetManagementRequest.getDay04());
		timesheetManagement.setDay05(timeSheetManagementRequest.getDay05());
		timesheetManagement.setDay06(timeSheetManagementRequest.getDay06());
		timesheetManagement.setDay07(timeSheetManagementRequest.getDay07());
		timesheetManagement.setDay08(timeSheetManagementRequest.getDay08());
		timesheetManagement.setDay09(timeSheetManagementRequest.getDay09());
		timesheetManagement.setDay10(timeSheetManagementRequest.getDay10());
		timesheetManagement.setDay11(timeSheetManagementRequest.getDay11());
		timesheetManagement.setDay12(timeSheetManagementRequest.getDay12());
		timesheetManagement.setDay13(timeSheetManagementRequest.getDay13());
		timesheetManagement.setDay14(timeSheetManagementRequest.getDay14());
		timesheetManagement.setDay15(timeSheetManagementRequest.getDay15());
		timesheetManagement.setDay16(timeSheetManagementRequest.getDay16());
		timesheetManagement.setDay17(timeSheetManagementRequest.getDay17());
		timesheetManagement.setDay18(timeSheetManagementRequest.getDay18());
		timesheetManagement.setDay19(timeSheetManagementRequest.getDay19());
		timesheetManagement.setDay20(timeSheetManagementRequest.getDay20());
		timesheetManagement.setDay21(timeSheetManagementRequest.getDay21());
		timesheetManagement.setDay22(timeSheetManagementRequest.getDay22());
		timesheetManagement.setDay23(timeSheetManagementRequest.getDay23());
		timesheetManagement.setDay24(timeSheetManagementRequest.getDay24());
		timesheetManagement.setDay25(timeSheetManagementRequest.getDay25());
		timesheetManagement.setDay26(timeSheetManagementRequest.getDay26());
		timesheetManagement.setDay27(timeSheetManagementRequest.getDay27());
		timesheetManagement.setDay28(timeSheetManagementRequest.getDay28());
		timesheetManagement.setDay29(timeSheetManagementRequest.getDay29());
		timesheetManagement.setDay30(timeSheetManagementRequest.getDay30());
		timesheetManagement.setDay31(timeSheetManagementRequest.getDay31());
		timesheetManagement.setStatus(timeSheetManagementRequest.getStatus());
		timesheetManagement.setSubmittedon(timeSheetManagementRequest.getSubmittedon());
		timesheetManagement.setApprovedon(timeSheetManagementRequest.getApprovedon());
		timeSheetManagementRepository.save(timesheetManagement);
		return "TimeSheet Updated Successfully";
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
