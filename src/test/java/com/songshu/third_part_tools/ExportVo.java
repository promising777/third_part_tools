package com.songshu.third_part_tools;

import java.util.Date;

import lombok.Data;

@Data
public class ExportVo {
	@ExcelFiled(colName="游戏名称")
	public String gameName;
	@ExcelFiled(colName="渠道名称")
	public String channelName;
	@ExcelFiled(colName="日期",dateFormat="yyyy-MM-dd HH:mm:ss")
	public Date date;
	@ExcelFiled(colName="金额",precision=2)
	public double price;
	
	
}
