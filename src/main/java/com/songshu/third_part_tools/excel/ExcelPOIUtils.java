package com.songshu.third_part_tools.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.songshu.third_part_tools.ExcelFiled;

public class ExcelPOIUtils extends ExcelUtils {
	@Override
	public <T> List<T> readExc(InputStream in, Class<T> class1) throws Exception {
		if (ObjectUtils.isEmpty(in) || ObjectUtils.isEmpty(class1)) {
			return null;
		}

		Workbook workbook = WorkbookFactory.create(in);
		Sheet sheet = workbook.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();

		List<T> list = new ArrayList<T>(rowNum);
		T obj;

		Field[] fields = class1.getDeclaredFields();
		Field field;
		for (int i = 0; i < rowNum; i++) {
			Row row = sheet.getRow(i);
			obj = class1.newInstance();
			for (int j = 1; j < fields.length; j++) {
				field = fields[j];
				ExcelFiled excelFiled = field.getAnnotation(ExcelFiled.class);
				Cell cell = row.getCell(excelFiled.colIndex());
				try {
					if (cell == null || cell.toString().length() == 0) {
						
					} else if (field.getType().equals(Date.class)) {
						if (CellType.STRING == cell.getCellType()) {
							BeanUtils.copyProperty(obj, field.getName(), dateFormat.parse(cell.getStringCellValue()));
						} else {
							BeanUtils.setProperty(obj, field.getName(), new Date(cell.getDateCellValue().getTime()));
						}
					} else if (field.getType().equals(java.lang.Integer.class)) {
						if (CellType.NUMERIC == cell.getCellType()) {
							BeanUtils.setProperty(obj, field.getName(), (int) cell.getNumericCellValue());
						} else if (CellType.STRING == cell.getCellType()) {
							BeanUtils.setProperty(obj, field.getName(), Integer.parseInt(cell.getStringCellValue()));
						}
					} else if (field.getType().equals(java.math.BigDecimal.class)) {
						if (CellType.NUMERIC == cell.getCellType()) {
							BeanUtils.setProperty(obj, field.getName(), new BigDecimal(cell.getNumericCellValue()));
						} else if (CellType.STRING == cell.getCellType()) {
							BeanUtils.setProperty(obj, field.getName(), new BigDecimal(cell.getStringCellValue()));
						}
					} else {
						if (CellType.NUMERIC == cell.getCellType()) {
							BeanUtils.setProperty(obj, field.getName(), new BigDecimal(cell.getNumericCellValue()));
						} else if (CellType.STRING == cell.getCellType()) {
							BeanUtils.setProperty(obj, field.getName(), cell.getStringCellValue());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception(String.format(errormsg, new String[]{("" + i), excelFiled.colName()}) + "," + e.getMessage());
				}

				
			}
			list.add(obj);
		}

		return list;

	}

	@Override
	public <T> void exportExc(OutputStream out, List<T> list) throws Exception {
		if (ObjectUtils.isEmpty(list) || ObjectUtils.isEmpty(out)) {
			return;
		}
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet();
		Class<T> excelClass;
		T obj;
		setHead(list.get(0).getClass(), hssfSheet);
		for (int i = 0; i < list.size(); i++) {
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
		hssfWorkbook.write(out);
		hssfWorkbook.close();
	}

	/**
	 * 根据属性上设置的注解进行转换数据格式后返回
	 * 
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
		} else if (excelFiled.precision() > 0) {
			val = String.format("%." + excelFiled.precision() + "f", field.get(obj));
		} else
			val = String.valueOf(field.get(obj));
		return val;
	}

	private void setHead(Class<? extends Object> class1, HSSFSheet hssfSheet) {
		Field[] fieldHeads = class1.getDeclaredFields();
		Row row = hssfSheet.createRow(0);
		for (int i = 0; i < fieldHeads.length; i++) {
			ExcelFiled excelFiled = fieldHeads[i].getAnnotation(ExcelFiled.class);
			row.createCell(i).setCellValue(excelFiled.colName());
		}
	}

}
