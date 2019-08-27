package com.songshu.third_part_tools.excel;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;

import com.songshu.third_part_tools.ExcelFiled;

public class ExcelPOIUtils extends ExcelUtils {
	@Override
	public void importExc() throws Exception{
		// TODO Auto-generated method stub

	}
	@Override
	public void exportExc(List list, String path) throws Exception{
		if (ObjectUtils.isEmpty(list) || ObjectUtils.isEmpty(path)) {
			return;
		}
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet();
		Class<T> excelClass;
		Object obj;
		setHead(list.get(0).getClass(),hssfSheet);
		for(int i = 0; i < list.size(); i ++){
			Row row = hssfSheet.createRow(i + 1);
			
			obj = list.get(i);
			excelClass = (Class<T>) obj.getClass();
			Field[] fields = excelClass.getDeclaredFields();
			Field field;
			for (int j = 0; j < fields.length; j++) {
				field = fields[j];
				field.setAccessible(true);
				String val = getVal(obj, field);
				
				row.createCell(j).setCellValue(val);
			}
			
		}
		hssfWorkbook.write(new File(path));
		hssfWorkbook.close();
	}
	
	/**
	 * 根据属性上设置的注解进行转换数据格式后返回
	 * @param obj
	 * @param field	
	 * @return
	 * @throws IllegalAccessException
	 */
	private String getVal(Object obj, Field field) throws IllegalAccessException {
		ExcelFiled excelFiled = field.getAnnotation(ExcelFiled.class);
		String val = "";
		if (!ObjectUtils.isEmpty(excelFiled.dateFormat())) {
			val = createDateFormat(excelFiled.dateFormat()).format(field.get(obj));
		}else if(excelFiled.precision() > 0){
			val = String.format("%."+excelFiled.precision()+"f", field.get(obj));
		}else
			val = String.valueOf(field.get(obj));
		return val;
	}
	
	private void setHead(Class<? extends Object> class1,HSSFSheet hssfSheet) {
		Field[] fieldHeads = class1.getDeclaredFields();
		Row row = hssfSheet.createRow(0);
		for (int i = 0; i < fieldHeads.length; i++) {
			ExcelFiled excelFiled = fieldHeads[i].getAnnotation(ExcelFiled.class);
			row.createCell(i).setCellValue(excelFiled.colName());
		}
	}
	
}
