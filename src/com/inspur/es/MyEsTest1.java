package com.inspur.es;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.search.SearchHit;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyEsTest1 {
    // jackson用于序列化操作的mapper
    private static final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * 测试新增doc
     * 
     * @throws UnknownHostException
     * @throws JsonProcessingException
     */
    public static void testAddDoc() throws UnknownHostException, JsonProcessingException {
        IndexInfo ib = new IndexInfo();
        ib.setIndexName("news_index");
        ib.setIndexType("news");
        
        News n = new News("555", "教育部工作网络视频会议的通知1", "2016-11-18 13:34:57", "信息内容5");
        byte[] json = mapper.writeValueAsBytes(n);
        EsUtil.addDocument(ib, n.getId(), json);
    }

    /**
     * 生成索引
     * 
     */
    public static void testCreatIndex() throws Exception {
        IndexInfo ib = new IndexInfo();
        ib.setIndexName("news_index");
        ib.setIndexType("news");
        StringBuffer sb = new StringBuffer();
        sb.append("{\"news\":{");
        sb.append("  \"properties\":{");
        sb.append("    \"pageKey\":{\"type\":\"string\"},");
        sb.append("    \"title\":{\"type\":\"string\",\"analyzer\":\"ik_max_word\"},");
        sb.append("    \"cTime\":{\"type\":\"string\",\"analyzer\":\"ik_max_word\"},");
        sb.append("    \"sInfo\":{\"type\":\"string\",\"index\":\"not_analyzed\",\"ignore_above\":256}");
        //    "message" : {"type" : "string", "index":"not_analyzed","ignore_above":256 }
        sb.append("  }");
        sb.append("}}");
        ib.setMappingStr(sb.toString());
        
        List<BaseObj> newsList = new ArrayList<>();
        newsList.add(new News("111", "关于做好2016届非师范类高校毕业生就业质量年度报告编制发布工作的通知", "2016-12-28 15:44:39", "信息内容1"));
        newsList.add(new News("222", "教育部办公厅关于召开2017届全国普通高校毕业生就业创业工作网络视频会议的通知", "2016-11-27 21:18:56", "信息内容2"));
        newsList.add(new News("333", "教育部办公厅关于召开2017届全国普通高校毕业生就业创业工作网络视频会议的通知", "2016-11-17 13:34:57", "信息内容3"));
        
        EsUtil.createIndex(ib, newsList);
    }
    
    /**
     * 测试search
     * 
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static void testSearch() throws Exception {
        IndexInfo ib = new IndexInfo();
        ib.setIndexName("news_index");
        ib.setIndexType("news");
        
        SearchHit[] hits = EsUtil.search(ib, "title", "通知");
        List<News> newsList = new ArrayList<>();
        for (SearchHit hit : hits) {
            News n = mapper.readValue(hit.getSourceAsString(), News.class);
            newsList.add(n);
            System.out.println(n);
        }        
    }
    
    public static void main(String[] args) throws Exception {
         testCreatIndex();
        //testAddDoc();
         testSearch();
    }
}