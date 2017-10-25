package com.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;

public class PoiReadExcel {
	public List<List<String>> readExcel(String fileName, String path) {
		List<List<String>> Row = new ArrayList<List<String>>();

		try {
			Workbook workBook = null;
			try {
				InputStream stream = new FileInputStream(new File(path + "\\" + fileName));
				workBook = new XSSFWorkbook(stream);
			} catch (Exception ex) {
				workBook = new HSSFWorkbook(new FileInputStream(path + "\\" + fileName));
			}

			for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workBook.getSheetAt(numSheet);
				System.out.println("总行数：" + sheet.getLastRowNum());
				if (sheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					System.out.println("总列数：" + row.getLastCellNum());
					// 循环列Cell
					ArrayList<String> arrCell = new ArrayList<String>();
					for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
						Cell cell = row.getCell(cellNum);
						if (cell == null) {
							continue;
						}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						arrCell.add(getValue(cell));
					}
					Row.add(arrCell);
				}
			}
		} catch (IOException e) {
			System.out.println("e:" + e);
		}

		return Row;
	}

	private String getValue(Cell cell) {
		// if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
		// return String.valueOf(cell.getBooleanCellValue());
		// } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
		// System.out.println(cell.getCellType());
		// return String.valueOf(cell.getNumericCellValue());
		// } else {
		return String.valueOf(cell.getStringCellValue());
		// }
	}

	public static void main(String[] args) {
		PoiReadExcel s = new PoiReadExcel();
		// ArrayList<ArrayList<String>>
		// row=s.readExcel("TEST.xlsx","D:\\Program Files\\Java");
		List<List<String>> row = s.readExcel("js-excel.xlsx", "D:/迅雷下载/");
		System.out.println("size:" + row.size());
		for (List<String> cell : row) {
			System.out.println(JSON.toJSONString(cell));
		}
	}
}
