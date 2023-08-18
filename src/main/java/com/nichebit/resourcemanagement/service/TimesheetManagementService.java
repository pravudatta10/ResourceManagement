package com.nichebit.resourcemanagement.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.dto.SendMailRequest;
import com.nichebit.resourcemanagement.dto.SumOfHrsInDaysDto;
import com.nichebit.resourcemanagement.dto.TasksResponse;
import com.nichebit.resourcemanagement.dto.TimeSheetDaysAndHoursRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetDaysAndHoursResponse;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementRequest;
import com.nichebit.resourcemanagement.dto.TimeSheetManagementResponse;
import com.nichebit.resourcemanagement.dto.TimesheetDaysAndHolidaysResponse;
import com.nichebit.resourcemanagement.entity.Employee;
import com.nichebit.resourcemanagement.entity.TimesheetManagement;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;
import com.nichebit.resourcemanagement.repository.HolidayMasterRepository;
import com.nichebit.resourcemanagement.repository.ProjectRepository;
import com.nichebit.resourcemanagement.repository.TaskManagementRepository;
import com.nichebit.resourcemanagement.repository.TimeSheetManagementRepository;

import freemarker.template.Template;

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

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Autowired
	private SendMailService sendMailService;

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
		LocalDate date = LocalDate.of(year, monthEnum.getValue(), day);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.getDefault());
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
			List<TimesheetDaysAndHolidaysResponse> HolidayList = new ArrayList<TimesheetDaysAndHolidaysResponse>();

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
					HolidayList, tsmr.getStatus(), tsmr.getSubmittedon(), tsmr.getApprovedon(), tr);
			tsdh.add(timeSheetManagementResponse);

		}

		return tsdh;
	}

	public List<TimeSheetManagementResponse> alltimesheets(String name, int financialyear, String month) {

		long id = employeeRepository.findempnamebyempid(name);
		System.out.println(employeeRepository.findempnamebyempid(name));

		List<TimeSheetManagementResponse> tsdh = new ArrayList<>();
		List<TimesheetManagement> tsmrl = timeSheetManagementRepository.findByempidmonthfy(id, financialyear, month);
		for (TimesheetManagement tsmr : tsmrl) {
			

			Long projectId = projectRepository.findProjectIdByName(tsmr.getProject());

			List<String> Tasks = taskManagementRepository.findByPid(projectId);

			List<TasksResponse> tr = new ArrayList<TasksResponse>();
			for (String task : Tasks) {

				TasksResponse trs = new TasksResponse();
				trs.setTask(task);

				tr.add(trs);

			}

			List<TimeSheetDaysAndHoursResponse> timeSheetDaysAndHoursResponse = new ArrayList<TimeSheetDaysAndHoursResponse>();

			List<TimesheetDaysAndHolidaysResponse> HolidayList = new ArrayList<TimesheetDaysAndHolidaysResponse>();
			// int days =
			// TimesheetManagementService.getNumberOfDaysInMonth(tsmr.getFinancialyear(),
			// tsmr.getMonth());

			int completedDays;
			int monNoForReqTS = getMonthNumber(tsmr.getMonth());
			LocalDate currentDate = LocalDate.now();
			Month currentMonth = currentDate.getMonth();
			int monthValue = currentMonth.getValue();
			int currentYear = currentDate.getYear();

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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					String HolidayType = holidayMasterRepository.findByDateAndClient(date, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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

					String daate = getFormattedDate(tsmr.getFinancialyear(), tsmr.getMonth(), i);
					String HolidayType = holidayMasterRepository.findByDateAndClient(daate, tsmr.getClient());
					String htype = "";
					if (HolidayType == null || ("").equals(HolidayType)) {
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
					tsmr.getRemarks(), tsmr.getFinancialyear(), tsmr.getMonth(), timeSheetDaysAndHoursResponse,
					HolidayList, tsmr.getStatus(), tsmr.getSubmittedon(), tsmr.getApprovedon(), tr);
			tsdh.add(timeSheetManagementResponse);

		}

		return tsdh;
	}

	@Scheduled(cron = "0 0 9 * * *")
	public void sendmailForTimesheet() throws Exception {
		try {
			LocalDate currentDate = LocalDate.now();
			int currentDay = currentDate.getDayOfMonth();
			DayOfWeek previousdayOfWeek = currentDate.withDayOfMonth(currentDay - 1).getDayOfWeek();
			String previousDayOfWeekString = previousdayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			String currentMonth = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			int currentYear = currentDate.getYear();
			int previousdays = currentDay - 1;
			if (!previousDayOfWeekString.equals("Sunday") || (!previousDayOfWeekString.equals("Saturday"))) {
				List<Long> empidfromEmployee = employeeRepository.allEmpid();
				for (long id : empidfromEmployee) {
					Employee employee = employeeRepository.findByempidforTS(id);
					freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
					Template template = configuration.getTemplate("TimeSheetReminder.ftl");
					SendMailRequest sendMailRequest = new SendMailRequest();
					sendMailRequest.setFrom("apparao.m@nichebit.com");
					sendMailRequest.setName(employee.getEmpname());
					sendMailRequest.setSubject(employee.getEmpname() + " Please Fill Yesterday Timesheet");
					sendMailRequest.setTo(employee.getEmail());
					Map<String, String> model = new HashMap<String, String>();
					model.put("UserName", employee.getEmpname());
					model.put("yesterday", previousDayOfWeekString);
					SumOfHrsInDaysDto ts = timeSheetManagementRepository.findByempTS(id, currentYear, currentMonth);
					if (ts != null) {
						for (int day = 1; day <= 31; day++) {
							if (day == 1 && day == previousdays && ts.getDay01() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 2 && day == previousdays && ts.getDay02() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 3 && day == previousdays && ts.getDay03() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 4 && day == previousdays && ts.getDay04() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 5 && day == previousdays && ts.getDay05() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 6 && day == previousdays && ts.getDay06() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 7 && day == previousdays && ts.getDay07() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 8 && day == previousdays && ts.getDay08() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 9 && day == previousdays && ts.getDay09() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 10 && day == previousdays && ts.getDay10() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 11 && day == previousdays && ts.getDay11() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 12 && day == previousdays && ts.getDay12() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 13 && day == previousdays && ts.getDay13() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 14 && day == previousdays && ts.getDay14() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 15 && day == previousdays && ts.getDay15() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 16 && day == previousdays && ts.getDay16() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
								break;
							}
							if (day == 17 && day == previousdays && ts.getDay17() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 18 && day == previousdays && ts.getDay18() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 19 && day == previousdays && ts.getDay19() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 20 && day == previousdays && ts.getDay20() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 21 && day == previousdays && ts.getDay21() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 22 && day == previousdays && ts.getDay22() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 23 && day == previousdays && ts.getDay23() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 24 && day == previousdays && ts.getDay24() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 25 && day == previousdays && ts.getDay25() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 26 && day == previousdays && ts.getDay26() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 27 && day == previousdays && ts.getDay27() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 28 && day == previousdays && ts.getDay28() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 29 && day == previousdays && ts.getDay29() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 30 && day == previousdays && ts.getDay30() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}
							if (day == 31 && day == previousdays && ts.getDay31() == 0.0) {
								sendMailService.sendMail(sendMailRequest, model, template);
							}

						}

					} else {
						sendMailService.sendMail(sendMailRequest, model, template);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void mailScheduler(Employee employee, Template template) throws
	 * Exception { try { LocalDate currentDate = LocalDate.now(); int currentDay =
	 * currentDate.getDayOfMonth(); DayOfWeek previousDayOfWeek =
	 * currentDate.withDayOfMonth(currentDay - 1).getDayOfWeek(); String
	 * previousDayOfWeekString = previousDayOfWeek.getDisplayName(TextStyle.FULL,
	 * Locale.ENGLISH); SendMailRequest sendMailRequest = new SendMailRequest();
	 * sendMailRequest.setFrom("apparao.m@nichebit.com");
	 * sendMailRequest.setName(employee.getEmpname());
	 * sendMailRequest.setSubject(employee.getEmpname() +
	 * " Please Fill Yesterday Timesheet");
	 * sendMailRequest.setTo(employee.getEmail()); Map<String, String> model = new
	 * HashMap<String, String>(); model.put("UserName", employee.getEmpname());
	 * model.put("yesterday", previousDayOfWeekString);
	 * sendMailService.sendMail(sendMailRequest, model, template);
	 * System.out.println("Timesheet Mail Send" + employee.getEmpname());
	 * 
	 * } catch (Exception e) { System.out.println("Timesheet Mail Send Failed" +
	 * employee.getEmpname()); } }
	 */
}
