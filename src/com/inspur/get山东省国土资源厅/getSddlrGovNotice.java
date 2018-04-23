package com.inspur.get山东省国土资源厅;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.inspur.es.EsUtil;
import com.inspur.hbase.HbaseUtil;
import com.inspur.util.zpUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import com.inspur.util.MD5;

public class getSddlrGovNotice implements PageProcessor {
	// 站点入口定义//山东省国土资源厅-通知公告
	//private static String inUrl = "http://www.sddlr.gov.cn/zwgk/xuke/chufa/";
	private static String inUrl = "http://www.sddlr.gov.cn/zwgk/notice/";
	//private static String inUrl = "http://www.sddlr.gov.cn/root20/gkml/201702/t20170223_91822.html";
	
		
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
		if (page.getUrl().regex("http:\\/\\/www\\.sddlr\\.gov\\.cn\\/zwgk[\\s\\S]*?").match()) {
			String sRegular = "//div[@class='list01 mt20']/ul/li/a/@href | //div[@class='rel_list list_t']/ul/li/a/@href"; // 
			// 获取列表页面的链接列表
			List<String> listHref = page.getHtml().xpath(sRegular).all();
			System.out.println("AAA : " + listHref.toString());
			
			// 加入爬虫队列
			page.addTargetRequests(listHref);			
		}
		// 2. 如果是用户详细页面
		else {
			num++;
			System.out.println("BBB : "+page.getUrl());
			// 获取详情页面信息
			String title = page.getHtml().xpath("//div[@class='title']/text()").get();
			String cTime = page.getHtml().xpath("//div[@class='infos']/dl/*/text()").all().toString(); 
			String sType = page.getHtml().xpath("//dd[@id='zp']/text()").get();
			String sInfo = page.getHtml().xpath("//div[@id='ztdx']/html()").get();
			
			// 处理正文内容，截取需要的部分
			sInfo = zpUtil.getStrFromSourceStr(sInfo, "([\\s\\S]*?)<div class=\"blank30\">");
			
			System.out.println("  pageKey :"+ MD5.MD5Encode(page.getUrl().toString()) );
			System.out.println("  title :"+ title );
			System.out.println("  sType :"+ sType );
			System.out.println("  cTime :"+ cTime );
			System.out.println("  sInfo :"+ sInfo );

            String pageKey = MD5.MD5Encode(page.getUrl().toString());
            
            // 添加到ES索引中
            //EsUtil.insertDocFromWWW(page.getUrl().toString(), pageKey, title, cTime, sInfo);
            
            // 添加到Hbase中
            String tableName = "newsinfo";
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
		Spider.create(new getSddlrGovNotice()).addUrl(inUrl).thread(5).run();
		endTime = new Date().getTime();
		System.out.println("========小爬虫【结束】喽！=========");
		System.out.println("一共爬到" + num + "个信息！用时为：" + (endTime - startTime) / 1000 + "s");
	}
}
