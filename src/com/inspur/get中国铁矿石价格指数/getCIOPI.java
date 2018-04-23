package com.inspur.get中国铁矿石价格指数;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import com.inspur.es.EsUtil;
import com.inspur.hbase.HbaseUtil;
import com.inspur.util.MD5;

public class getCIOPI implements PageProcessor {
    // 站点入口定义//山东省财政厅 -通知公告 || 政策解读
    //private static String inUrl = "http://www.sdcz.gov.cn/sdczww.s?method=lanmu&cid=2";
    private static String inUrl = "http://www.tiekuangshi.com/news/kuaibao/1.htm";
    // private static String inUrl = "http://www.sdcz.gov.cn/sdczww.s?method=lanmu&cid=217";
    // private static String inUrl = "http://www.sdcz.gov.cn/sdczww.s?method=lanmu&cid=217";
    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
    // 用户数量
    private static int num = 0;
    // 搜索关键词
    private static String keyword = "2";
    
    /**
     * process 方法是webmagic爬虫的核心<br>
     * 编写抽取【待爬取目标链接】的逻辑代码在html中。
     */
    @Override
    public void process(Page page) {
        
        // 1. 如果是用户列表页面 【入口页面】，将所有用户的详细页面的url放入target集合中。
        if (page.getUrl().regex("http:\\/\\/www\\.tiekuangshi\\.com\\/news\\/kuaibao\\/[\\d]*\\.htm").match()) {
          
            String sRegular = "//div[@class='pager']/a/@href  | //div[@class='news_list']/ul/li/a/@href";
            // 获取列表页面的链接列表
            List<String> listHref = page.getHtml().xpath(sRegular).all();
            System.out.println("AAA : " + listHref.toString());
            
            // 加入爬虫队列
            page.addTargetRequests(listHref);
        }
        // 2. 如果是用户详细页面
        else {
            num++;
            System.out.println("BBB : " + page.getUrl());
            // 获取详情页面信息
            String title = page.getHtml().xpath("//h1[@class='title']/text()").get();
            String cTime = page.getHtml().xpath("//div[@class='info']/div/text()").get();
            // String cSourceCom = page.getHtml().xpath("//span[@id='source']/text()").get();
            String sInfo = page.getHtml().xpath("//dd[@class='article-content']/p[2]/span/text()").get();
            
            System.out.println("  pageKey :" + MD5.MD5Encode(page.getUrl().toString()));
            System.out.println("  title :" + title);
            System.out.println("  cTime :" + cTime);
            System.out.println("  sInfo :" + sInfo);
            String pageKey = MD5.MD5Encode(page.getUrl().toString());
            
            // 添加到ES索引中
            // EsUtil.insertDocFromWWW(page.getUrl().toString(), pageKey, title, cTime, sInfo);
            
            // 添加到Hbase中
/*            String tableName = "newsinfo";
            List<Map> cl = new ArrayList();
            Map<String, String> m = new HashMap<String, String>();
            if ("[]".equals(title)){
                title = page.getUrl().toString();
            }
            m.put("url", page.getUrl().toString());
            m.put("title", title);
            m.put("cTime", cTime);
            m.put("sInfo", sInfo);
            cl.add(m);
            HbaseUtil.insertData(tableName, pageKey, cl);*/
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
        Spider.create(new getCIOPI()).addUrl(inUrl).thread(5).run();
        endTime = new Date().getTime();
        System.out.println("========小爬虫【结束】喽！=========");
        System.out.println("一共爬到" + num + "个信息！用时为：" + (endTime - startTime) / 1000 + "s");
    }
}
