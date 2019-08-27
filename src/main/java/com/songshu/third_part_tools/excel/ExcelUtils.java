package com.songshu.third_part_tools.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class ExcelUtils {
	
	final static String errormsg = "第 %s 行的“%s”数据导入错误";
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	SimpleDateFormat createDateFormat(String pattern){
		return new SimpleDateFormat(pattern);
	}
	
	
	public <T> List<T> readExc(InputStream in,Class<T> class1) throws Exception {
		return null;
	}
	
	/**导出excel到指定路径
	 * @param list
	 * @param path
	 * @throws Exception
	 */
	public <T> void exportExc(OutputStream out,List<T> list) throws Exception {
	}
	
	
}
