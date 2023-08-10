package com.nichebit.resourcemanagement.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.entity.TimesheetManagement;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;
import com.nichebit.resourcemanagement.repository.TimeSheetManagementRepository;

@Service
public class UtilitysServices {

	@Autowired
	TimeSheetManagementRepository timeSheetManagementRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public int getNumberOfDaysInMonth(int year, String month) {
		Month monthEnum = Month.valueOf(month.toUpperCase());
		return YearMonth.of(year, monthEnum).lengthOfMonth();
	}

	public String getDayName(int year, String month, int day) {
		Month monthEnum = Month.valueOf(month.toUpperCase());
		LocalDate date = LocalDate.of(year, monthEnum, day);
		return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
	}

	public String excelForTimeSheet(String name, int financialyear, String month) throws FileNotFoundException {

		
		String filePath = "C:\\Users\\kpds0\\Desktop\\ServerPath\\" +"TimeSheet Of "+month+".xlsx";
		System.out.println(filePath);

		// days and date
		int days = getNumberOfDaysInMonth(financialyear, month);
		String[] Date = new String[31];
		String[] Day = new String[31];
		for (int day = 1; day <= days; day++) {
			Day[day - 1] = getDayName(financialyear, month, day);
			Date[day - 1] = day + "-" + month + "-" + financialyear;
		}

		// hrs and projects
		long id = employeeRepository.findempnamebyempid(name);

		List<TimesheetManagement> tsml = timeSheetManagementRepository.findByempidmonthfy(id, financialyear, month);
		String[] DayPro = new String[31];
		double[] DayHrs = new double[31];
		String Client = "";

		for (TimesheetManagement tsm : tsml) {
			Client = tsm.getClient();
			if (tsm.getDay01() > 0) {
		
				DayPro[0] = DayPro[0] + tsm.getProject() + ",";
				DayHrs[0] = DayHrs[0] + tsm.getDay01();
			}
			if (tsm.getDay02() > 0) {
				DayPro[1] = DayPro[1] + tsm.getProject() + ",";
				DayHrs[1] = DayHrs[1] + tsm.getDay02();
			}
			if (tsm.getDay03() > 0) {
				DayPro[2] = DayPro[2] + tsm.getProject() + ",";
				DayHrs[2] = DayHrs[2] + tsm.getDay03();
			}
			if (tsm.getDay04() > 0) {
				DayPro[3] = DayPro[3] + tsm.getProject() + ",";
				DayHrs[3] = DayHrs[3] + tsm.getDay04();
			}
			if (tsm.getDay05() > 0) {
				DayPro[4] = DayPro[4] + tsm.getProject() + ",";
				DayHrs[4] = DayHrs[4] + tsm.getDay05();
			}
			if (tsm.getDay06() > 0) {
				DayPro[5] = DayPro[5] + tsm.getProject() + ",";
				DayHrs[5] = DayHrs[5] + tsm.getDay06();
			}
			if (tsm.getDay07() > 0) {
				DayPro[6] = DayPro[6] + tsm.getProject() + ",";
				DayHrs[6] = DayHrs[6] + tsm.getDay07();
			}
			if (tsm.getDay08() > 0) {
				DayPro[7] = DayPro[7] + tsm.getProject() + ",";
				DayHrs[7] = DayHrs[7] + tsm.getDay08();
			}
			if (tsm.getDay09() > 0) {
				DayPro[8] = DayPro[8] + tsm.getProject() + ",";
				DayHrs[8] = DayHrs[8] + tsm.getDay09();
			}
			if (tsm.getDay10() > 0) {
				DayPro[9] = DayPro[9] + tsm.getProject() + ",";
				DayHrs[9] = DayHrs[9] + tsm.getDay10();
			}
			if (tsm.getDay11() > 0) {
				DayPro[10] = DayPro[10] + tsm.getProject() + ",";
				DayHrs[10] = DayHrs[10] + tsm.getDay11();
			}
			if (tsm.getDay12() > 0) {
				DayPro[11] = DayPro[11] + tsm.getProject() + ",";
				DayHrs[11] = DayHrs[11] + tsm.getDay12();
			}
			if (tsm.getDay13() > 0) {
				DayPro[12] = DayPro[12] + tsm.getProject() + ",";
				DayHrs[12] = DayHrs[12] + tsm.getDay13();
			}
			if (tsm.getDay14() > 0) {
				DayPro[13] = DayPro[13] + tsm.getProject() + ",";
				DayHrs[13] = DayHrs[13] + tsm.getDay14();
			}
			if (tsm.getDay15() > 0) {
				DayPro[14] = DayPro[14] + tsm.getProject() + ",";
				DayHrs[14] = DayHrs[14] + tsm.getDay15();
			}
			if (tsm.getDay16() > 0) {
				DayPro[15] = DayPro[15] + tsm.getProject() + ",";
				DayHrs[15] = DayHrs[15] + tsm.getDay16();
			}
			if (tsm.getDay17() > 0) {
				DayPro[16] = DayPro[16] + tsm.getProject() + ",";
				DayHrs[16] = DayHrs[16] + tsm.getDay17();
			}

			if (tsm.getDay18() > 0) {
				DayPro[17] = DayPro[17] + tsm.getProject() + ",";
				DayHrs[17] = DayHrs[17] + tsm.getDay18();
			}
			if (tsm.getDay19() > 0) {
				DayPro[18] = DayPro[18] + tsm.getProject() + ",";
				DayHrs[18] = DayHrs[18] + tsm.getDay19();
			}
			if (tsm.getDay20() > 0) {
				DayPro[19] = DayPro[19] + tsm.getProject() + ",";
				DayHrs[19] = DayHrs[19] + tsm.getDay20();
			}

			if (tsm.getDay21() > 0) {
				DayPro[20] = DayPro[20] + tsm.getProject() + ",";
				DayHrs[20] = DayHrs[20] + tsm.getDay21();
			}

			if (tsm.getDay22() > 0) {
				DayPro[21] = DayPro[21] + tsm.getProject() + ",";
				DayHrs[21] = DayHrs[21] + tsm.getDay22();
			}
			if (tsm.getDay23() > 0) {
				DayPro[22] = DayPro[22] + tsm.getProject() + ",";
				DayHrs[22] = DayHrs[22] + tsm.getDay23();
			}
			if (tsm.getDay24() > 0) {
				DayPro[23] = DayPro[23] + tsm.getProject() + ",";
				DayHrs[23] = DayHrs[23] + tsm.getDay24();
			}

			if (tsm.getDay25() > 0) {
				DayPro[24] = DayPro[24] + tsm.getProject() + ",";
				DayHrs[24] = DayHrs[24] + tsm.getDay25();
			}
			if (tsm.getDay26() > 0) {
				DayPro[25] = DayPro[25] + tsm.getProject() + ",";
				DayHrs[25] = DayHrs[25] + tsm.getDay26();
			}
			if (tsm.getDay27() > 0) {
				DayPro[26] = DayPro[26] + tsm.getProject() + ",";
				DayHrs[26] = DayHrs[26] + tsm.getDay27();
			}
			if (tsm.getDay28() > 0) {
				DayPro[27] = DayPro[27] + tsm.getProject() + ",";
				DayHrs[27] = DayHrs[27] + tsm.getDay28();
			}
			if (tsm.getDay29() > 0) {
				DayPro[28] = DayPro[28] + tsm.getProject() + ",";
				DayHrs[28] = DayHrs[28] + tsm.getDay29();
			}
			if (tsm.getDay30() > 0) {
				DayPro[29] = DayPro[29] + tsm.getProject() + ",";
				DayHrs[29] = DayHrs[29] + tsm.getDay30();
			}
			if (tsm.getDay31() > 0) {
				DayPro[30] = DayPro[30] + tsm.getProject() + ",";
				DayHrs[30] = DayHrs[30] + tsm.getDay31();
			}
		}

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("TimeSheet");

//createdfont
		XSSFFont font = wb.createFont();
		font.setBold(true);
//createdstyles
		XSSFCellStyle Styleforheader = wb.createCellStyle();
		Styleforheader.setBorderTop(BorderStyle.THICK);;
		Styleforheader.setFont(font);
		
		XSSFCellStyle Stylets = wb.createCellStyle();
		Stylets.setFont(font);

		XSSFCellStyle StyleForHeader = wb.createCellStyle();
		StyleForHeader.setFont(font);
		StyleForHeader.setAlignment(HorizontalAlignment.CENTER);
		StyleForHeader.setBorderTop(BorderStyle.THIN);
		StyleForHeader.setBorderBottom(BorderStyle.THIN);
		StyleForHeader.setBorderLeft(BorderStyle.THIN);
		StyleForHeader.setBorderRight(BorderStyle.THIN);

		XSSFCellStyle StyleNormalcoloums = wb.createCellStyle();

		StyleNormalcoloums.setFont(font);
		StyleNormalcoloums.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		StyleNormalcoloums.setFillPattern(FillPatternType.FINE_DOTS);
		StyleNormalcoloums.setAlignment(HorizontalAlignment.CENTER);
		StyleNormalcoloums.setBorderTop(BorderStyle.THIN);
		StyleNormalcoloums.setBorderBottom(BorderStyle.THIN);
		StyleNormalcoloums.setBorderLeft(BorderStyle.THIN);
		StyleNormalcoloums.setBorderRight(BorderStyle.THIN);
		StyleNormalcoloums.setWrapText(true);

		XSSFCellStyle StyleNormalcoloumsforweakoff = wb.createCellStyle();

		StyleNormalcoloumsforweakoff.setFont(font);
		StyleNormalcoloumsforweakoff.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		StyleNormalcoloumsforweakoff.setFillPattern(FillPatternType.FINE_DOTS);
		StyleNormalcoloumsforweakoff.setAlignment(HorizontalAlignment.CENTER);
		StyleNormalcoloumsforweakoff.setBorderTop(BorderStyle.THIN);
		StyleNormalcoloumsforweakoff.setBorderBottom(BorderStyle.THIN);
		StyleNormalcoloumsforweakoff.setBorderLeft(BorderStyle.THIN);
		StyleNormalcoloumsforweakoff.setBorderRight(BorderStyle.THIN);
		StyleNormalcoloums.setWrapText(true);

		String[] headerArray = { "Date", "Day", "Projects", "Hrs." };

		XSSFRow row1 = sheet.createRow(1);
		XSSFCell tscell = row1.createCell(2);
		tscell.setCellValue("Time Sheet");
		tscell.setCellStyle(Stylets);

		XSSFRow row3 = sheet.createRow(3);
		XSSFCell tscell1 = row3.createCell(0);
		tscell1.setCellValue("Nichebit Softech Pvt Ltd");
		tscell1.setCellStyle(StyleForHeader);

		XSSFRow row4 = sheet.createRow(4);
		XSSFCell tscell4 = row4.createCell(0);
		tscell4.setCellValue("Hyderabad");
		tscell4.setCellStyle(StyleForHeader);

		XSSFRow row5 = sheet.createRow(5);
		XSSFCell tscell5 = row5.createCell(0);
		tscell5.setCellValue("India");
		tscell5.setCellStyle(StyleForHeader);

		XSSFRow row8 = sheet.createRow(8);
		XSSFCell tscell8 = row8.createCell(0);
		tscell8.setCellValue("Resource Name");
		tscell8.setCellStyle(StyleForHeader);
		XSSFCell tscell81 = row8.createCell(1);
		tscell81.setCellValue(name);
		tscell81.setCellStyle(StyleForHeader);

		XSSFRow row9 = sheet.createRow(9);
		XSSFCell tscell9 = row9.createCell(0);
		tscell9.setCellValue("Client");
		tscell9.setCellStyle(StyleForHeader);
		XSSFCell tscell91 = row9.createCell(1);
		tscell91.setCellValue(Client);
		tscell91.setCellStyle(StyleForHeader);

		XSSFRow row10 = sheet.createRow(10);
		XSSFCell tscell10 = row10.createCell(0);
		tscell10.setCellValue("Project");
		tscell10.setCellStyle(StyleForHeader);
		XSSFCell tscell101 = row10.createCell(1);
		tscell101.setCellValue("");
		tscell101.setCellStyle(StyleForHeader);

		int Rowno = 12;

//setting headders
		XSSFRow row12 = sheet.createRow(Rowno);
		for (int i = 0; i<headerArray.length; i++) {
			
			XSSFCell cell = row12.createCell(i);
			cell.setCellValue(headerArray[i]);
			cell.setCellStyle(Styleforheader);

		}

		for (int i = 0; i < days; i++) {
			int cell = 0;
			XSSFRow row = sheet.createRow(++Rowno);
			String Project = "";

			if (DayPro[i] == null || "".equals(DayPro[i])) {
				Project = "";
			} else {
				Project = DayPro[i].substring(4, DayPro[i].length() - 1);
			}

			if ("Sunday".equals(Day[i]) ||"Saturday".equals(Day[i])) {
				row.createCell((short) cell).setCellValue(Day[i]);
				row.getCell(cell).setCellStyle(StyleNormalcoloumsforweakoff);
				row.createCell((short) ++cell).setCellValue(Date[i]);
				row.getCell(cell).setCellStyle(StyleNormalcoloumsforweakoff);
				row.createCell((short) ++cell).setCellValue(Project);
				row.getCell(cell).setCellStyle(StyleNormalcoloumsforweakoff);
				row.createCell((short) ++cell).setCellValue(DayHrs[i]);
				row.getCell(cell).setCellStyle(StyleNormalcoloumsforweakoff);
			} else {
				row.createCell((short) cell).setCellValue(Day[i]);
				row.getCell(cell).setCellStyle(StyleNormalcoloums);
				row.createCell((short) ++cell).setCellValue(Date[i]);
				row.getCell(cell).setCellStyle(StyleNormalcoloums);
				row.createCell((short) ++cell).setCellValue(Project);
				row.getCell(cell).setCellStyle(StyleNormalcoloums);
				row.createCell((short) ++cell).setCellValue(DayHrs[i]);
				row.getCell(cell).setCellStyle(StyleNormalcoloums);
			}
		}

		int cellforwidth = 0;

		sheet.setColumnWidth((short) cellforwidth, 7000);
		sheet.setColumnWidth((short) ++cellforwidth, 5000);
		sheet.setColumnWidth((short) ++cellforwidth, 15000);
		sheet.setColumnWidth((short) ++cellforwidth, 5000);

		FileOutputStream fout = new FileOutputStream(filePath);
		try {
			wb.write(fout);
			fout.flush();
			fout.close();
		} catch (Exception e) {
			throw new RuntimeException("Excel having some isuues" + e);
		}
		return filePath;

	}

}
