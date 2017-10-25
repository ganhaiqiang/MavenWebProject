package com.demo.excel;

import java.util.List;

public class RowReader implements IRowReader {

	/*
	 * 业务逻辑实现方法
	 * 
	 * @see com.eprosun.util.excel.IRowReader#getRows(int, int, java.util.List)
	 */
	@Override
	public void getRows(int sheetIndex, int curRow, List<String> rowlist) {
		System.out.print(curRow + " ");
		// 标题不读取
		if (curRow == 0) {
			return;
		}
		for (int i = 0; i < rowlist.size(); i++) {
			System.out.print(rowlist.get(i) + "★");
		}
		System.out.println();
	}
}
