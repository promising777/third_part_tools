package com.songshu.third_part_tools;

import lombok.Data;

@Data
public class ExportVo {
	@ExcelFiled(colName="游戏名称")
	public String gameName;
	@ExcelFiled(colName="渠道名称")
	public String channelName;
	
	
}
