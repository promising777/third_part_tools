package com.songshu.third_part_tools.excel;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;

public class ExcelPOIUtils extends ExcelUtils {
	@Override
	public void importExc() throws Exception{
		// TODO Auto-generated method stub

	}
	@Override
	public void exportExc(List list,String path) throws Exception{
		if (ObjectUtils.isEmpty(list) || ObjectUtils.isEmpty(path)) {
			return;
		}
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet();
		Class<T> excelClass;
		Object obj;
		for(int i = 0; i < list.size(); i ++){
			Row row = hssfSheet.createRow(i);
			
			obj = list.get(i);
			excelClass = (Class<T>) obj.getClass();
			Field[] fields = excelClass.getDeclaredFields();
			Field field;
			for (int j = 0; j < fields.length; j++) {
				field = fields[j];
				field.setAccessible(true);
				String val = String.valueOf(field.get(obj));
				row.createCell(j).setCellValue(val);
			}
			
		}
		hssfWorkbook.write(new File(path));
		hssfWorkbook.close();
	}

}
