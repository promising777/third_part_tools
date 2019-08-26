package com.songshu.third_part_tools.excel.factory;

import com.songshu.third_part_tools.excel.ExcelJXLUtils;

public class ExcelJXLFactory implements ExcelFactory {

	public ExcelJXLUtils produce() {
		return new ExcelJXLUtils();
	}

}
