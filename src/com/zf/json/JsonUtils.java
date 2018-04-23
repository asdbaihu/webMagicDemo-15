package com.zf.json;
/**
 * 
 */
public class JsonUtils {
	
	public static char jsonStringBegin = '"';
	public static char jsonStringEnd = '"';
	
	public static char jsonListBegin = '[';
	public static char jsonListEnd = ']';
	
	public static char jsonMapBegin = '{';
	public static char jsonMapEnd = '}';
	public static char jsonMapConnector = ':';
	
	public static char pathSeparator = '/';
	public static char pathListBegin = '*';
	public static char pathListIndexBegin = '[';
	public static char pathListIndexEnd = ']';
	
	public static boolean isValidString(String content, char begin, char end){
		char[] chars = content.toCharArray();
		int beginIndex = 0;
		int endIndex = 0;
		int stringIndex = 0;
		if(chars[0]!=begin || chars[chars.length-1] != end){
			return false;
		}
		for (int i = 0; i < chars.length; i++) {
			if(i>0 && begin!=jsonStringBegin && chars[i]==jsonStringBegin){
				stringIndex++;				
			}
			if(stringIndex%2!=0){
				continue;
			}
			if(chars[i]=='\\'){
				i++;				
			}else if(chars[i]==begin){
				beginIndex++;
			}else if(chars[i]==end){
				endIndex++;
			}
			
		}		
		if(stringIndex%2!=0){
			return false;
		}		
		if(begin==end && beginIndex>0 && beginIndex%2==0){
			return true;
		}
		if(beginIndex>0 && endIndex>0 && beginIndex==endIndex){
			return true;
		}
		return false;
	}
	
	public static boolean isSmoothString(String s){
		boolean flag = false;
		if(s.matches("\\d+")||s.matches("\\d+\\.\\d+")){
			flag = true;
		}else if(JsonUtils.isValidString(s, JsonUtils.jsonStringBegin, JsonUtils.jsonStringEnd)||JsonUtils.isValidString(s, JsonUtils.jsonListBegin, JsonUtils.jsonListEnd)||JsonUtils.isValidString(s, JsonUtils.jsonMapBegin, JsonUtils.jsonMapEnd)){
			flag = true;
		}else if(s.toLowerCase().equals("null")){
			flag = true;
		}
		return flag;
	}
	
	public static Object outputString(String value){
		if(value.matches("\\d+")){
			return Integer.valueOf(value);
		}else if(value.matches("\\d+\\.\\d+")){
			return Double.valueOf(value);
		}else if(value.charAt(0)==(JsonUtils.jsonStringBegin)){
			return value.substring(1,value.length()-1);
		}else if(value.toLowerCase().equals("null")){
			return null;
		}else{
			return value;
		}
	}	
	
	
}
