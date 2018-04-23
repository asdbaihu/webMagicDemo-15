package com.zf.json;

import java.util.ArrayList;
import java.util.List;
/**
 */
public class JsonList {
	
	public List<String> listStringAction(String list){
		List<String> jsonList = new ArrayList<String>();		
		String listString = list.trim().substring(1, list.trim().length()-1);
		String[] arrString = listString.split(",");
		StringBuffer sb = new StringBuffer();
		for (String arrs : arrString) {
			if(sb.length()>0){
				if(JsonUtils.isSmoothString(sb.toString())){
					jsonList.add(sb.toString());
					sb.delete(0, sb.length());					
				}else{
					sb.append(",");
				}
			}
			sb.append(arrs);			
		}
		if(JsonUtils.isSmoothString(sb.toString())){
			jsonList.add(sb.toString());
			sb.delete(0, sb.length());					
		}else{
			jsonList = null;
		}
		return jsonList;
	}	
	
	public boolean isListString(String json){
		if(json.trim().charAt(0)==JsonUtils.jsonListBegin){
			return true;
		}
		return false;
	}
}
