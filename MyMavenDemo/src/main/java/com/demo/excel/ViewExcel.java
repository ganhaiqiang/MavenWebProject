package com.demo.excel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.demo.pojo.Student;

public class ViewExcel extends AbstractXlsView {

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String excelName = "用户信息.xls";
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + processFileName(request, excelName));

		List stuList = (List) model.get("list");
		// 产生Excel表头
		Sheet sheet = workbook.createSheet("studentList");
		Row header = sheet.createRow(0); // 第0行
		// 产生标题列
		header.createCell(0).setCellValue("name");
		header.createCell(1).setCellValue("sex");
		header.createCell(2).setCellValue("date");
		header.createCell(3).setCellValue("count");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));

		// 填充数据
		int rowNum = 1;
		for (Iterator iter = stuList.iterator(); iter.hasNext();) {
			Student element = (Student) iter.next();
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(element.getId());
			row.createCell(1).setCellValue(element.getName().toString());
			row.createCell(2).setCellValue(element.getSex().toString());
			row.getCell(2).setCellStyle(cellStyle);
			row.createCell(3).setCellValue(element.getAge());
			row.createCell(4).setCellValue(element.getAddress());
			row.createCell(5).setCellValue(element.getPhone());
			row.createCell(6).setCellValue(element.getPicture());
		}

		// 列总和计算
		Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("TOTAL:");
		String formual = "SUM(D2:D" + rowNum + ")"; // D2到D[rowNum]单元格起(count数据)
		row.createCell(3).setCellFormula(formual);
	}

	/**
	 * 下载文件名乱码处理
	 * 
	 * @param request
	 * @param fileNames
	 * @return
	 */
	public static String processFileName(HttpServletRequest request, String fileNames) {
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {
				return java.net.URLEncoder.encode(fileNames, "UTF8");
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				return new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileNames;
	}
}
