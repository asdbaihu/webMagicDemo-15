package com.inspur.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class zpUtil {

	/**
	 * 在字符串中用正则表达式提取符合条件的字符串列表
	 * 
	 * @param sourceStr 源串"<a>123</a><a>456</a><a>789</a>"
	 * @param sHost Url中的协议+host部分
	 * @param sRegEx 正则"<a>([\\s\\S]*?)</a>"
	 * @return List<String>
	 */
	public static List<String> getUrlListFromScript(String sourceStr, String sHost, String sRegEx) {
		Pattern pat = Pattern.compile(sRegEx);
		Matcher mat = pat.matcher(sourceStr);
		List<String> retList = new ArrayList<String>();

		while (mat.find()) {
			retList.add(sHost + mat.group(1));
			// System.out.println(sHost + mat.group(1));
		}
		return retList;
	}
	/**
	 * 在字符串中用正则表达式提取符合条件的字符串
	 * 
	 * @param sourceStr 源串"<a>123</a><a>456</a><a>789</a>"
	 * @param sRegEx 正则"<a>([\\s\\S]*?)</a>"
	 * @return String
	 */
	public static String getStrFromSourceStr(String sourceStr, String sRegEx) {
		Pattern pat = Pattern.compile(sRegEx);
		Matcher mat = pat.matcher(sourceStr);
		mat.find();
		return mat.group(1);
	}

 
	public static void main(String[] args) {
		aaaTest("");
	}
	
	private static void aaaTest(String aaa) {
		String sRegEx = "<a>([\\s\\S]*?)</a>";
		String ss = "<a>123</a><a>456</a><a>789</a>";

		List<String> urls = getUrlListFromScript(ss, "", sRegEx);
		System.out.println(urls.toString());
	}
}