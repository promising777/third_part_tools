package com.songshu.third_part_tools.excel;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;

import com.songshu.third_part_tools.ExcelFiled;

public abstract class ExcelUtils {
	
	public void importExc() throws Exception {
	}
	
	/**导出excel到指定路径
	 * @param list
	 * @param path
	 * @throws Exception
	 */
	public void exportExc(List list,String path) throws Exception {
	}
	
	
	public void setHead(Class<? extends Object> class1,HSSFSheet hssfSheet) {
		Field[] fieldHeads = class1.getDeclaredFields();
		Row row = hssfSheet.createRow(0);
		for (int i = 0; i < fieldHeads.length; i++) {
			ExcelFiled excelFiled = fieldHeads[i].getAnnotation(ExcelFiled.class);
			row.createCell(i).setCellValue(excelFiled.colName());
		}
	}
}
