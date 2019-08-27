package com.songshu.third_part_tools.excel;

import java.text.SimpleDateFormat;
import java.util.List;

public abstract class ExcelUtils {
	
	public SimpleDateFormat createDateFormat(String pattern){
		return new SimpleDateFormat(pattern);
	}
	
	public void importExc() throws Exception {
	}
	
	/**导出excel到指定路径
	 * @param list
	 * @param path
	 * @throws Exception
	 */
	public void exportExc(List list,String path) throws Exception {
	}
	
	
}
