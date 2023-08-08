package com.nichebit.resourcemanagement.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import com.nichebit.resourcemanagement.repository.EmployeeRepository;
import com.nichebit.resourcemanagement.repository.TimeSheetManagementRepository;

@Service
public class TimesheetManagementService {

	@Autowired
	TimeSheetManagementRepository timeSheetManagementRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	EmployeeRepository employeeRepository;

	ReturnResponse returnResponse = new ReturnResponse();

	public static int getNumberOfDaysInMonth(int year, String month) {
		Month monthEnum = Month.valueOf(month.toUpperCase());
		return YearMonth.of(year, monthEnum).lengthOfMonth();
	}

	public static String getDayName(int year, String month, int day) {
		Month monthEnum = Month.valueOf(month.toUpperCase());
		LocalDate date = LocalDate.of(year, monthEnum, day);
		return date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
	}

	public ResponseEntity<?> savetimsheet(TimeSheetManagementRequest timeSheetManagementRequest) {

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
		returnResponse.setStatus("TimeSheet Saved Successfully.");
		return ResponseEntity.ok(returnResponse);
	}

	public ResponseEntity<?> updatetimsheet(TimeSheetManagementRequest timeSheetManagementRequest) {
		TimesheetManagement timesheetManagement = timeSheetManagementRepository
				.findById(timeSheetManagementRequest.getId()).orElse(null);
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
		if (timesheetManagement == null) {
			returnResponse.setStatus("Timesheet Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
		returnResponse.setStatus("TimeSheet Deleted Successfully.");
		return ResponseEntity.ok(returnResponse);
	}

	public List<TimeSheetManagementResponse> alltimesheet(String name, int financialyear, String month) {

		long id = employeeRepository.findempnamebyempid(name);

		List<TimeSheetManagementResponse> tsdh = new ArrayList<>();
		List<TimesheetManagement> tsmrl = timeSheetManagementRepository.findByempidmonthfy(id, financialyear, month);
		for (TimesheetManagement tsmr : tsmrl) {
			List<TimeSheetDaysAndHoursResponse> timeSheetDaysAndHoursResponse = new ArrayList<TimeSheetDaysAndHoursResponse>();
			int days = TimesheetManagementService.getNumberOfDaysInMonth(tsmr.getFinancialyear(), tsmr.getMonth());
			for (int i = 1; i <= days; i++) {
				if (i == 1) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay01());
					timeSheetDaysAndHoursResponse.add(tsh);
				}
				if (i == 2) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay02());
					timeSheetDaysAndHoursResponse.add(tsh);

				}

				if (i == 3) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay03());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 4) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay04());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 5) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay05());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 6) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay06());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 7) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay07());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 8) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay08());
					timeSheetDaysAndHoursResponse.add(tsh);

				}

				if (i == 9) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay09());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 10) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay10());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 11) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay11());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 12) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay12());
					timeSheetDaysAndHoursResponse.add(tsh);

				}

				if (i == 13) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay13());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 14) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay14());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 15) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay15());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 16) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay16());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 17) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay17());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 18) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay18());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 19) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay19());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 20) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay20());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 21) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay21());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 22) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay22());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 23) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay23());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 24) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay24());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 25) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay25());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 26) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay26());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 27) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay27());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 28) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay28());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 29) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay29());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 30) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay30());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
				if (i == 31) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay31());
					timeSheetDaysAndHoursResponse.add(tsh);

				}
			}
			TimeSheetManagementResponse timeSheetManagementResponse = new TimeSheetManagementResponse(tsmr.getId(),
					tsmr.getEmpid(), tsmr.getReportingmanager(), tsmr.getProject(), tsmr.getTask(), tsmr.getClient(),
					tsmr.getRemarks(), tsmr.getFinancialyear(), tsmr.getMonth(), timeSheetDaysAndHoursResponse,
					tsmr.getStatus(), tsmr.getSubmittedon(), tsmr.getApprovedon());
			tsdh.add(timeSheetManagementResponse);

		}
		return tsdh;

	}

}
