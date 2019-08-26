package com.songshu.third_part_tools.excel.factory;

import com.songshu.third_part_tools.excel.ExcelPOIUtils;

public class ExcelPOIFactory implements ExcelFactory {

	public ExcelPOIUtils produce() {
		return new ExcelPOIUtils();
	}

}
