package com.nichebit.resourcemanagement.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
import com.nichebit.resourcemanagement.dto.TasksResponse;
import com.nichebit.resourcemanagement.dto.TimeSheetDaysAndHoursRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetDaysAndHoursResponse;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementResponse;
import com.nichebit.resourcemanagement.dto.TimesheetDaysAndHolidaysResponse;
import com.nichebit.resourcemanagement.entity.TimesheetManagement;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;
import com.nichebit.resourcemanagement.repository.HolidayMasterRepository;
import com.nichebit.resourcemanagement.repository.ProjectRepository;
import com.nichebit.resourcemanagement.repository.TaskManagementRepository;
import com.nichebit.resourcemanagement.repository.TimeSheetManagementRepository;

@Service
public class TimesheetManagementService {

	@Autowired
	TimeSheetManagementRepository timeSheetManagementRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	HolidayMasterRepository holidayMasterRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TaskManagementRepository taskManagementRepository;

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

	public static int getMonthNumber(String monthName) {
		String normalizedMonthName = monthName.trim().toLowerCase(Locale.ENGLISH);

		for (Month month : Month.values()) {
			if (normalizedMonthName.equals(month.toString().toLowerCase(Locale.ENGLISH))) {
				return month.getValue();
			}
		}
		return -1;
	}

	public static String getFormattedDate(int year, String month, int day) {
		Month monthEnum = Month.valueOf(month.toUpperCase());
		LocalDate date = LocalDate.of(year, monthEnum, day);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy", Locale.getDefault());
		return date.format(formatter);
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
		} else {
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

	}

	public ResponseEntity<?> deletetimsheet(Long id) {
		TimesheetManagement timesheetManagement = timeSheetManagementRepository.findById(id).orElse(null);
		if (timesheetManagement == null) {
			returnResponse.setStatus("Timesheet Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
		timeSheetManagementRepository.delete(timesheetManagement);
		returnResponse.setStatus("TimeSheet Deleted Successfully.");
		return ResponseEntity.ok(returnResponse);
	}

	public List<TimeSheetManagementResponse> alltimesheet(String name, int financialyear, String month) {

		long id = employeeRepository.findempnamebyempid(name);

		List<TimeSheetManagementResponse> tsdh = new ArrayList<>();
		List<TimesheetManagement> tsmrl = timeSheetManagementRepository.findByempidmonthfy(id, financialyear, month);
		System.out.println(tsmrl);
		for (TimesheetManagement tsmr : tsmrl) {

			Long projectId = projectRepository.findProjectIdByName(tsmr.getProject());

			System.out.println("projectId" + projectId);

			List<String> Tasks = taskManagementRepository.findByPid(projectId);
			System.out.println("Tasks" + Tasks);

			List<TasksResponse> tr = new ArrayList<TasksResponse>();
			for (String task : Tasks) {

				TasksResponse trs = new TasksResponse();
				trs.setTask(task);

				tr.add(trs);
				System.out.println(tr);

			}
			List<TimesheetDaysAndHolidaysResponse>	HolidayList=new ArrayList<TimesheetDaysAndHolidaysResponse>();
			
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
					tsmr.getRemarks(), tsmr.getFinancialyear(), tsmr.getMonth(), timeSheetDaysAndHoursResponse,HolidayList,
					tsmr.getStatus(), tsmr.getSubmittedon(), tsmr.getApprovedon(), tr);
			tsdh.add(timeSheetManagementResponse);

		}

		return tsdh;
	}

	public List<TimeSheetManagementResponse> alltimesheets(String name, int financialyear, String month) {

		long id = employeeRepository.findempnamebyempid(name);

		List<TimeSheetManagementResponse> tsdh = new ArrayList<>();
		List<TimesheetManagement> tsmrl = timeSheetManagementRepository.findByempidmonthfy(id, financialyear, month);
		System.out.println(tsmrl);
		for (TimesheetManagement tsmr : tsmrl) {

			Long projectId = projectRepository.findProjectIdByName(tsmr.getProject());

			System.out.println("projectId" + projectId);

			List<String> Tasks = taskManagementRepository.findByPid(projectId);
			System.out.println("Tasks" + Tasks);

			List<TasksResponse> tr = new ArrayList<TasksResponse>();
			for (String task : Tasks) {

				TasksResponse trs = new TasksResponse();
				trs.setTask(task);

				tr.add(trs);
				System.out.println(tr);

			}

			List<TimeSheetDaysAndHoursResponse> timeSheetDaysAndHoursResponse = new ArrayList<TimeSheetDaysAndHoursResponse>();

			List<TimesheetDaysAndHolidaysResponse> HolidayList = new ArrayList<TimesheetDaysAndHolidaysResponse>();
			//int days = TimesheetManagementService.getNumberOfDaysInMonth(tsmr.getFinancialyear(), tsmr.getMonth());

			int completedDays;
			int monNoForReqTS = getMonthNumber(tsmr.getMonth());
			LocalDate currentDate = LocalDate.now();
			Month currentMonth = currentDate.getMonth();
			int monthValue = currentMonth.getValue();
			int currentYear = currentDate.getYear() % 100;

			if (currentYear == tsmr.getFinancialyear() && monNoForReqTS == monthValue) {
				completedDays = currentDate.getDayOfMonth();
			} else {
				completedDays = getNumberOfDaysInMonth(tsmr.getFinancialyear(), tsmr.getMonth());
			}
			for (int i = 1; i <= completedDays; i++) {
				if (i == 1) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay01());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay01());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 2) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay02());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay02());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}

				if (i == 3) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay03());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay03());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 4) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay04());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay04());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 5) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay05());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay05());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 6) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay06());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay06());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 7) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay07());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay07());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 8) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay08());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay08());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}

				if (i == 9) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay09());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay09());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 10) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay10());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay10());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 11) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay11());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay11());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 12) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay12());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay12());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}

				if (i == 13) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay13());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay13());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 14) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay14());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay14());
					hr.setH_type(htype);
					HolidayList.add(hr);
				}
				if (i == 15) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay15());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay15());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 16) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay16());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay16());
					hr.setH_type(htype);
					HolidayList.add(hr);
				}
				if (i == 17) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay17());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay17());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 18) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay18());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay18());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 19) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay19());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay19());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 20) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay20());
					timeSheetDaysAndHoursResponse.add(tsh);

					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay20());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 21) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay21());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay21());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 22) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay22());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay22());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 23) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay23());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay23());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 24) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay24());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay24());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 25) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay25());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay25());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 26) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay26());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay26());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 27) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay27());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay27());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 28) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay28());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay28());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 29) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay29());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay29());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 30) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay30());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay30());
					hr.setH_type(htype);
					HolidayList.add(hr);

				}
				if (i == 31) {
					TimeSheetDaysAndHoursResponse tsh = new TimeSheetDaysAndHoursResponse();
					String dayname = getDayName(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					tsh.setDate(i);
					tsh.setDayName(dayname);
					tsh.setTime(tsmr.getDay31());
					timeSheetDaysAndHoursResponse.add(tsh);
					TimesheetDaysAndHolidaysResponse hr = new TimesheetDaysAndHolidaysResponse();

					String date = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getMonth());
					String htype = "";
					if (HolidayType != null || ("").equals(HolidayType)) {
						htype = "";
					} else {
						htype = HolidayType;
					}
					hr.setDate(i);
					hr.setDayName(dayname);
					hr.setTime(tsmr.getDay31());
					hr.setH_type(htype);
					HolidayList.add(hr);
					
					

				}
			}
			TimeSheetManagementResponse timeSheetManagementResponse = new TimeSheetManagementResponse(tsmr.getId(),
					tsmr.getEmpid(), tsmr.getReportingmanager(), tsmr.getProject(), tsmr.getTask(), tsmr.getClient(),
					tsmr.getRemarks(), tsmr.getFinancialyear(), tsmr.getMonth(), timeSheetDaysAndHoursResponse,HolidayList,
					tsmr.getStatus(), tsmr.getSubmittedon(), tsmr.getApprovedon(), tr);
			tsdh.add(timeSheetManagementResponse);

		}

		return tsdh;
	}

}
