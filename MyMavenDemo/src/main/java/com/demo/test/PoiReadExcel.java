package com.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiReadExcel {
	public ArrayList<ArrayList<String>> readExcel(String fileName, String path) {
		ArrayList<ArrayList<String>> Row = new ArrayList<ArrayList<String>>();

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
				if (sheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}

					// 循环列Cell
					ArrayList<String> arrCell = new ArrayList<String>();
					for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
						Cell cell = row.getCell(cellNum);
						if (cell == null) {
							continue;
						}
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
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	public static void main(String[] args) {
		PoiReadExcel s = new PoiReadExcel();
		// ArrayList<ArrayList<String>>
		// row=s.readExcel("TEST.xlsx","D:\\Program Files\\Java");
		ArrayList<ArrayList<String>> row = s.readExcel("1.xlsx", "D:\\Program Files\\Java");
		System.out.println("size:" + row.size());
		for (ArrayList<String> cell : row) {
			for (String str : cell) {
				System.out.println(str);
			}
		}
	}
}
