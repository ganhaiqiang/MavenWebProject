package com.demo.excel;

public class Main {
	public static void main(String[] args) {
		IRowReader reader = new RowReader();
		try {
//			ExcelReaderUtil.readExcel(reader, "D:\\迅雷下载\\js-excel.xls");
			ExcelReaderUtil.readExcel(reader, "D:\\迅雷下载\\js-excel.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
