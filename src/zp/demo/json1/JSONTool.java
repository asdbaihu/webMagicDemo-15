package zp.demo.json1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 本类为json和Map,List相互转换的工具类，有8个功能<br/>
 * 1.json字符串可以转换为对应的Map对象<br/>
 * 2.json字符串可以转换为对应的List对象<br/>
 * 3.JSONObject对象转换为对应的Map对象<br/>
 * 4.JSONArray对象转换为对应的List对象<br/>
 * 5.Map对象转换为json字符串<br/>
 * 6.List对象转换为json字符串<br/>
 * 7.Map对象转换为JSONObject对象<br/>
 * 8.List对象转换为JSONArray对象<br/>
 * @author yaoxy
 * @date 2013年9月12日11:11:19
 */
public abstract class JSONTool {
	private static final Log log = LogFactory.getLog(JSONTool.class);

	/**
	 * 将map转换为json格式的字符串，本方法和listToJsonStr(List list)方法互相迭代
	 * @param parms
	 * @return
	 */
	public static String mapToJsonStr(Map<String, Object> parms) {
		if (log.isDebugEnabled()) {
			log.debug("at the head of JSONTool.mapToJsonStr() method");
		}
		String jsonStr = "";
		JSONObject jsonObj = null;
		if (parms != null && parms.size() > 0) {
			jsonObj = mapToJsonObj(parms);
			jsonStr = jsonObj.toString();
		} else {
			if (log.isDebugEnabled()) {
				log.debug("error:传入的Map为空！");
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("at the end of JSONTool.mapToJsonStr() method");
		}
		return jsonStr;
	}

	/**
	 * 将Map转换为JSONObject，本方法和listToJsonArr(List list)迭代
	 * @param parms
	 * @return
	 */
	public static JSONObject mapToJsonObj(Map<String, Object> parms) {
		if (log.isDebugEnabled()) {
			log.debug("at the head of JSONTool.mapToJsonObj() method");
		}
		JSONObject jsonObj = null;

		if (parms != null && parms.size() > 0) {
			jsonObj = new JSONObject();

		/*	代码审查
		 * Iterator it = parms.keySet().iterator();
			while (it.hasNext()) {
				Object objKey = it.next();
				Object objValue = parms.get(objKey);*/
				
				Iterator<Map.Entry<String,Object>> it = parms.entrySet().iterator() ;
				while(it.hasNext()){
				Map.Entry<String,Object> me=it.next();
				Object objKey =me.getKey();
				Object objValue=(String)me.getValue();
				
				
				if (objValue != null && (objValue instanceof Map)) {
					jsonObj.put(objKey, mapToJsonObj((Map) objValue));
				} else if (objValue != null && (objValue instanceof List)) {
					jsonObj.put(objKey, listToJsonArr((List) objValue));
				} else {
					jsonObj.put(objKey, objValue);
				}
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("error:传入的Map为空！");
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("at the end of JSONTool.mapToJsonObj() method");
		}
		return jsonObj;
	}

	/**
	 * 将list转换为json格式的字符串，本方法和mapToJsonStr(Map<String,Object>parms)方法互相迭代
	 * @param list
	 * @return
	 */
	public static String listToJsonStr(List list) {
		if (log.isDebugEnabled()) {
			log.debug("at the head of JSONTool.listToJsonStr() method");
		}
		String jsonStr = "";
		JSONArray jsonArr = null;
		if (list != null && list.size() > 0) {
			jsonArr = listToJsonArr(list);
			jsonStr = jsonArr.toString();
		} else {
			if (log.isDebugEnabled()) {
				log.debug("error:传入的List为空！");
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("at the end of JSONTool.listToJsonStr() method");
		}
		return jsonStr;
	}

	/**
	 * 将List转换为JSONArray，本方法和mapToJsonObj(Map<String,Object>parms)迭代
	 * @param list
	 * @return
	 */
	public static JSONArray listToJsonArr(List list) {
		if (log.isDebugEnabled()) {
			log.debug("at the head of JSONTool.listToJsonArr() method");
		}
		JSONArray jsonArr = null;

		if (list != null && list.size() > 0) {
			jsonArr = new JSONArray();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				Object obj = list.get(i);
				if (obj != null && (obj instanceof Map)) {
					jsonArr.add(mapToJsonObj((Map) obj));
				} else if (obj != null && (obj instanceof List)) {
					jsonArr.add(listToJsonArr((List) obj));
				} else {
					jsonArr.add((obj == null) ? "" : obj);
				}
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("error:传入的List为空！");
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("at the end of JSONTool.listToJsonArr() method");
		}
		return jsonArr;
	}

	/**
	 * 本方法用于将json格式的字符串转换成Map
	 * @param json
	 * @return
	 */
	public static Map jsonStrToMap(String json) {
		if (log.isDebugEnabled()) {
			log.debug("at the head of JSONTool.jsonStrToMap() method");
		}
		//System.out.println("at the head of JSONTool.jsonStrToMap() method");
		Map jsonMap = null;

		if (json != null && json.length() > 1) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			jsonMap = jsonObjToMap(jsonObj);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("error:传入的json字符串为空！");
			}
		}
		//System.out.println("at the end of JSONTool.jsonStrToMap() method");
		if (log.isDebugEnabled()) {
			log.debug("at the end of JSONTool.jsonStrToMap() method");
		}
		return jsonMap;
	}

	/**
	 * 将json转换成Map，该json可以是复杂的json传，最终JSONObject将转换成map，JSONArray将转换成List。
	 * 本方法和jsonArrToList(JSONArray jsonArr)递归
	 * @param jsonObj
	 * @return
	 */
	public static Map jsonObjToMap(JSONObject jsonObj) {
		if (log.isDebugEnabled()) {
			log.debug("at the head of JSONTool.jsonObjToMap() method");
		}
		//System.out.println("at the head of JSONTool.jsonObjToMap() method");
		Map jsonMap = null;
		//JSONObject不为空才解析，否则返回空
		if ( jsonObj.size() > 0) {
			jsonMap = new HashMap<String, String>();
			
			Iterator<Map.Entry<String,Object>> it = jsonObj.entrySet().iterator() ;
			while(it.hasNext()){
			Map.Entry<String,Object> me=it.next();
			Object objKey=me.getKey();
			Object objValue=me.getValue().toString();
			 
			/*
			 * ***代码审查
			 Iterator it = jsonObj.keySet().iterator();
			while (it.hasNext()) {
				Object objKey = it.next();
				Object objValue = jsonObj.get(objKey);
			 */
				if (objValue != null && (objValue instanceof JSONObject)) {
					//如果objValue是JSONOBject则将其转换为Map
					jsonMap.put(objKey, jsonObjToMap((JSONObject) objValue));
				} else if (objValue != null && (objValue instanceof JSONArray)) {
					//如果objValue是JSONArray则将其转换为List
					jsonMap.put(objKey, jsonArrToList((JSONArray) objValue));
				} else {
					jsonMap.put(objKey, objValue);
				}
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("error:传入的JSONObject为空！");
			}
		}
		//System.out.println("at the end of JSONTool.jsonObjToMap() method");
		if (log.isDebugEnabled()) {
			log.debug("at the end of JSONTool.jsonObjToMap() method");
		}
		return jsonMap;
	}

	/**
	 * 本方法用于将json类型的字符串转换为List
	 * @param jsonStr
	 * @return
	 */
	public static List jsonStrToList(String jsonStr) {
		if (log.isDebugEnabled()) {
			log.debug("at the head of JSONTool.jsonStrToList() method");
		}
		//System.out.println("at the head of JSONTool.jsonStrToMap() method");
		List jsonList = null;

		if (jsonStr != null && jsonStr.length() > 1) {
			JSONArray jsonArr = JSONArray.fromObject(jsonStr);
			jsonList = jsonArrToList(jsonArr);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("error:传入的json字符串为空！");
			}
		}
		//System.out.println("at the end of JSONTool.jsonStrToMap() method");
		if (log.isDebugEnabled()) {
			log.debug("at the end of JSONTool.jsonStrToList() method");
		}
		return jsonList;
	}

	/**
	 * 本方法用于将JSONArray转换为List，本方法和jsonObjToMap(JSONObject jsonObj)方法有递归关系
	 * @param jsonArr
	 * @return
	 */
	public static List jsonArrToList(JSONArray jsonArr) {
		if (log.isDebugEnabled()) {
			log.debug("at the head of JSONTool.jsonArrToList() method");
		}
		//System.out.println("at the head of JSONTool.jsonArrToList() method");
		List rtnJsonArr = null;
		if (jsonArr != null && jsonArr.size() > 0) {
			//System.out.println("jsonArr.size()="+jsonArr.size());
			rtnJsonArr = new ArrayList();
			int size = jsonArr.size();
			for (int i = 0; i < size; i++) {
				Object jsonObj = jsonArr.get(i);
				if (jsonObj != null && (jsonObj instanceof JSONObject)) {
					rtnJsonArr.add(jsonObjToMap((JSONObject) jsonObj));
				} else if (jsonObj != null && (jsonObj instanceof JSONArray)) {
					rtnJsonArr.add(jsonArrToList((JSONArray) jsonObj));
				} else {
					//System.out.println("other class = "+jsonObj.getClass());
					rtnJsonArr.add(jsonObj);
				}
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("error:传入的JSONArray为空！");
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("at the end of JSONTool.jsonArrToList() method");
		}
		//System.out.println("at the end of JSONTool.jsonArrToList() method");
		return rtnJsonArr;
	}

	//	public static void main(String args[]){
	//		System.out.println(System.currentTimeMillis());
	//		String jsonStr = "";
	//		Map jsonMap = new HashMap();
	//		ArrayList list = new ArrayList();
	//		Map map = new HashMap();
	//		map.put("am","s");
	//		map.put("bm","d");
	//		map.put("cm","f");
	//		list.add("a");
	//		list.add("b");
	//		list.add("c");
	//		list.add("d");
	//		list.add("e");
	//		list.add("a");
	//		jsonMap.put("list", list);
	//		jsonMap.put("map", map);
	//		System.out.println("jsonMap:"+jsonMap);
	//		
	//		//将map转换为json格式字符串
	//		System.out.println("JSONTool.mapToJsonStr:"+JSONTool.mapToJsonStr(jsonMap));
	//		
	//		//list
	//		System.out.println("List.listToJsonStr:"+JSONTool.listToJsonStr(list));
	//		
	//		String jsonStr2 = JSONTool.listToJsonStr(list);
	//		//将JSON类型的List类型的String转换为List
	//		List jsonlist = JSONTool.jsonStrToList(jsonStr2);
	//		System.out.println("jsonlist.size()="+jsonlist.size());
	//		System.out.println("jsonlist："+jsonlist);
	//		
	//		System.out.println(System.currentTimeMillis());
	//		
	//	}
}
