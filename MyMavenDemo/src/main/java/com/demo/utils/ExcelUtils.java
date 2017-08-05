package com.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	/**
	 * 遍历Excel2003文档内容
	 * 
	 * @param inputStream
	 */
	public static void readExcel2003(InputStream inputStream) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = wb.getSheetAt(0);

			// 读取图片
			List<HSSFPictureData> pictures = wb.getAllPictures();
			for (int i = 0; i < pictures.size(); i++) {
				HSSFPictureData pictureData = pictures.get(i);
				byte[] picData = pictureData.getData();
				System.out.println("image-size:" + picData.length);
			}

			// 遍历单元格内容
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:// 字符串
						System.out.print(cell.getRichStringCellValue().getString());
						System.out.print('|');
						break;
					case Cell.CELL_TYPE_NUMERIC:// 数值与日期
						if (DateUtil.isCellDateFormatted(cell)) {
							System.out.print(String.valueOf(cell.getDateCellValue()));
						} else {
							System.out.print(cell.getNumericCellValue());
						}
						System.out.print('|');
						break;
					case Cell.CELL_TYPE_BOOLEAN:// boolean类型
						System.out.print(cell.getBooleanCellValue());
						System.out.print('|');
						break;
					default:
					}
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历Excel2007文档内容
	 * 
	 * @param inputStream
	 */
	public static void readExcel2007(InputStream inputStream) {
		try {
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);

			// 读取图片
			List<XSSFPictureData> pictures = wb.getAllPictures();
			for (int i = 0; i < pictures.size(); i++) {
				XSSFPictureData pictureData = pictures.get(i);
				byte[] picData = pictureData.getData();
				System.out.println("image-size:" + picData.length);
			}

			// 遍历单元格内容
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getRichStringCellValue().getString());
						System.out.print('|');
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							System.out.print(String.valueOf(cell.getDateCellValue()));
						} else {
							System.out.print(cell.getNumericCellValue());
						}
						System.out.print('|');
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue());
						System.out.print('|');
						break;
					default:
					}
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
