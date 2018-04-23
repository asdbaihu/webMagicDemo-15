package com.inspur.get山东高校毕业生就业信息网;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspur.es.EsUtil;
import com.inspur.es.IndexInfo;
import com.inspur.es.News;
import com.inspur.hbase.HbaseUtil;
import com.inspur.util.zpUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import com.inspur.util.MD5;

public class getSdBysCnNotice implements PageProcessor {
    // 站点入口定义 山东高校毕业生就业信息网 重要通知
    private static String inUrl = "http://www.sdbys.cn/col/col21/index.html";
    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
    // 用户数量
    private static int num = 0;
    // 搜索关键词
    private static String keyword = "4";
    
    /**
     * process 方法是webmagic爬虫的核心<br>
     * 编写抽取【待爬取目标链接】的逻辑代码在html中。
     */
    @Override
    public void process(Page page) {
        
        // 1. 如果是用户列表页面 【入口页面】，将所有用户的详细页面的url放入target集合中。
        if (page.getUrl().regex("http:\\/\\/www\\.sdbys\\.cn\\/col\\/col21\\/index.html").match()) {
            // String sRegular = "//td[@id='newslist']/table/tbody/tr/td/a/@href";
            // 获取页面dom结构中的script标签
            String sSctipt = page.getHtml().xpath("//script").all().get(3).toString();
            // System.out.println("AAA11 : " + sSctipt);
            
            String sHost = "http://www.sdbys.cn"; // host好像不用自己添加，得试试？？？
            // urls[i]='/art/2017/1/19/art_21_75749.html';headers[i]='·2016年就业质量年度报告';year[i]='2017';month[i]='01';day[i]='19';imgstrs[i]='';
            // 获取列表页面的链接列表
            List<String> listHref = zpUtil.getUrlListFromScript(sSctipt, sHost, "urls\\[i\\]=\\'([\\s\\S]*?)\\';headers\\[i\\]");
            System.out.println("AAA : " + listHref.toString());
            
            // 加入爬虫队列
            page.addTargetRequests(listHref);
        }
        // 2. 如果是用户详细页面
        else {
            num++;
            System.out.println("BBB : " + page.getUrl());
            // 获取详情页面信息
            String pageKey = MD5.MD5Encode(page.getUrl().toString());
            System.out.println("  pageKey :" + pageKey);
            // System.out.println("  BBB111 :" +
            // page.getHtml().xpath("//table[@class='style23']/tbody/tr/td/table/tbody/tr/td/html()").all());
            String title = page.getHtml()
                    .xpath("//table[@class='style23']/tbody/tr/td/table/tbody/tr/td/span/strong/text()")
                    .all()
                    .toString();
            System.out.println("  title :" + title);
            // String cTime =
            // page.getHtml().xpath("//table[@class='style23']/tbody/tr/td/table/tbody/tr/td/span/text()").all().get(1).toString();
            String cTime = page.getHtml().xpath("//table[@width='85%']/tbody/tr/td/span/text()").all().toString();
            System.out.println("  cTime :" + cTime);
            String sInfo = page.getHtml().xpath("//table[@class='style23']/tbody/tr/td/table/tbody/tr[2]/td/table/html()").get();
            System.out.println("  sInfo :" + sInfo);
            
            // 添加到ES索引中
            // EsUtil.insertDocFromWWW(page.getUrl().toString(), pageKey, title, cTime, sInfo);
            
            // 添加到Hbase中
            String tableName = "newsinfo";
            List<Map> cl = new ArrayList();
            Map<String, String> m = new HashMap<String, String>();
            if ("[]".equals(title)) {
                title = page.getUrl().toString();
            }
            m.put("url", page.getUrl().toString());
            m.put("title", title);
            m.put("cTime", cTime);
            m.put("sInfo", sInfo);
            cl.add(m);
            HbaseUtil.insertData(tableName, pageKey, cl);
        }
    }
    
    @Override
    public Site getSite() {
        return this.site;
    }
    
    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("========小爬虫【启动】喽！=========");
        startTime = new Date().getTime();
        Spider.create(new getSdBysCnNotice()).addUrl(inUrl).thread(5).run();
        endTime = new Date().getTime();
        System.out.println("========小爬虫【结束】喽！=========");
        System.out.println("一共爬到" + num + "个信息！用时为：" + (endTime - startTime) / 1000 + "s");
    }
}
