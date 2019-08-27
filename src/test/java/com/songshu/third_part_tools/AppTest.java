package com.songshu.third_part_tools;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.StringUtil;

import com.songshu.third_part_tools.excel.factory.ExcelFactory;
import com.songshu.third_part_tools.excel.factory.ExcelPOIFactory;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
	
	public static void main(String[] args) throws Exception {

		/*long starTime = System.currentTimeMillis();
		List<ExportVo> exportVos = new ArrayList<ExportVo>();
		while (true) {
			ExportVo exportVo = new ExportVo();
			exportVo.setChannelName("刘滔");
			exportVo.setGameName("萌仙途");
			exportVo.setDate(new Date());
			exportVo.setPrice(12.312412);
			exportVos.add(exportVo);
			if (exportVos.size() > 11) {
				break;
			}
		}
		ExcelFactory factory = new ExcelPOIFactory();
		FileOutputStream out = new FileOutputStream(new File("H:\\导出.xls"));
		factory.produce().exportExc(out,exportVos);
		long endTime = System.currentTimeMillis();
		System.out.println("导出耗时："+(endTime - starTime));*/
		
		ExcelFactory factory = new ExcelPOIFactory();
		FileInputStream in = new FileInputStream(new File("H:\\导出.xls"));
		List<ExportAo> aos = factory.produce().readExc(in, ExportAo.class);
		System.out.println(aos.size());
		
	}
}
