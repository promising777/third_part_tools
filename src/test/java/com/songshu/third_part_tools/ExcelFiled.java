package com.songshu.third_part_tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelFiled {
	/**
	 * 列头名称
	 * @return
	 */
	String colName() default "";
	
	/**
	 * 数据精度，精确到后几位数
	 * 默认0位
	 * @return
	 */
	int precision() default 0;
	
	/**
	 * 日期格式
	 * 默认 “yyyy-MM-dd HH:mm:ss”
	 * @return
	 */
	String dateFormat() default "";
}
