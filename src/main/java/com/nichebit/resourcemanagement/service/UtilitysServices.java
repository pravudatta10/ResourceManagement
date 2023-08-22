package com.nichebit.resourcemanagement.service;

import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.entity.TimesheetManagement;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;
import com.nichebit.resourcemanagement.repository.TimeSheetManagementRepository;

import io.jsonwebtoken.io.IOException;

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
	@Value("${server.path}")
	String folderPath;
	public String excelForTimeSheet(String name, int financialyear, String month) throws java.io.IOException { 
		// days and date
		int totalDays = getNumberOfDaysInMonth(financialyear, month);
		String[] date = new String[31];
		String[] days = new String[31];
		for (int day = 1; day <= totalDays; day++) {
			days[day - 1] = getDayName(financialyear, month, day);
			date[day - 1] = day + "-" + month + "-" + financialyear;
		}

		// hrs and projects
		long id = employeeRepository.findempnamebyempid(name);

		List<TimesheetManagement> tsml = timeSheetManagementRepository.findByempidmonthfy(id, financialyear, month);
		String[] dayPro = new String[31];
		double[] dayHrs = new double[31];
		String client = "";

		for (TimesheetManagement tsm : tsml) {
			client = tsm.getClient();
			if (tsm.getDay01() > 0) {

				dayPro[0] = dayPro[0] + tsm.getProject() + ",";
				dayHrs[0] = dayHrs[0] + tsm.getDay01();
			}
			if (tsm.getDay02() > 0) {
				dayPro[1] = dayPro[1] + tsm.getProject() + ",";
				dayHrs[1] = dayHrs[1] + tsm.getDay02();
			}
			if (tsm.getDay03() > 0) {
				dayPro[2] = dayPro[2] + tsm.getProject() + ",";
				dayHrs[2] = dayHrs[2] + tsm.getDay03();
			}
			if (tsm.getDay04() > 0) {
				dayPro[3] = dayPro[3] + tsm.getProject() + ",";
				dayHrs[3] = dayHrs[3] + tsm.getDay04();
			}
			if (tsm.getDay05() > 0) {
				dayPro[4] = dayPro[4] + tsm.getProject() + ",";
				dayHrs[4] = dayHrs[4] + tsm.getDay05();
			}
			if (tsm.getDay06() > 0) {
				dayPro[5] = dayPro[5] + tsm.getProject() + ",";
				dayHrs[5] = dayHrs[5] + tsm.getDay06();
			}
			if (tsm.getDay07() > 0) {
				dayPro[6] = dayPro[6] + tsm.getProject() + ",";
				dayHrs[6] = dayHrs[6] + tsm.getDay07();
			}
			if (tsm.getDay08() > 0) {
				dayPro[7] = dayPro[7] + tsm.getProject() + ",";
				dayHrs[7] = dayHrs[7] + tsm.getDay08();
			}
			if (tsm.getDay09() > 0) {
				dayPro[8] = dayPro[8] + tsm.getProject() + ",";
				dayHrs[8] = dayHrs[8] + tsm.getDay09();
			}
			if (tsm.getDay10() > 0) {
				dayPro[9] = dayPro[9] + tsm.getProject() + ",";
				dayHrs[9] = dayHrs[9] + tsm.getDay10();
			}
			if (tsm.getDay11() > 0) {
				dayPro[10] = dayPro[10] + tsm.getProject() + ",";
				dayHrs[10] = dayHrs[10] + tsm.getDay11();
			}
			if (tsm.getDay12() > 0) {
				dayPro[11] = dayPro[11] + tsm.getProject() + ",";
				dayHrs[11] = dayHrs[11] + tsm.getDay12();
			}
			if (tsm.getDay13() > 0) {
				dayPro[12] = dayPro[12] + tsm.getProject() + ",";
				dayHrs[12] = dayHrs[12] + tsm.getDay13();
			}
			if (tsm.getDay14() > 0) {
				dayPro[13] = dayPro[13] + tsm.getProject() + ",";
				dayHrs[13] = dayHrs[13] + tsm.getDay14();
			}
			if (tsm.getDay15() > 0) {
				dayPro[14] = dayPro[14] + tsm.getProject() + ",";
				dayHrs[14] = dayHrs[14] + tsm.getDay15();
			}
			if (tsm.getDay16() > 0) {
				dayPro[15] = dayPro[15] + tsm.getProject() + ",";
				dayHrs[15] = dayHrs[15] + tsm.getDay16();
			}
			if (tsm.getDay17() > 0) {
				dayPro[16] = dayPro[16] + tsm.getProject() + ",";
				dayHrs[16] = dayHrs[16] + tsm.getDay17();
			}

			if (tsm.getDay18() > 0) {
				dayPro[17] = dayPro[17] + tsm.getProject() + ",";
				dayHrs[17] = dayHrs[17] + tsm.getDay18();
			}
			if (tsm.getDay19() > 0) {
				dayPro[18] = dayPro[18] + tsm.getProject() + ",";
				dayHrs[18] = dayHrs[18] + tsm.getDay19();
			}
			if (tsm.getDay20() > 0) {
				dayPro[19] = dayPro[19] + tsm.getProject() + ",";
				dayHrs[19] = dayHrs[19] + tsm.getDay20();
			}

			if (tsm.getDay21() > 0) {
				dayPro[20] = dayPro[20] + tsm.getProject() + ",";
				dayHrs[20] = dayHrs[20] + tsm.getDay21();
			}

			if (tsm.getDay22() > 0) {
				dayPro[21] = dayPro[21] + tsm.getProject() + ",";
				dayHrs[21] = dayHrs[21] + tsm.getDay22();
			}
			if (tsm.getDay23() > 0) {
				dayPro[22] = dayPro[22] + tsm.getProject() + ",";
				dayHrs[22] = dayHrs[22] + tsm.getDay23();
			}
			if (tsm.getDay24() > 0) {
				dayPro[23] = dayPro[23] + tsm.getProject() + ",";
				dayHrs[23] = dayHrs[23] + tsm.getDay24();
			}

			if (tsm.getDay25() > 0) {
				dayPro[24] = dayPro[24] + tsm.getProject() + ",";
				dayHrs[24] = dayHrs[24] + tsm.getDay25();
			}
			if (tsm.getDay26() > 0) {
				dayPro[25] = dayPro[25] + tsm.getProject() + ",";
				dayHrs[25] = dayHrs[25] + tsm.getDay26();
			}
			if (tsm.getDay27() > 0) {
				dayPro[26] = dayPro[26] + tsm.getProject() + ",";
				dayHrs[26] = dayHrs[26] + tsm.getDay27();
			}
			if (tsm.getDay28() > 0) {
				dayPro[27] = dayPro[27] + tsm.getProject() + ",";
				dayHrs[27] = dayHrs[27] + tsm.getDay28();
			}
			if (tsm.getDay29() > 0) {
				dayPro[28] = dayPro[28] + tsm.getProject() + ",";
				dayHrs[28] = dayHrs[28] + tsm.getDay29();
			}
			if (tsm.getDay30() > 0) {
				dayPro[29] = dayPro[29] + tsm.getProject() + ",";
				dayHrs[29] = dayHrs[29] + tsm.getDay30();
			}
			if (tsm.getDay31() > 0) {
				dayPro[30] = dayPro[30] + tsm.getProject() + ",";
				dayHrs[30] = dayHrs[30] + tsm.getDay31();
			}
		}
		String filePath="";
		try (XSSFWorkbook wb = new XSSFWorkbook()) {
			XSSFSheet sheet = wb.createSheet("TimeSheet");

			// createdfont
			XSSFFont font = wb.createFont();
			font.setBold(true);
			// createdstyles
			XSSFCellStyle Styleforheader = wb.createCellStyle();
			Styleforheader.setBorderTop(BorderStyle.THICK);
			Styleforheader.setFont(font);

			XSSFCellStyle styles = wb.createCellStyle();
			styles.setFont(font);

			XSSFCellStyle styleForHeader = wb.createCellStyle();
			styleForHeader.setFont(font);
			styleForHeader.setAlignment(HorizontalAlignment.CENTER);
			styleForHeader.setBorderTop(BorderStyle.THIN);
			styleForHeader.setBorderBottom(BorderStyle.THIN);
			styleForHeader.setBorderLeft(BorderStyle.THIN);
			styleForHeader.setBorderRight(BorderStyle.THIN);

			XSSFCellStyle styleNormalColoums = wb.createCellStyle();

			styleNormalColoums.setFont(font);
			styleNormalColoums.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
			styleNormalColoums.setFillPattern(FillPatternType.FINE_DOTS);
			styleNormalColoums.setAlignment(HorizontalAlignment.CENTER);
			styleNormalColoums.setBorderTop(BorderStyle.THIN);
			styleNormalColoums.setBorderBottom(BorderStyle.THIN);
			styleNormalColoums.setBorderLeft(BorderStyle.THIN);
			styleNormalColoums.setBorderRight(BorderStyle.THIN);
			styleNormalColoums.setWrapText(true);

			XSSFCellStyle styleNormalcoloumsforWeakoff = wb.createCellStyle();

			styleNormalcoloumsforWeakoff.setFont(font);
			styleNormalcoloumsforWeakoff.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			styleNormalcoloumsforWeakoff.setFillPattern(FillPatternType.FINE_DOTS);
			styleNormalcoloumsforWeakoff.setAlignment(HorizontalAlignment.CENTER);
			styleNormalcoloumsforWeakoff.setBorderTop(BorderStyle.THIN);
			styleNormalcoloumsforWeakoff.setBorderBottom(BorderStyle.THIN);
			styleNormalcoloumsforWeakoff.setBorderLeft(BorderStyle.THIN);
			styleNormalcoloumsforWeakoff.setBorderRight(BorderStyle.THIN);
			styleNormalColoums.setWrapText(true);

			String[] headerArray = { "Date", "Day", "Projects", "Hrs." };

			XSSFRow row1 = sheet.createRow(1);
			XSSFCell tscell = row1.createCell(2);
			tscell.setCellValue("Time Sheet");
			tscell.setCellStyle(styles);

			XSSFRow row3 = sheet.createRow(3);
			XSSFCell tscell1 = row3.createCell(0);
			tscell1.setCellValue("Nichebit Softech Pvt Ltd");
			tscell1.setCellStyle(styleForHeader);

			XSSFRow row4 = sheet.createRow(4);
			XSSFCell tscell4 = row4.createCell(0);
			tscell4.setCellValue("Hyderabad");
			tscell4.setCellStyle(styleForHeader);

			XSSFRow row5 = sheet.createRow(5);
			XSSFCell tscell5 = row5.createCell(0);
			tscell5.setCellValue("India");
			tscell5.setCellStyle(styleForHeader);

			XSSFRow row8 = sheet.createRow(8);
			XSSFCell tscell8 = row8.createCell(0);
			tscell8.setCellValue("Resource Name");
			tscell8.setCellStyle(styleForHeader);
			XSSFCell tscell81 = row8.createCell(1);
			tscell81.setCellValue(name);
			tscell81.setCellStyle(styleForHeader);

			XSSFRow row9 = sheet.createRow(9);
			XSSFCell tscell9 = row9.createCell(0);
			tscell9.setCellValue("Client");
			tscell9.setCellStyle(styleForHeader);
			XSSFCell tscell91 = row9.createCell(1);
			tscell91.setCellValue(client);
			tscell91.setCellStyle(styleForHeader);

			XSSFRow row10 = sheet.createRow(10);
			XSSFCell tscell10 = row10.createCell(0);
			tscell10.setCellValue("Project");
			tscell10.setCellStyle(styleForHeader);
			XSSFCell tscell101 = row10.createCell(1);
			tscell101.setCellValue("");
			tscell101.setCellStyle(styleForHeader);

			int rowNo = 12;

			// setting headders
			XSSFRow row12 = sheet.createRow(rowNo);
			for (int i = 0; i < headerArray.length; i++) {

				XSSFCell cell = row12.createCell(i);
				cell.setCellValue(headerArray[i]);
				cell.setCellStyle(Styleforheader);

			}

			for (int i = 0; i < totalDays; i++) {
				int cell = 0;
				XSSFRow row = sheet.createRow(++rowNo);
				String project = "";

				if (dayPro[i] == null || "".equals(dayPro[i])) {
					project = "";
				} else {
					project = dayPro[i].substring(4, dayPro[i].length() - 1);
				}

				if ("Sunday".equals(days[i]) || "Saturday".equals(days[i])) {
					row.createCell((short) cell).setCellValue(days[i]);
					row.getCell(cell).setCellStyle(styleNormalcoloumsforWeakoff);
					row.createCell((short) ++cell).setCellValue(date[i]);
					row.getCell(cell).setCellStyle(styleNormalcoloumsforWeakoff);
					row.createCell((short) ++cell).setCellValue(project);
					row.getCell(cell).setCellStyle(styleNormalcoloumsforWeakoff);
					row.createCell((short) ++cell).setCellValue(dayHrs[i]);
					row.getCell(cell).setCellStyle(styleNormalcoloumsforWeakoff);
				} else {
					row.createCell((short) cell).setCellValue(days[i]);
					row.getCell(cell).setCellStyle(styleNormalColoums);
					row.createCell((short) ++cell).setCellValue(date[i]);
					row.getCell(cell).setCellStyle(styleNormalColoums);
					row.createCell((short) ++cell).setCellValue(project);
					row.getCell(cell).setCellStyle(styleNormalColoums);
					row.createCell((short) ++cell).setCellValue(dayHrs[i]);
					row.getCell(cell).setCellStyle(styleNormalColoums);
				}
			}

			int cellforwidth = 0;

			sheet.setColumnWidth((short) cellforwidth, 7000);
			sheet.setColumnWidth((short) ++cellforwidth, 5000);
			sheet.setColumnWidth((short) ++cellforwidth, 15000);
			sheet.setColumnWidth((short) ++cellforwidth, 5000);			
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
			String docname = "TimeSheet Of " + month + timestamp + ".xlsx";
			 filePath = folderPath + docname;
			FileOutputStream fout = new FileOutputStream(filePath);
			try {
				wb.write(fout);
				fout.flush();
				fout.close();
			} catch (Exception e) {
				throw new RuntimeException("Excel having some isuues" + e);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;

	}

}
