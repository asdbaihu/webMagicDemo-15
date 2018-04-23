package zp.demo.json1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Test {
    
    /**
     * @param args
     * @author wen
     */
    public static void main(String[] args) {
        // test1();
        // test2();
        String json = "{1:{1:{jhinfo:['计划一','知行网,'www.zhixing123.cn'],jhrate:['1-5:10.0','6-100:5.0/1']},2:{jhinfo:['计划二',''知行网,'www.zhixing123.cn'],],jhrate:['1-100:100.0']},3:{jhinfo:['计划三',''知行网,'www.zhixing123.cn'],],jhrate:['1-100:150.0/7']}},2:{4:{jhinfo:['年计划',''知行网,'www.zhixing123.cn'],],jhrate:['365-365:1000.0']}}}";
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            String name = jsonObject.getString("1");
            String address = jsonObject.getString("2");
            System.out.println("name is:" + name);
            System.out.println("address is:" + address);
            Iterator it = jsonObject.keys();
            while (it.hasNext()) {
                System.out.println(jsonObject.get(it.next()));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * json对象字符串转换
     * 
     * @author wen
     */
    private static void test2() {
        String json = "{‘name’: ‘亲亲宝宝’,'array’:[{'a':'111','b':'222','c':'333'},{},{'a':'999'}],’address’:'亲亲宝宝’}";
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            String name = jsonObject.getString("name");
            String address = jsonObject.getString("address");
            System.out.println("name is:" + name);
            System.out.println("address is:" + address);
            JSONArray jsonArray = jsonObject.getJSONArray("array");
            for (int i = 0; i < jsonArray.size(); i++) {
                System.out.println("item " + i + " :" + jsonArray.getString(i));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * json数组 转换，数组以[开头
     * 
     * @author wen
     */
    private static void test1() {
        boolean[] boolArray = new boolean[] {true, false, true};
        JSONArray jsonArray1 = JSONArray.fromObject(boolArray);
        System.out.println(jsonArray1);
        // prints [true,false,true]
        List list = new ArrayList();
        list.add("first");
        list.add("second");
        JSONArray jsonArray2 = JSONArray.fromObject(list);
        System.out.println(jsonArray2);
        // prints ["first","second"]
        JSONArray jsonArray3 = JSONArray.fromObject("['json','is','easy']");
        System.out.println(jsonArray3);
        // prints ["json","is","easy"]
    }
}