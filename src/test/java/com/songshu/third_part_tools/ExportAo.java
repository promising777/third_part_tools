package com.songshu.third_part_tools;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ExportAo implements Serializable{
	@ExcelFiled(colIndex=0,colName="游戏名称")
	public String gameName;
	@ExcelFiled(colIndex=1,colName="渠道名称")
	public String channelName;
	@ExcelFiled(colIndex=2,colName="日期")
	public Date date;
	@ExcelFiled(colIndex=3,colName="金额")
	public double price;
	
}
