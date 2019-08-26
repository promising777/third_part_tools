package com.songshu.third_part_tools.excel;
import java.util.Collection;
import java.util.Map;

public class ObjectUtils {


	/**
	 * 判断字符串为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 集合判断是否为空
	 * 
	 * @param collection
	 *            使用泛型
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> collection) {
		return collection == null || collection.size() == 0;
	}

	/**
	 * map集合不为空的判断
	 * 
	 * @param map
	 *            使用泛型，可以传递不同的类型参数
	 * @return
	 */
	public static <T> boolean isEmpty(Map<T, T> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * byte类型数组判断不为空
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isEmpty(byte[] t) {
		return t == null || t.length == 0;
	}

	/**
	 * short类型数组不为空判断
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isEmpty(short[] t) {
		return t == null || t.length == 0;
	}

	/**
	 * 数组判断不为空,没有泛型数组,所以还是分开写吧
	 * 
	 * @param t
	 *            可以是int,short,byte,String,Object,long
	 * @return
	 */
	public static boolean isEmpty(int[] t) {
		return t == null || t.length == 0;
	}

	/**
	 * long类型数组不为空
	 * 
	 * @param t
	 * @return
	 */
	public static boolean notEmpty(long[] t) {
		return t != null && t.length > 0;
	}

	/**
	 * String类型的数组不为空
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isEmpty(String[] t) {
		return t == null || t.length == 0;
	}

	/**
	 * Object类型数组不为空
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isEmpty(Object[] t) {
		return t != null && t.length > 0;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		return o == null || "".equals(o) || "null".equals(o);
	}
}
