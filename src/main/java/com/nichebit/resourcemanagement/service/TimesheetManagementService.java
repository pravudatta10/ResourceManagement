package com.nichebit.resourcemanagement.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.dto.TimeSheetDaysAndHoursRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetDaysAndHoursResponse;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementResponse;
import com.nichebit.resourcemanagement.entity.TimesheetManagement;
import com.nichebit.resourcemanagement.repository.TimeSheetManagementRepository;

@Service
public class TimesheetManagementService {

	@Autowired
	TimeSheetManagementRepository timeSheetManagementRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	ReturnResponse returnResponse = new ReturnResponse();
	
	public static int getNumberOfDaysInMonth(int year, String month) {
        Month monthEnum = Month.valueOf(month.toUpperCase());
        return YearMonth.of(year, monthEnum).lengthOfMonth();
    }
	
	
	 public static String getDayName(int year, String  month, int day) {
		  Month monthEnum = Month.valueOf(month.toUpperCase());
	        LocalDate date = LocalDate.of(year, monthEnum, day);
	        DayOfWeek dayOfWeek = date.getDayOfWeek();
	        return dayOfWeek.toString();
	    }

	public TimeSheetManagementResponse savetimsheet(TimeSheetManagementRequest timeSheetManagementRequest) {

		TimesheetManagement timesheetManagement = new TimesheetManagement();
		timesheetManagement.setEmpid(timeSheetManagementRequest.getEmpid());
		timesheetManagement.setReportingmanager(timeSheetManagementRequest.getReportingmanager());
		timesheetManagement.setProject(timeSheetManagementRequest.getProject());
		timesheetManagement.setTask(timeSheetManagementRequest.getTask());
		timesheetManagement.setClient(timeSheetManagementRequest.getClient());
		timesheetManagement.setRemarks(timeSheetManagementRequest.getRemarks());
		timesheetManagement.setFinancialyear(timeSheetManagementRequest.getFinancialyear());
		timesheetManagement.setMonth(timeSheetManagementRequest.getMonth());
		List<TimeSheetDaysAndHoursRequest> Datas = timeSheetManagementRequest.getDateAndDayArray();
		for (TimeSheetDaysAndHoursRequest ts : Datas) {
			if (ts.getDate() == 1) {
				timesheetManagement.setDay01(ts.getTime());
			} else if (ts.getDate() == 2) {
				timesheetManagement.setDay02(ts.getTime());
			} else if (ts.getDate() == 3) {
				timesheetManagement.setDay03(ts.getTime());
			} else if (ts.getDate() == 4) {
				timesheetManagement.setDay04(ts.getTime());
			} else if (ts.getDate() == 5) {
				timesheetManagement.setDay05(ts.getTime());
			} else if (ts.getDate() == 6) {
				timesheetManagement.setDay06(ts.getTime());
			} else if (ts.getDate() == 7) {
				timesheetManagement.setDay07(ts.getTime());
			} else if (ts.getDate() == 8) {
				timesheetManagement.setDay08(ts.getTime());
			} else if (ts.getDate() == 9) {
				timesheetManagement.setDay09(ts.getTime());
			} else if (ts.getDate() == 10) {
				timesheetManagement.setDay10(ts.getTime());
			} else if (ts.getDate() == 11) {
				timesheetManagement.setDay11(ts.getTime());
			} else if (ts.getDate() == 12) {
				timesheetManagement.setDay12(ts.getTime());
			} else if (ts.getDate() == 13) {
				timesheetManagement.setDay13(ts.getTime());
			} else if (ts.getDate() == 14) {
				timesheetManagement.setDay14(ts.getTime());
			} else if (ts.getDate() == 15) {
				timesheetManagement.setDay15(ts.getTime());
			} else if (ts.getDate() == 16) {
				timesheetManagement.setDay16(ts.getTime());
			} else if (ts.getDate() == 17) {
				timesheetManagement.setDay17(ts.getTime());
			} else if (ts.getDate() == 18) {
				timesheetManagement.setDay18(ts.getTime());
			} else if (ts.getDate() == 19) {
				timesheetManagement.setDay19(ts.getTime());
			} else if (ts.getDate() == 20) {
				timesheetManagement.setDay20(ts.getTime());
			} else if (ts.getDate() == 21) {
				timesheetManagement.setDay21(ts.getTime());
			} else if (ts.getDate() == 22) {
				timesheetManagement.setDay22(ts.getTime());
			} else if (ts.getDate() == 23) {
				timesheetManagement.setDay23(ts.getTime());
			} else if (ts.getDate() == 24) {
				timesheetManagement.setDay24(ts.getTime());
			} else if (ts.getDate() == 25) {
				timesheetManagement.setDay25(ts.getTime());
			} else if (ts.getDate() == 26) {
				timesheetManagement.setDay26(ts.getTime());
			} else if (ts.getDate() == 27) {
				timesheetManagement.setDay27(ts.getTime());
			} else if (ts.getDate() == 28) {
				timesheetManagement.setDay28(ts.getTime());
			} else if (ts.getDate() == 29) {
				timesheetManagement.setDay29(ts.getTime());
			} else if (ts.getDate() == 30) {
				timesheetManagement.setDay30(ts.getTime());
			} else if (ts.getDate() == 31) {
				timesheetManagement.setDay31(ts.getTime());
			}
		}

		timesheetManagement.setStatus(timeSheetManagementRequest.getStatus());
		timesheetManagement.setSubmittedon(timeSheetManagementRequest.getSubmittedon());
		timesheetManagement.setApprovedon(timeSheetManagementRequest.getApprovedon());
		timeSheetManagementRepository.save(timesheetManagement);

		

		TimeSheetManagementResponse timeSheetManagementResponse = new TimeSheetManagementResponse();
		timeSheetManagementResponse.setId(timesheetManagement.getId());
		timeSheetManagementResponse.setEmpid(timesheetManagement.getEmpid());
		timeSheetManagementResponse.setReportingmanager(timesheetManagement.getReportingmanager());
		timeSheetManagementResponse.setProject(timesheetManagement.getProject());
		timeSheetManagementResponse.setTask(timesheetManagement.getTask());
		timeSheetManagementResponse.setClient(timesheetManagement.getClient());
		timeSheetManagementResponse.setRemarks(timesheetManagement.getRemarks());
		timeSheetManagementResponse.setFinancialyear(timesheetManagement.getFinancialyear());
		timeSheetManagementResponse.setMonth(timesheetManagement.getMonth());

		List<TimeSheetDaysAndHoursResponse> timeSheetDaysAndHoursResponse = new ArrayList<TimeSheetDaysAndHoursResponse>();
		
		
		System.out.println("year"+""+timeSheetManagementRequest.getFinancialyear()+timeSheetManagementRequest.getMonth());
		
		
		int days=TimesheetManagementService.getNumberOfDaysInMonth(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth());
		
		System.out.println("days"+days);
		for(int i=1;i<=days;i++)
		{
		if(i==1 && timesheetManagement.getDay01() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay01());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==2 && timesheetManagement.getDay02() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay02());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		
		if(i==3 && timesheetManagement.getDay03() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay03());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==4 && timesheetManagement.getDay04() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay04());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==5 && timesheetManagement.getDay05() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay05());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==6 && timesheetManagement.getDay06() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay06());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==7 && timesheetManagement.getDay07() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay07());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==8 && timesheetManagement.getDay08() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay08());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		
		if(i==9 && timesheetManagement.getDay09() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay09());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==10 && timesheetManagement.getDay10() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay10());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==11 && timesheetManagement.getDay11() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay11());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==12 && timesheetManagement.getDay12() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay12());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		
		if(i==13 && timesheetManagement.getDay13() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay13());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==14 && timesheetManagement.getDay14() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay14());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==15 && timesheetManagement.getDay15() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay15());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==16 && timesheetManagement.getDay16() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay16());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==17 && timesheetManagement.getDay17() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay17());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==18 && timesheetManagement.getDay18() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay18());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==19 && timesheetManagement.getDay19() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay19());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==20 && timesheetManagement.getDay20() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay20());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==21 && timesheetManagement.getDay21() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay21());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==22 && timesheetManagement.getDay22() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay22());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==23 && timesheetManagement.getDay23() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay23());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==24 && timesheetManagement.getDay24() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay24());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==25 && timesheetManagement.getDay25() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay25());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==26 && timesheetManagement.getDay26() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay26());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==27 && timesheetManagement.getDay27() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay27());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==28 && timesheetManagement.getDay28() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay28());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==29 && timesheetManagement.getDay29() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay29());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==30 && timesheetManagement.getDay30() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay30());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		if(i==31 && timesheetManagement.getDay31() > 0)
		{
		TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
		String dayname=getDayName(timeSheetManagementRequest.getFinancialyear(),timeSheetManagementRequest.getMonth(),days);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(timesheetManagement.getDay31());
					timeSheetDaysAndHoursResponse.add(tsh);
					
		}
		}
		
		timeSheetManagementResponse.setDateAndDayArray(timeSheetDaysAndHoursResponse);

		timeSheetManagementResponse.setStatus(timesheetManagement.getStatus());
		timeSheetManagementResponse.setSubmittedon(timesheetManagement.getSubmittedon());
		timeSheetManagementResponse.setApprovedon(timesheetManagement.getApprovedon());

		return timeSheetManagementResponse;
	}

	public ResponseEntity<?> updatetimsheet(TimeSheetManagementRequest timeSheetManagementRequest) {
		TimesheetManagement timesheetManagement = timeSheetManagementRepository.findById(timeSheetManagementRequest.getId()).orElse(null);
		if (timesheetManagement == null) {
			returnResponse.setStatus("Timesheet Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
		
		timesheetManagement.setEmpid(timeSheetManagementRequest.getEmpid());
		timesheetManagement.setReportingmanager(timeSheetManagementRequest.getReportingmanager());
		timesheetManagement.setProject(timeSheetManagementRequest.getProject());
		timesheetManagement.setTask(timeSheetManagementRequest.getTask());
		timesheetManagement.setClient(timeSheetManagementRequest.getClient());
		timesheetManagement.setRemarks(timeSheetManagementRequest.getRemarks());
		timesheetManagement.setFinancialyear(timeSheetManagementRequest.getFinancialyear());
		timesheetManagement.setMonth(timeSheetManagementRequest.getMonth());
		List<TimeSheetDaysAndHoursRequest> Datas = timeSheetManagementRequest.getDateAndDayArray();
		for (TimeSheetDaysAndHoursRequest ts : Datas) {
			if (ts.getDate() == 1) {
				timesheetManagement.setDay01(ts.getTime());
			} else if (ts.getDate() == 2) {
				timesheetManagement.setDay02(ts.getTime());
			} else if (ts.getDate() == 3) {
				timesheetManagement.setDay03(ts.getTime());
			} else if (ts.getDate() == 4) {
				timesheetManagement.setDay04(ts.getTime());
			} else if (ts.getDate() == 5) {
				timesheetManagement.setDay05(ts.getTime());
			} else if (ts.getDate() == 6) {
				timesheetManagement.setDay06(ts.getTime());
			} else if (ts.getDate() == 7) {
				timesheetManagement.setDay07(ts.getTime());
			} else if (ts.getDate() == 8) {
				timesheetManagement.setDay08(ts.getTime());
			} else if (ts.getDate() == 9) {
				timesheetManagement.setDay09(ts.getTime());
			} else if (ts.getDate() == 10) {
				timesheetManagement.setDay10(ts.getTime());
			} else if (ts.getDate() == 11) {
				timesheetManagement.setDay11(ts.getTime());
			} else if (ts.getDate() == 12) {
				timesheetManagement.setDay12(ts.getTime());
			} else if (ts.getDate() == 13) {
				timesheetManagement.setDay13(ts.getTime());
			} else if (ts.getDate() == 14) {
				timesheetManagement.setDay14(ts.getTime());
			} else if (ts.getDate() == 15) {
				timesheetManagement.setDay15(ts.getTime());
			} else if (ts.getDate() == 16) {
				timesheetManagement.setDay16(ts.getTime());
			} else if (ts.getDate() == 17) {
				timesheetManagement.setDay17(ts.getTime());
			} else if (ts.getDate() == 18) {
				timesheetManagement.setDay18(ts.getTime());
			} else if (ts.getDate() == 19) {
				timesheetManagement.setDay19(ts.getTime());
			} else if (ts.getDate() == 20) {
				timesheetManagement.setDay20(ts.getTime());
			} else if (ts.getDate() == 21) {
				timesheetManagement.setDay21(ts.getTime());
			} else if (ts.getDate() == 22) {
				timesheetManagement.setDay22(ts.getTime());
			} else if (ts.getDate() == 23) {
				timesheetManagement.setDay23(ts.getTime());
			} else if (ts.getDate() == 24) {
				timesheetManagement.setDay24(ts.getTime());
			} else if (ts.getDate() == 25) {
				timesheetManagement.setDay25(ts.getTime());
			} else if (ts.getDate() == 26) {
				timesheetManagement.setDay26(ts.getTime());
			} else if (ts.getDate() == 27) {
				timesheetManagement.setDay27(ts.getTime());
			} else if (ts.getDate() == 28) {
				timesheetManagement.setDay28(ts.getTime());
			} else if (ts.getDate() == 29) {
				timesheetManagement.setDay29(ts.getTime());
			} else if (ts.getDate() == 30) {
				timesheetManagement.setDay30(ts.getTime());
			} else if (ts.getDate() == 31) {
				timesheetManagement.setDay31(ts.getTime());
			}
		}

		timesheetManagement.setStatus(timeSheetManagementRequest.getStatus());
		timesheetManagement.setSubmittedon(timeSheetManagementRequest.getSubmittedon());
		timesheetManagement.setApprovedon(timeSheetManagementRequest.getApprovedon());
		timeSheetManagementRepository.save(timesheetManagement);
		returnResponse.setStatus("TimeSheet Updated Successfully.");
		return ResponseEntity.ok(returnResponse);
	}

	public ResponseEntity<?> deletetimsheet(Long id) {
		TimesheetManagement timesheetManagement = timeSheetManagementRepository.findById(id).orElse(null);
		if(timesheetManagement == null)
		{
			returnResponse.setStatus("Timesheet Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
		returnResponse.setStatus("TimeSheet Deleted Successfully.");
		return ResponseEntity.ok(returnResponse);	}

	
/*	 public List<TimeSheetManagementResponse> alltimesheet() { 
		 return timeSheetManagementRepository.findAll().stream() .map(TimesheetManagement ->
	 new TimeSheetManagementResponse(TimesheetManagement.getId(),TimesheetManagement.getEmpid(),TimesheetManagement.getReportingmanager(),TimesheetManagement.getProject(),TimesheetManagement.getTask(),
			 TimesheetManagement.getClient(),TimesheetManagement.getRemarks(),TimesheetManagement.getFinancialyear(),TimesheetManagement.getMonth(),TimesheetManagement.getD
	  .toList());
	  
	  }*/
	

	public void timesheetTimesheet(TimeSheetManagementRequest timeSheetManagementRequest) {

		System.out.println("days" + timeSheetManagementRequest.getReportingmanager());
////		TimesheetManagement timesheetManagement = new TimesheetManagement();
////		timesheetManagement.setEmpid(timesheetRequestBygettingays.getEmpid());
////		timesheetManagement.setReportingmanager(timesheetRequestBygettingays.getReportingmanager());
////		timesheetManagement.setProject(timesheetRequestBygettingays.getProject());
////		timesheetManagement.setTask(timesheetRequestBygettingays.getTask());
////		timesheetManagement.setClient(timesheetRequestBygettingays.getClient());
////		timesheetManagement.setRemarks(timesheetRequestBygettingays.getRemarks());
////		timesheetManagement.setFinancialyear(timesheetRequestBygettingays.getFinancialyear());
////		timesheetManagement.setMonth_name(timesheetRequestBygettingays.getMonth_name());
//////		timesheetManagement.setDay01(timesheetRequestBygettingays.getDays().);
////		timesheetManagement.setDay02(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay02());
////		timesheetManagement.setDay03(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay03());
////		timesheetManagement.setDay04(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay04());
////		timesheetManagement.setDay05(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay05());
////		timesheetManagement.setDay06(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay06());
////		timesheetManagement.setDay07(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay07());
////		timesheetManagement.setDay08(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay08());
////		timesheetManagement.setDay09(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay09());
////		timesheetManagement.setDay10(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay10());
////		timesheetManagement.setDay11(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay11());
////		timesheetManagement.setDay12(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay12());
////		timesheetManagement.setDay13(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay13());
////		timesheetManagement.setDay14(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay14());
////		timesheetManagement.setDay15(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay15());
////		timesheetManagement.setDay16(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay16());
////		timesheetManagement.setDay17(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay17());
////		timesheetManagement.setDay18(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay18());
////		timesheetManagement.setDay19(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay19());
////		timesheetManagement.setDay20(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay20());
////		timesheetManagement.setDay21(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay21());
////		timesheetManagement.setDay22(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay22());
////		timesheetManagement.setDay23(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay23());
////		timesheetManagement.setDay24(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay24());
////		timesheetManagement.setDay25(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay25());
////		timesheetManagement.setDay26(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay26());
////		timesheetManagement.setDay27(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay27());
////		timesheetManagement.setDay28(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay28());
////		timesheetManagement.setDay29(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay29());
////		timesheetManagement.setDay30(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay30());
////		timesheetManagement.setDay31(timesheetRequestBygettingays.getTimeSheetDaysRequest().getDay31());
////		timesheetManagement.setStatus(timesheetRequestBygettingays.getStatus());
////		timesheetManagement.setSubmittedon(timesheetRequestBygettingays.getSubmittedon());
////		timesheetManagement.setApprovedon(timesheetRequestBygettingays.getApprovedon());
////		timeSheetManagementRepository.save(timesheetManagement);
	}

}
