package com.inspur.get家庭医生在线;

import java.util.List;
import java.util.Date;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import com.inspur.es.EsUtil;
import com.inspur.util.MD5;

public class getJTYSZX implements PageProcessor {
	// 搜索关键词
	private static String keyword = "1";
	// 站点入口定义//疾病症状大全1
	private static String inUrl = "http://jbk.familydoctor.com.cn/diseasepolymer_2_0_1.html";
	// 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
	// 用户数量
	private static int num = 0;

	/**
	 * process 方法是webmagic爬虫的核心<br>
	 * 编写抽取【待爬取目标链接】的逻辑代码在html中。
	 */
	@Override
	public void process(Page page) {

		// 1. 如果是用户列表页面 【入口页面】，将所有用户的详细页面的url放入target集合中。
        if (page.getUrl().regex("http:\\/\\/jbk\\.familydoctor\\.com\\.cn\\/diseasepolymer_2_0_([\\d]*)\\.html").match()) {
		//if (page.getUrl().regex("http:\\/\\/www\\.shandongbusiness\\.gov\\.cn\\/Office-10-([\\d]*)\\.html").match()) {
			String sRegular = "//div[@class='letter-list']/dl/dd/a/@href";
			System.out.println("AAA : " + page.getUrl());
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
			String title = page.getHtml().xpath("//div[@id='ku-nav-cont']/h2/a/text()").get();
            String t1 = page.getHtml().xpath("//div[@class='sbj']/b/text()").get();
			String sInfo = page.getHtml().xpath("//div[@class='detail-cont']/p/text()").all().toString();
			
			System.out.println("  pageKey :"+ MD5.MD5Encode(page.getUrl().toString()) );
			System.out.println("  title :"+ title );
			System.out.println("  t1 :"+ t1 );
			System.out.println("  sInfo :"+ sInfo);
			
            // 添加到ES索引中
            String pageKey = MD5.MD5Encode(page.getUrl().toString());
             EsUtil.insertDocFromWWW(page.getUrl().toString(), pageKey, title, t1, sInfo);
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
		Spider.create(new getJTYSZX()).addUrl(inUrl).thread(5).run();
		endTime = new Date().getTime();
		System.out.println("========小爬虫【结束】喽！=========");
		System.out.println("一共爬到" + num + "个信息！用时为：" + (endTime - startTime) / 1000 + "s");
	}
}
