package zp.demo;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import zp.demo.json1.JSONTool;

public class aaa {
    public static void main(String[] args) {
        String ret = "";
        ret = "{\"state\":\"ok\",\"message\":\"\",\"data\":true}";
        ret = "{\"state\":\"ok\",\"message\":\"\",\"data\":[{\"companyType\":\"企业\",\"webName\":\"山东华凌电缆有限公司\",\"liscense\":\"鲁ICP备10203337号\",\"companyName\":\"山东华凌电缆有限公司\",\"webSite\":[\"www.hl-cable.com\"],\"examineDate\":\"2011-03-30\"}]}";
        //ret = "{\"timestamp\":\"1489641333\",\"errno\":\"0\",\"nonceStr\":\"ee31b7a2-a3af-4947-adb5-ce08c3fe19d6\",\"jsapi_ticket\":\"sM4AOVdWfPE4DxkXGEs8VPaPsaDh6tb4mKK7lj32zgGxHKWp7xXpAs53DcGrMZLXYWMaVp6zDwq19N8P0r5c1w\",\"signature\":\"630f6e0655065ffef569e4908cb6d493cd12329e\",\"url\":\"http://www.tianyancha.com/company/233174024\"}";
        //ret = "{\"state\":\"ok\",\"message\":\"\",\"data\":[{\"id\":1124448117,\"reportYear\":\"2015\"},{\"id\":1124448111,\"reportYear\":\"2014\"},{\"id\":1124448107,\"reportYear\":\"2013\"}]}";
        ppp("111  :  " + ret);
        Pattern pat = Pattern.compile("data\\\":([\\s\\S]*?)\\}");
        Matcher mat = pat.matcher(ret);
        while (mat.find()) {
            ppp("222  :  ");
            ppp("ss  :  " + mat.group(1));
        }
        
        Map aaa = JSONTool.jsonStrToMap(ret);
        ppp("333  :  " + aaa.toString());
        ppp("444  :  " + aaa.get("data"));
        
        String eee = aaa.get("data").toString();
        
        List ddd = JSONTool.jsonStrToList(eee);
        for (int i = 0;i<ddd.size();i++){
            ppp("ddd:  "+i+"  :  "+ ddd.get(i));
        }
    }
    
    public static void ppp(String str) {
        System.out.println(str);
    }
}
