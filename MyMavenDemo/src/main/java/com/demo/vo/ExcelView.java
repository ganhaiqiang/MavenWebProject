package com.demo.vo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.alibaba.fastjson.JSON;
import com.demo.pojo.Student;

public class ExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Student student = (Student) model.get("student");
			System.out.println(JSON.toJSONString(student, true));
			Sheet sheet = workbook.createSheet("接地环流数据报表");
			// PoiUtil.setCircleReportColumnWidth(sheet);
			Row titleRow = sheet.createRow((short) 0);
			String[] cellTitles = new String[] { "运单号", "寄件人", "手机", "寄件人地址", "收件人", "收件人手机", "订单号", "B相最小值", "B相最大值", "B相平均值", "B相备注",
					"C相最小值", "C相最大值", "C相平均值", "C相备注", "图形" };
			for (int i = 0; i < cellTitles.length; i++) {
				Cell cell = titleRow.createCell(i);
				cell.setCellValue(cellTitles[i]);

				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();
				// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setFontName("宋体");
				font.setFontHeightInPoints((short) 11.5);
				style.setFont(font);
				style.setAlignment(CellStyle.ALIGN_CENTER);
				style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				style.setBorderBottom((short) 1);
				style.setBorderLeft((short) 1);
				style.setBorderRight((short) 1);
				style.setBorderTop((short) 1);
				style.setBottomBorderColor(HSSFColor.BLACK.index);
				cell.setCellStyle(style);
			}
			for (int i = 0; i < 10; i++) {
				Row row = sheet.createRow((short) i + 1);
				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();
				// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setFontName("宋体");
				font.setFontHeightInPoints((short) 11.5);
				style.setFont(font);
				style.setAlignment(CellStyle.ALIGN_CENTER);
				style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				style.setBorderBottom((short) 1);
				style.setBorderLeft((short) 1);
				style.setBorderRight((short) 1);
				style.setBorderTop((short) 1);
				style.setBottomBorderColor(HSSFColor.BLACK.index);
				for (int j = 0; j < 16; j++) {
					row.createCell(j).setCellStyle(style);
				}
				row.getCell(0).setCellValue("123452342342");
				row.getCell(1).setCellValue("张三丰");
				row.getCell(2).setCellValue("小白");
				row.getCell(3).setCellValue("13428713009");
				row.getCell(4).setCellValue("关羽");
				row.getCell(5).setCellValue("0755-87823345");
				row.getCell(6).setCellValue("MEM36345234234");
				row.getCell(7).setCellValue("哈哈");
				row.getCell(8).setCellValue("在啧啧啧");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
